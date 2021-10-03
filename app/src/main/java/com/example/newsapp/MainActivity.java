package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Clickevent {
    ArrayList<news>updatedNews=new ArrayList<>();




    RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this,this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        }
        public void fetchData(){
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        JSONArray jsonArray = response.getJSONArray("articles");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonNewsObject = jsonArray.getJSONObject(i);

                            Log.d("APP",jsonNewsObject.getString("title"));
                            news News = new news(
                                    jsonNewsObject.getString("title"),

                                    jsonNewsObject.getString("description"),
                                    jsonNewsObject.getString("url"),
                                    jsonNewsObject.getString("urlToImage")
                            );
                            updatedNews.add(News);

                        }
                        recyclerViewAdapter.UpdateNews(updatedNews);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Something went Wrong",Toast.LENGTH_LONG).show();
                }

            });
            requestQueue.add(jsonObjectRequest);
        }




        @Override
        public void onItemClick(int position){
            String url=updatedNews.get(position).url;
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);

        }
    }