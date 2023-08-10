package com.example.thithuandroidnetworkingde1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btncau1,btncau2,btncau3;
    TextView tvkq;
    Context context=this;
    String strKQ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncau1=findViewById(R.id.btncau1);
        btncau2=findViewById(R.id.btncau2);
        btncau3=findViewById(R.id.btncau3);
        tvkq=findViewById(R.id.tvkq);
        btncau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(config.select1);
            }
        });
        btncau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(config.select2);
            }
        });
        btncau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(config.select3);
            }
        });
    }

    private void select(String url) {
        RequestQueue queue= Volley.newRequestQueue(context);

        JsonObjectRequest request=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String jsonResponse = response.toString();
                try {
                    JSONArray students = response.getJSONArray("students");
                    strKQ = "";

                    for (int i = 0; i < students.length(); i++) {
                        JSONObject student = students.getJSONObject(i);
                        String hocsinhid = student.getString("hocsinhid");
                        String lopid = student.getString("lopid");
                        String hoten = student.getString("hoten");
                        String ngaysinh = student.getString("ngaysinh");
                        String gioitinh = student.getString("gioitinh");
                        String diachi = student.getString("diachi");
                        String loptruong = student.getString("loptruong");
                        String bithuchidoan = student.getString("bithuchidoan");
                        String ghichu = student.getString("ghichu");

                        strKQ += "Học sinh ID: " + hocsinhid + "\n";
                        strKQ += "Lớp ID: " + lopid + "\n";
                        strKQ += "Họ tên: " + hoten + "\n";
                        strKQ += "Ngày sinh: " + ngaysinh + "\n";
                        strKQ += "Giới tính: " + gioitinh + "\n";
                        strKQ += "Địa chỉ: " + diachi + "\n";
                        strKQ += "Lớp trưởng: " + loptruong + "\n";
                        strKQ += "Bit hội đoàn: " + bithuchidoan + "\n";
                        strKQ += "Ghi chú: " + ghichu + "\n";
                        strKQ += "\n";
                    }
                    tvkq.setText(strKQ);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvkq.setText(error.getMessage());
            }
        });
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
}