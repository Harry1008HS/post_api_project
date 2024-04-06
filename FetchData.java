package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchData extends AppCompatActivity {
Button button;
TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);

        button = findViewById(R.id.fetchData);
        textView=findViewById(R.id.textv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue= Volley.newRequestQueue(FetchData.this);
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest("https://durable-aids.000webhostapp.com/home.php",
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray= null;
                        try {
                            jsonArray = response.getJSONArray("userInfo");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(int i=0;i<=jsonArray.length();i++)
                        {
                            try {
                                JSONObject object=jsonArray.getJSONObject(i);
                                String nem=object.getString("user_name");
                                String email=object.getString("user_email");
                                String phone=object.getString("user_phone");
                                String pswd=object.getString("user_password");
                               textView.append(nem+"\n"+email+"\n"+phone+"\n"+pswd);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FetchData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}