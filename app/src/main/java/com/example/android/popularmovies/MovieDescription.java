package com.example.android.popularmovies;

/**
 * Created by Valeri on 21.04.2016.
 */
public class MovieDescription {

    private String title;
    private String release_date;
    private String poster_path;
    private String user_rating;
    private String plot_synopsis;

    public void setPlot_synopsis(String plot_synopsis) {
        this.plot_synopsis = plot_synopsis;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }


    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public String getPlot_synopsis() {
        return plot_synopsis;
    }
}
