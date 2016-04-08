package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    // for example of Picasso
    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
            "http://i.imgur.com/DvpvklR.png",
            "http://i.imgur.com/px142gvb.jpg",
            "http://i.imgur.com/HAhJkQlb.jpg",
            "http://i.imgur.com/2L4g6YKb.jpg",
            "http://i.imgur.com/Vb3WSh7b.jpg",
            "http://i.imgur.com/GzR9Ea8b.jpg"
    };

    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMoviesAdapter = new ImageListAdapter(getActivity(), eatFoodyImages);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_movies);
        listView.setAdapter(mMoviesAdapter);

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
// TODO load new list of movies from server
//        SunshineSyncAdapter.syncImmediately(getActivity());
    }

    public void setUseTodayLayout(boolean useSingleMovieLayout) {
        mUseSingleMovieLayout = useSingleMovieLayout;
// TODO if it's need  create setUseSingleMovieLayou in the MoviesAdapter
/*        if(mMoviesAdapter != null) {
            mMoviesAdapter .setUseSingleMovieLayout(useSingleMovieLayout);
        }
*/    }

}
