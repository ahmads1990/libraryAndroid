package com.example.myapplication.mainPage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //url for cover list
    private String url = "https://api.mangadex.org/manga?contentRating[]=safe&includes[]=cover_art&includes[]=author&includes[]=artist&limit=25";

    //data
    private ArrayList<manga> mangaArrayList;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

        Log.d("mytag", "working");
        mangaArrayList = new ArrayList<>();
        initRecyclerView();

    }

    private void initRecyclerView() {
        Context context = this;
        //sendRequestLogin();
        sendRequestCover(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                RecyclerView recyclerView = findViewById(R.id.main_recycler);
                //
                viewAdapter adapter = new viewAdapter(mangaArrayList, context);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
        });
    }

    private void sendRequestLogin() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.mangadex.org/auth/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "ahmads1990");
                params.put("password", "1234");
                Log.d("mytag", "auth started");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                Log.d("mytag", "auth failed");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void sendRequestCover(final VolleyCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("mytag1", "start here");
                            //extract data from json
                            JSONObject first = new JSONObject(response);
                            JSONArray jsonArray = first.getJSONArray("data");

                            //go throw all the json array
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    JSONObject subObject;

                                    //get id
                                    String id = jsonObject.getString("id");
                                    Log.d("mytag1", "id: " + id);

                                    //get title
                                    subObject = jsonObject.getJSONObject("attributes");
                                    String title = subObject.getJSONObject("title").getString("en");
                                    Log.d("mytag1", "title: " + title);

                                    //get description
                                    String description = subObject.getJSONObject("description").getString("en");
                                    Log.d("mytag1", "desc: " + description);

                                    //get publish year
                                    int publishYear = subObject.getInt("year");
                                    Log.d("mytag1", "year: " + publishYear);

                                    //getting inner array for the author artist cover filename
                                    JSONArray innerArray = jsonObject.getJSONArray("relationships");

                                    //get author name
                                    jsonObject = innerArray.getJSONObject(0).getJSONObject("attributes");
                                    String author = jsonObject.getString("name");
                                    Log.d("mytag1", "author: " + author);

                                    //get artist name
                                    jsonObject = innerArray.getJSONObject(1).getJSONObject("attributes");
                                    String artist = jsonObject.getString("name");
                                    Log.d("mytag1", "artist: " + artist);

                                    //get file name
                                    jsonObject = innerArray.getJSONObject(2).getJSONObject("attributes");
                                    String fileName = jsonObject.getString("fileName");

                                    //image url https://uploads.mangadex.org/covers/{ manga.id }/{ cover.filename }.256.jpg
                                    String url = "https://uploads.mangadex.org/covers/" + id + '/' + fileName + ".512.jpg";
                                    Log.d("mytag1", "url is :" + url);

                                    mangaArrayList.add(new manga(id, title, description, publishYear, author, artist, url));
                                    Log.d("mytag1", "manga size " + mangaArrayList.size());

                                    callBack.onSuccess();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            Log.d("mytag", "json exceptiopn" + e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mytag", "error listener");
                Log.d("mytag", "error message" + error.toString());
            }
        }
        );
        queue.add(stringRequest);
    }

    public interface VolleyCallBack {
        void onSuccess();
    }
}