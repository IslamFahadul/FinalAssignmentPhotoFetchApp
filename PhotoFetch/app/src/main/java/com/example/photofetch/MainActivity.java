package com.example.photofetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PhotoModel> list;
    RecyclerView listView;
    PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        listView = findViewById(R.id.list);
        adapter = new PhotoAdapter(list, this);
        listView.setAdapter(adapter);
        getPhotos();

    }

    void getPhotos() {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Loading");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://muthosoft.com/univ/photos";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String[] image = response.split(":|,");
                        ArrayList<String> imagePath = new ArrayList<>();
                        ArrayList<String> imageDescription = new ArrayList<>();

                        for (int i = 0; i < image.length; i++) {
                            if (i % 2 == 0) {
                                imagePath.add(image[i]);
                            } else {
                                imageDescription.add(image[i]);
                            }
                        }

                        for (int i = 0; i < imagePath.size(); i++) {
                            list.add(new PhotoModel(imagePath.get(i), imageDescription.get(i)));
                            progressDialog.dismiss();
                        }

                        adapter.notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }
}