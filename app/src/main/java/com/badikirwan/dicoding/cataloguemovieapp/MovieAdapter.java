package com.badikirwan.dicoding.cataloguemovieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieModel> mDataMovie = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieModel> items) {
        mDataMovie = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieModel item) {
        mDataMovie.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mDataMovie.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {

        if (mDataMovie == null) {
            return 0;
        }
        return mDataMovie.size();
    }

    @Override
    public MovieModel getItem(int position) {
        return mDataMovie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_list, null);
            holder.tvMovieTitle = (TextView) convertView.findViewById(R.id.tv_movie_name);
            holder.tvMovieOverview = (TextView) convertView.findViewById(R.id.tv_movie_description);
            holder.tvReleaseDate = (TextView) convertView.findViewById(R.id.tv_movie_date);
            holder.imgPosterMovie = (ImageView) convertView.findViewById(R.id.img_movie);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvMovieTitle.setText(mDataMovie.get(position).getTitle_movie());
        holder.tvMovieOverview.setText(mDataMovie.get(position).getOverview());

        String daterelease = mDataMovie.get(position).getRelease_date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(daterelease);
            SimpleDateFormat newFormatDate = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String dateRelase = newFormatDate.format(date);
            holder.tvReleaseDate.setText(dateRelase);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String path_poster = "http://image.tmdb.org/t/p/w185/"+ mDataMovie.get(position).getPoster();
        Glide.with(context)
                .load(path_poster)
                .crossFade()
                .into(holder.imgPosterMovie);

        return convertView;
    }

    private static class ViewHolder {

        TextView tvMovieTitle;
        TextView tvMovieOverview;
        TextView tvReleaseDate;
        ImageView imgPosterMovie;
    }
}
