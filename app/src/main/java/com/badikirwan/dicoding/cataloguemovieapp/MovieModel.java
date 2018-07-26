package com.badikirwan.dicoding.cataloguemovieapp;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieModel {

    private String title_movie;
    private String poster;
    private String overview;
    private String release_date;
    private String backdrop;

    MovieModel(JSONObject movie) {
        try {
            String movieTitle = movie.getString("title");
            String movieOverview = movie.getString("overview");
            String movieReleaseDate = movie.getString("release_date");
            String moviePoster = movie.getString("poster_path");
            String movieBackdrop = movie.getString("backdrop_path");

            this.title_movie = movieTitle;
            this.overview = movieOverview;
            this.release_date = movieReleaseDate;
            this.poster = moviePoster;
            this.backdrop = movieBackdrop;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle_movie() {
        return title_movie;
    }

    public void setTitle_movie(String title_movie) {
        this.title_movie = title_movie;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}
