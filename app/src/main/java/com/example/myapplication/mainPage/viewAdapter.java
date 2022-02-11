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

        Picasso.get().load(localDataSet.get(position).getImageUrl()).into(holder.imageView);
        holder.title.setText(localDataSet.get(position).getTitle());
        holder.description.setText(localDataSet.get(position).getDescription());
        holder.publishYear.setText(localDataSet.get(position).getPublishYear());
        holder.author.setText(localDataSet.get(position).getAuthor());
        holder.artist.setText(localDataSet.get(position).getArtist());
    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //define views
        ImageView imageView;
        TextView title;
        TextView description;
        TextView publishYear;
        TextView author;
        TextView artist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.main_item_image);
            title = (TextView) itemView.findViewById(R.id.main_item_title);
            description = (TextView) itemView.findViewById(R.id.main_item_desc);
            publishYear = (TextView) itemView.findViewById(R.id.main_item_publishYear);
            author = (TextView) itemView.findViewById(R.id.main_item_author);
            artist = (TextView) itemView.findViewById(R.id.main_item_artist);
        }
    }
}
