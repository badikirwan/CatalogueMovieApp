package com.badikirwan.dicoding.cataloguemovieapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieListLoader extends AsyncTaskLoader<ArrayList<MovieModel>> {

    private static final String API_KEY = "33a4f2f91284c9133695dfba6bd802da";
    private ArrayList<MovieModel> mDataMovie;
    private boolean mHasResult = false;
    private String mJudulMovie;

    public MovieListLoader(Context context, String judulMovie) {
        super(context);
        onContentChanged();
        this.mJudulMovie = judulMovie;
    }

    @Override
    public void deliverResult(ArrayList<MovieModel> data) {
        mDataMovie = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        } else if (mHasResult) {
            deliverResult(mDataMovie);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mDataMovie);
            mDataMovie = null;
            mHasResult = false;
        }
    }

    private void onReleaseResources(ArrayList<MovieModel> mDataMovie) {

    }

    @Override
    public ArrayList<MovieModel> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieModel> movieModels = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+
                API_KEY+"&language=en-US&query="+ mJudulMovie;

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String result = new String(responseBody);
                    JSONObject responsObject = new JSONObject(result);
                    JSONArray list = responsObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieModel movieModel = new MovieModel(movie);
                        movieModels.add(movieModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return movieModels;
    }
}
