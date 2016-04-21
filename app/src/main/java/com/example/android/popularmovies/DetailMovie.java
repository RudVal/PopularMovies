package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Valeri on 17.04.2016.
 */
public class DetailMovie extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, new DetailFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {

        private static final  String LOG_TAG = DetailFragment.class.getName();
        private  static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

        private String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/w185/";

//        private String mForecastStr;
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

        private ImageView mPosterView;
        private TextView mTitelView;
        private TextView mReleaseDate;
        private TextView mVoteAverage;
        private TextView mOverview;

        public DetailFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            // The detail Activity called via Intent
            Intent intent = getActivity().getIntent();
            if (intent != null) {
                title = intent.getExtras().getString(MDB_TITLE);
                release_date = intent.getExtras().getString(MDB_RELEASE_DATE);
                poster_path = intent.getExtras().getString(MDB_POSTER_PATH);
                user_rating = intent.getExtras().getString(MDB_USER_RATING);
                plot_synopsis = intent.getExtras().getString(MDB_PLOT_SYNOPSIS);
            }
            String imageUrls = BASE_IMAGE_PATH + poster_path;

            mPosterView = (ImageView) rootView.findViewById(R.id.poster_view);
            Picasso.with(getContext()).load(imageUrls)
                    .fit().into(mPosterView);

            mTitelView = (TextView) rootView.findViewById(R.id.titel_text);
            mTitelView.setText(title);
            mReleaseDate = (TextView) rootView.findViewById(R.id.release_date);
            mReleaseDate.setText(release_date);
            mVoteAverage = (TextView) rootView.findViewById(R.id.user_rating);
            mVoteAverage.setText(user_rating);
            mOverview = (TextView) rootView.findViewById(R.id.plot_synopsis);
            mOverview.setText(plot_synopsis);

            return rootView;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.detailfragment, menu);

            // Retrieve the share menu item
            MenuItem menuItem = menu.findItem(R.id.action_share);
            // Get the provider and hold onto it to set/change the share intent
            ShareActionProvider mShareActionProvider =
                    (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

            // Attach an intent to this ShareActionProvider.
            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            } else {
                Log.d(LOG_TAG, "Share Action Provider is null?");
            }
        }


        private Intent createShareForecastIntent() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
//            shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr + FORECAST_SHARE_HASHTAG);
            return shareIntent;
        }
    }
}
