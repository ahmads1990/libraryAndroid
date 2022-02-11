package com.example.myapplication.mainPage;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class gallery extends AppCompatActivity {

    Button back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_layout_item_gallery);

        manga gallery_manga = (manga) getIntent().getSerializableExtra("manga");
        setData(gallery_manga);

        back = (Button) findViewById(R.id.gallery_back_btn);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    private void setData(manga gallery) {
        ImageView img = (ImageView) findViewById(R.id.gallery_img);
        TextView title = (TextView) findViewById(R.id.gallery_title);
        TextView author = (TextView) findViewById(R.id.gallery_author);
        TextView artist = (TextView) findViewById(R.id.gallery_artist);
        TextView desc = (TextView) findViewById(R.id.gallery_desc_text);

        //load data
        Picasso.get().load(gallery.getImageUrl()).into(img);
        title.setText(gallery.getTitle());
        author.setText("author: " + gallery.getAuthor());
        artist.setText("artist: " + gallery.getArtist());
        desc.setText(gallery.getDescription());
    }
}
