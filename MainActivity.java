package com.example.apiproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
 EditText e1,e2;
 Button submit;
 TextView textView;
 private static final String url="";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        submit=findViewById(R.id.sumit);
        textView=findViewById(R.id.tv);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(e1.getText().toString(),
                        e2.getText().toString());
            }
        });
    }

   public void getData(final  String name, final String email) {
       StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               e1.setText("");
               e2.setText("");
                textView.setText(response);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               e1.setText("");
               e2.setText("");
               textView.setText(error.toString());
               textView.setTextColor(Color.RED);
           }
       }
       ){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String ,String > map=new HashMap<String,String>();
               map.put("name",name);
               map.put("email",email);
               return map;
           }
       };
       RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
       queue.add(stringRequest);
    }


}