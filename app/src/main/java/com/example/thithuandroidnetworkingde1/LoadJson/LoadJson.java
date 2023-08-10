package com.example.thithuandroidnetworkingde1.LoadJson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.thithuandroidnetworkingde1.MainActivity;
import com.example.thithuandroidnetworkingde1.R;
import com.example.thithuandroidnetworkingde1.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoadJson extends AppCompatActivity {
    Button btnloadjson,btnchuyen;
    TextView tvkqjson;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_json);
        btnloadjson=findViewById(R.id.btnloadjson);
        btnchuyen=findViewById(R.id.btnchuyen);
        tvkqjson=findViewById(R.id.tvkqjson);
        btnloadjson.setOnClickListener((view)->{
            getjson();
        });
         btnchuyen.setOnClickListener((view)->{
             Intent intent=new Intent(context, MainActivity.class);
                startActivity(intent);

         });
    }
    String strKQ="";
    private void getjson() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = config.loadjson;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    strKQ = "";
                    try {
                        JSONArray productsArray = response.getJSONArray("products");

                        for (int i = 0; i < productsArray.length(); i++) {
                            JSONObject product = productsArray.getJSONObject(i);
                            String pid = product.getString("pid");
                            String name = product.getString("name");
                            String price = product.getString("price");
                            String created_at = product.getString("created_at");
                            String updated_at = product.getString("updated_at");

                            strKQ += String.format(
                                    "Product ID: %s\nName: %s\nPrice: %s\nCreated At: %s\nUpdated At: %s\n\n",
                                    pid, name, price, created_at, updated_at
                            );
                        }

                        tvkqjson.setText(strKQ);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    tvkqjson.setText(error.toString());
                    Log.d("eeeee", "onErrorResponse: " + error.toString());
                }
        );

        // Thêm request vào hàng đợi
        queue.add(request);
    }

}