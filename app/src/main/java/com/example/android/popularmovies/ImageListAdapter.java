package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Valeri on 29.03.2016.
 */
public class ImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;

//    private String[] imageUrls;
    private ArrayList<String> imageUrls;

//    public ImageListAdapter(Context context, String[] imageUrls) {
    public ImageListAdapter(Context context, ArrayList<String> imageUrls) {
        super(context, R.layout.fragment_main , imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.list_item_movie, parent, false);
        }

//        Picasso.with(context).load(imageUrls[position])
        Picasso.with(context).load(imageUrls.get(position))
        .fit() // will explain later
                .into((ImageView) convertView);
        return convertView;
    }
}