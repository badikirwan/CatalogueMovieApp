package com.badikirwan.dicoding.cataloguemovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener{

    public static String EXTRA_TITLE = "EXTRA_TITLE";
    public static String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";
    public static String EXTRA_IMG_POSTER = "EXTRA_IMG_POSTER";
    public static String EXTRA_IMG_BACKDROP = "EXTRA_IMG_BACKDROP";
    public static String EXTRA_RELEASE_DATE = "EXTRA_RELEASE_DATE";
    private TextView tvTitle;
    private TextView tvOvervie;
    private TextView tvRelease;
    private ImageView imgBackdrop;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvTitle = (TextView) findViewById(R.id.tv_title_movie);
        tvOvervie = (TextView) findViewById(R.id.tv_overview_movie);
        tvRelease = (TextView) findViewById(R.id.tv_release_movie);
        imgBackdrop = (ImageView) findViewById(R.id.img_backdrop_movie);
        btnPlay = (Button) findViewById(R.id.btn_play);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        String backdrop = getIntent().getStringExtra(EXTRA_IMG_BACKDROP);
        String path_backdrop = "http://image.tmdb.org/t/p/w185/" + backdrop;

        tvTitle.setText(title);
        tvOvervie.setText(overview);
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);
            SimpleDateFormat new_format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            tvRelease.setText(new_format.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(this)
                .load(path_backdrop)
                .crossFade()
                .into(imgBackdrop);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_play) {
            Toast.makeText(this, "Playing Movie", Toast.LENGTH_SHORT).show();
        }
    }
}
