package com.ameya.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {
    private String TAG = ItemsActivity.class.getName();

    private static final String JSON_URL = "https://jsonplaceholder.typicode.com/photos";

    private ListView listView;

    private List<Item> itemList;

    private SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Inside onCreate");
        setContentView(R.layout.activity_items);

        db = new SQLiteDatabaseHandler(this);

        listView = (ListView) findViewById(R.id.listView);
        itemList = new ArrayList<>();

        loadItemList();
    }

    private void loadItemList() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG,"Response received");

                        try {
                            JSONArray itemArray = new JSONArray(response);

                            for (int i = 0; i < itemArray.length(); i++) {
                                JSONObject itemObject = itemArray.getJSONObject(i);
                                Log.d(TAG,"title is " + itemObject.getString("title"));
                                Log.d(TAG,"thumbnail url is " + itemObject.getString("thumbnailUrl"));
                                Log.d(TAG,"Id is " + itemObject.getInt("id"));

                                Item item = new Item(itemObject.getInt("id"), itemObject.getString("title"),itemObject.getString("thumbnailUrl"));

                                itemList.add(item);
                            }

                            ItemsViewAdapter adapter = new ItemsViewAdapter(itemList, getApplicationContext());

                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            Log.d(TAG,"JSON Exception");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.getMessage());
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }
}
