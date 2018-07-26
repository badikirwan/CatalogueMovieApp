package com.badikirwan.dicoding.cataloguemovieapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieModel>>{

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private MovieAdapter adapter;
    private ListView listView;
    private EditText edtTitle;
    private Button btnCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        listView = (ListView) findViewById(R.id.lv_movie);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listenerList);

        edtTitle = (EditText) findViewById(R.id.edt_cari_movie);
        btnCari = (Button) findViewById(R.id.btn_cari);
        btnCari.setOnClickListener(myListenet);

        String movie_title = edtTitle.getText().toString().trim();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MOVIE, movie_title);

        getLoaderManager().initLoader(0, bundle, this);

    }

    @Override
    public Loader<ArrayList<MovieModel>> onCreateLoader(int id, Bundle args) {
        String titleMoviewSearch = "";

        if (args != null) {
            titleMoviewSearch = args.getString(EXTRA_MOVIE);
        }
        return new MovieListLoader(this, titleMoviewSearch);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieModel>> loader, ArrayList<MovieModel> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieModel>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListenet = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String judul_movie = edtTitle.getText().toString();

            if(TextUtils.isEmpty(judul_movie)){
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_MOVIE, judul_movie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };

    AdapterView.OnItemClickListener listenerList = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MovieModel movieItem = (MovieModel) parent.getItemAtPosition(position);

            Intent moveDetail = new Intent(MainActivity.this, MovieDetailActivity.class);
            moveDetail.putExtra(MovieDetailActivity.EXTRA_TITLE, movieItem.getTitle_movie());
            moveDetail.putExtra(MovieDetailActivity.EXTRA_OVERVIEW, movieItem.getOverview());
            moveDetail.putExtra(MovieDetailActivity.EXTRA_RELEASE_DATE, movieItem.getRelease_date());
            moveDetail.putExtra(MovieDetailActivity.EXTRA_IMG_POSTER, movieItem.getPoster());
            moveDetail.putExtra(MovieDetailActivity.EXTRA_IMG_BACKDROP, movieItem.getBackdrop());
            startActivity(moveDetail);
        }
    };
}
