package com.example.myapplication.mainPage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class viewAdapter extends RecyclerView.Adapter<viewAdapter.ViewHolder> {

    //data
    private ArrayList<manga> localDataSet;

    public viewAdapter(ArrayList<manga> localDataSet, Context context) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("mytag1", "onBindViewHolder: called.");
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_layout_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewAdapter.ViewHolder holder, int position) {
        Log.d("mytag1", "title: " + localDataSet.get(position).getTitle()
        +"\nartist: " + localDataSet.get(position).getArtist()
        +"\nurl: " + localDataSet.get(position).getImageUrl());

        holder.title.setText(localDataSet.get(position).getTitle());
        holder.lang.setText(localDataSet.get(position).getArtist());
        Picasso.get().load(localDataSet.get(position).getImageUrl()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //define views
        ImageView imageView;
        TextView title;
        TextView lang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.main_item_image);
            title = (TextView) itemView.findViewById(R.id.main_item_title);
            lang = (TextView) itemView.findViewById(R.id.main_item_lang);
        }
    }
}
