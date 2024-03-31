package com.example.apiproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
 EditText e1,e2,e3,e4;
 Button submit,nextpeg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        nextpeg=findViewById(R.id.nextpage);
        nextpeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FetchData.class));
            }
        });

        submit=findViewById(R.id.sumit);
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://durable-aids.000webhostapp.com/insert.php", new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 Toast.makeText(MainActivity.this, "get response"+response, Toast.LENGTH_SHORT).show();
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

                 Toast.makeText(MainActivity.this, "Error"+error, Toast.LENGTH_SHORT).show();
             }
         }){
             @Nullable
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                  super.getParams();
                  Map<String, String> map=new HashMap<>();
                 map.put("user_name",e1.getText().toString());
                 map.put("user_phone",e2.getText().toString());
                 map.put("user_email",e3.getText().toString());
                 map.put("user_password",e4.getText().toString());
                 return map;

             }
         };
         requestQueue.add(stringRequest);
            }
        });
    }
}
