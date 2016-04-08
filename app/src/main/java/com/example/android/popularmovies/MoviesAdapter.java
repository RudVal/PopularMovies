package com.example.android.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Valeri on 23.03.2016.
 */
public class MoviesAdapter  extends CursorAdapter {

    // using separate view for Single Movie or not
    private boolean mUseSingleMovieLayout = true;
    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    /**
     * Cache of the children views for a forecast list item.
     */
    public static class ViewHolder {

// TODO adopt  ViewHolder to movie parameters
/*        public final ImageView iconView;
        public final TextView dateView;
        public final TextView descriptionView;
        public final TextView highTempView;
        public final TextView lowTempView;
*/
        public ViewHolder(View view) {
/*            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
            descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
*/        }

    }


    public MoviesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    @Override
    public int getItemViewType(int position) {
        // TODO if it need you can layout-type in the list
//        return (position == 0 && mUseSingleMovieLayout) ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
        return 0;
    }

    @Override
    public int getViewTypeCount () {
        return 2;
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//  TODO there are the place to define layout-type for item in the List
        int layoutId = R.layout.list_item_movie;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    /*
        This is where we fill-in the views with the contents of the cursor.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
     // TODO fill    viewHolder with parameters from cursor

    }
/*    public void setUseSingleMovieLayout(boolean useTodayLayout) {
        mUseSingleMovieLayout = useTodayLayout;
    }
*/
}
