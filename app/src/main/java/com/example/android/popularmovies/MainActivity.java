package com.example.android.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity  extends ActionBarActivity { // extends AppCompatActivity

//    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private final String FORECASTFRAGMENT_TAG = "FFTAG";

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Log.d(LOG_TAG, "onCreate called");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MoviesFragment())
//                    .add(R.id.container, new MoviesFragment(), FORECASTFRAGMENT_TAG)
                    .commit();
        }

// TODO TwoPane or not TwoPane
/*        if (findViewById(R.id.weather_detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.weather_detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
//            getSupportActionBar().setElevation(0f);
        }
*/
//        MoviesFragment moviesFragment = ((MoviesFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_forecast));
//        moviesFragment.setUseTodayLayout(!mTwoPane);
// TODO SyncAdapter.initializeSyncAdapter(this)
//        SunshineSyncAdapter.initializeSyncAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
// TODO it maybe need something more  in onResume
    }

    @Override
    // The activity is about to become visible.
    protected void onStart() {
//        Log.d(LOG_TAG, "onStart called");
        super.onStart();

    }
    @Override
    // Another activity is taking focus (this activity is about to be "paused").
    protected void onPause() {
//        Log.d(LOG_TAG, "onPause called");
        super.onPause();

    }

    @Override
    // The activity is about to be destroyed.
    protected void onDestroy() {
//        Log.d(LOG_TAG, "onDestroy called");
        super.onDestroy();

    }

    @Override
    // The activity is no longer visible (it is now "stopped")
    protected void onStop() {
//        Log.d(LOG_TAG, "onStop called");
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

 //   @Override
    public void onItemSelected(Uri contentUri) {

//  TODO  onItemSelected in MainActivity
/*        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            args.putParcelable(DetailFragment.DETAIL_URI, contentUri);

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.weather_detail_container, fragment, DETAILFRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .setData(contentUri);
            startActivity(intent);
        }
*/
    }

}
