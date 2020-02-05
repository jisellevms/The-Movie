package com.jisellemartins.themovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jisellemartins.themovie.R;
import com.jisellemartins.themovie.model.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.Holder> {

    ArrayList<Results> list;

    public AdapterMovie(ArrayList<Results> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it_movie, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Results results = list.get(position);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + results.getPoster_path()).into(holder.poster_path);
        holder.title.setText(results.getTitle());
        holder.release_date.setText(results.getRelease_date());
        holder.overview.setText(results.getOverview());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView poster_path;
        private TextView title, overview, release_date;

        public Holder(@NonNull View itemView) {
            super(itemView);

            poster_path = itemView.findViewById(R.id.poster_path);
            title = itemView.findViewById(R.id.title_movie);
            overview = itemView.findViewById(R.id.overview);
            release_date = itemView.findViewById(R.id.release_date);
        }
    }
}
