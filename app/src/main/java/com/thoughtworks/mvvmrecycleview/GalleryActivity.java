package com.thoughtworks.mvvmrecycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = GalleryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("image_url") && intent.hasExtra("image_name")) {
            String imageUrl = intent.getStringExtra("image_url");
            String imageName = intent.getStringExtra("image_name");

            setImage(imageUrl, imageName);
        }
    }

    private void setImage(String imageUrl, String imageName) {
        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);
        ImageView imageView = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(imageView);
    }
}