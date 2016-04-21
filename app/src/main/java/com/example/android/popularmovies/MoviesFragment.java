package com.example.android.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valeri on 23.03.2016.
 */
public class MoviesFragment extends Fragment {
    public static final String LOG_TAG = MoviesFragment.class.getSimpleName();
//    private MoviesAdapter mMoviesAdapter;
    private ImageListAdapter mMoviesAdapter;
    private static final int MOVIES_LOADER = 0;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private boolean mUseSingleMovieLayout;

    private String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/w185/";
    private final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";

    //    private String[] movieImages ;
    private ArrayList<String> movieImages = new ArrayList<String>();
    private List<MovieDescription> moviesDescriptionsList = new ArrayList<MovieDescription>();

    private String movieType = "top_rated";

    final String MDB_RESULTS = "results";
    final String MDB_TITLE = "title";
    final String MDB_RELEASE_DATE = "release_date";
    final String MDB_POSTER_PATH = "poster_path";
    final String MDB_USER_RATING = "vote_average";
    final String MDB_PLOT_SYNOPSIS = "overview";

    private String title;
    private String release_date;
    private String poster_path;
    private String user_rating;
    private String plot_synopsis;


// themoviedb.org access parameters
//    API-Schlüssel       340e4849da2cc7a913903be7febe9463
//    Example API Request:    https://api.themoviedb.org/3/movie/550?api_key=340e4849da2cc7a913903be7febe9463


    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMoviesAdapter = new ImageListAdapter(getActivity(), movieImages);
//        mMoviesAdapter = new ImageListAdapter(getActivity(), new ArrayList<String>());
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // Get a reference to the ListView, and attach this adapter to it.
        GridView listView = (GridView) rootView.findViewById(R.id.gridView);
        listView.setAdapter(mMoviesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailMovie.class);
//                String forecast = (String)mMoviesAdapter.getItem(i);
//                String send = "Overview" + movieOverview[i] + "\n" + "Release Date" + movieReleaseDate[i];
                MovieDescription descr = (MovieDescription)moviesDescriptionsList.get(i);
                intent.putExtra(MDB_TITLE, descr.getTitle());
                intent.putExtra(MDB_RELEASE_DATE, descr.getRelease_date());
                intent.putExtra(MDB_POSTER_PATH, descr.getPoster_path());
                intent.putExtra(MDB_USER_RATING, descr.getUser_rating());
                intent.putExtra(MDB_PLOT_SYNOPSIS, descr.getPlot_synopsis());
                startActivity(intent);
            }
        });

//        return super.onCreateView(inflater, container, savedInstanceState);
        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
// TODO create Menu in the  MoviesFragment
        inflater.inflate(R.menu.moviesfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
// TODO start some function if Menu used
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateMovies();
           return true;
        }
/*        if(id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
*/
        return false;
    }

    private void updateMovies() {
        FetchMovieTask movieTask = new FetchMovieTask();
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        movieType = sharedPrefs.getString(
                getString(R.string.pref_movies_key),
                getString(R.string.pref_movies_popular));

        movieTask.execute(movieType);
//        SunshineSyncAdapter.syncImmediately(getActivity());
    }

    public void setUseTodayLayout(boolean useSingleMovieLayout) {
        mUseSingleMovieLayout = useSingleMovieLayout;
// TODO if it's need  create setUseSingleMovieLayou in the MoviesAdapter
/*        if(mMoviesAdapter != null) {
            mMoviesAdapter .setUseSingleMovieLayout(useSingleMovieLayout);
        }
*/    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    public class FetchMovieTask  extends AsyncTask<String, Void, String[]> {

        private String[] getMoviesDataFromJson(String movieJsonStr, int numDays)
                throws JSONException {
//            String[] resultStrs = new String[numDays];

            // These are the names of the JSON objects that need to be extracted.
/*            final String MDB_RESULTS = "results";
            final String MDB_TITLE = "title";
            final String MDB_RELEASE_DATE = "release_date";
            final String MDB_POSTER_PATH = "poster_path";
            final String MDB_USER_RATING = "vote_average";
            final String MDB_PLOT_SYNOPSIS = "overview";
*/
            moviesDescriptionsList.clear();
            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = movieJson.getJSONArray(MDB_RESULTS);
//            movieImages = new String[movieArray.length()];
            String[] resultStrs = new String[movieArray.length()];

            for(int i = 0; i < movieArray.length()-1 ; i++) {
/*                String title;
                String release_date;
                String poster_path;
                String user_rating;
                String plot_synopsis;
*/
                // Get the JSON object representing the one movie
                JSONObject oneMovieObject = movieArray.getJSONObject(i);
                title = oneMovieObject.getString(MDB_TITLE);
                release_date = oneMovieObject.getString(MDB_RELEASE_DATE);
                poster_path = oneMovieObject.getString(MDB_POSTER_PATH);
                user_rating = oneMovieObject.getString(MDB_USER_RATING);
                plot_synopsis = oneMovieObject.getString(MDB_PLOT_SYNOPSIS);

                MovieDescription description = new MovieDescription();
                description.setTitle(title);
                description.setRelease_date(release_date);
                description.setPoster_path(poster_path);
                description.setUser_rating(user_rating);
                description.setPlot_synopsis(plot_synopsis);

                moviesDescriptionsList.add(description);

//                resultStrs[i] = title + " - " + release_date + " - " + poster_path + " - "
//                        + vote_average; // + " - " + plot_synopsis;
                resultStrs[i] = BASE_IMAGE_PATH + poster_path;
                movieImages.add(BASE_IMAGE_PATH + poster_path);
//                movieImages[i] = BASE_IMAGE_PATH + poster_path;
            }
            return resultStrs;
        }

            @Override
        protected String[] doInBackground(String... params) {
            String forecastJsonStr = null;
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if(params.length == 0){
                return null;
            }
            String format = "json";
            String units = "metric";
            int numDays = 7;
            String API_KEY = ***REMOVED***;

//            https://api.themoviedb.org/3/movie/top_rated?api_key=340e4849da2cc7a913903be7febe9463

            try {
//                final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
                final String APY_KEY_PARAM = "api_key";

                Uri builtUri = Uri.parse(MOVIE_BASE_URL + params[0]).buildUpon()
                            .appendQueryParameter(APY_KEY_PARAM, API_KEY)
                            .build();
                URL url = new URL(builtUri.toString());

                // Create request
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();

            }catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the movie data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                String[] movieArr = getMoviesDataFromJson(forecastJsonStr, numDays);
                return movieArr;
            } catch(JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
                return null;
        }  // end of doInBackground

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {
                mMoviesAdapter.clear();
                for(String oneMovieStr : result) {
                    mMoviesAdapter.add(oneMovieStr);
                }
                mMoviesAdapter.notifyDataSetChanged();
                // New data is back from the server.  Hooray!
            }
        }

    }  // end of FetchMovieTask


}
