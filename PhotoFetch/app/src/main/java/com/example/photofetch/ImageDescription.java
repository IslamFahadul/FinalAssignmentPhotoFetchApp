package com.example.photofetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ImageDescription extends AppCompatActivity {

    TextView imageName, imageDescription;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description);
        imageName = findViewById(R.id.imageName);
        imageDescription = findViewById(R.id.description);
        imageView = findViewById(R.id.imageView);

        imageName.setText(getIntent().getStringExtra("imageName"));
        imageDescription.setText(getIntent().getStringExtra("imageDescription"));
        Glide.with(this).load(getIntent().getStringExtra("imagePath")).into(imageView);


    }
}