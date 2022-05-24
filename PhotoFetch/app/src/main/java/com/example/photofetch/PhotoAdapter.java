package com.example.photofetch;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {
    ArrayList<PhotoModel> list;
    Context context;

    public PhotoAdapter(ArrayList<PhotoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View rowView) {
            super(rowView);
            imageView = rowView.findViewById(R.id.imageView);

        }
    }

    @Override
    public PhotoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_photos, parent, false);

        return new PhotoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.MyViewHolder viewHolder, int position) {

        PhotoModel model = list.get(position);


        Glide.with(context).load("https://muthosoft.com/univ/photos/" + model.getImagePath()).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ImageDescription.class);
                intent.putExtra("imageName", model.getImagePath());
                intent.putExtra("imageDescription", model.getDescription());
                intent.putExtra("imagePath", "https://muthosoft.com/univ/photos/" + model.getImagePath());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


