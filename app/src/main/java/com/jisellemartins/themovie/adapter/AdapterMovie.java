package com.jisellemartins.themovie.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.jisellemartins.themovie.R;
import com.jisellemartins.themovie.model.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.Holder> {

    ArrayList<Results> list;
    private AlertDialog alert;
private Activity activity;

    public AdapterMovie(ArrayList<Results> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it_movie, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        final Results results = list.get(position);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + results.getPoster_path()).into(holder.poster_path);
        holder.poster_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDetails(results.getTitle(), results.getRelease_date(), results.getOverview());
            }
        });
    }

    public void showDialogDetails(String title, String releaseDate, String overview) {
        LayoutInflater li = activity.getLayoutInflater();

        View view = li.inflate(R.layout.dialog_details_movie, null);

        TextView txtTitleMovie = view.findViewById(R.id.titleMovie);
        txtTitleMovie.setText(title);
        TextView txtReleaseDate = view.findViewById(R.id.releaseDate);
        txtReleaseDate.setText(releaseDate);
        TextView txtOverview = view.findViewById(R.id.overview);
        txtOverview.setText(overview);

        view.findViewById(R.id.linearDialog).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alert.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        alert = builder.create();
        alert.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView poster_path;

        public Holder(@NonNull View itemView) {
            super(itemView);

            poster_path = itemView.findViewById(R.id.poster_path);

        }
    }
}
