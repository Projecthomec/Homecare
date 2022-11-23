package com.example.homecare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ServiceRegActivity extends AppCompatActivity {

    EditText nameET;
    EditText numberET;
    EditText addressET;
    EditText cityET;
    EditText stateET;
    EditText emailET;
    EditText passwordET;
    EditText serviceET;
    EditText experienceET;
    Button registerBT;

    private GlobalPreference globalPreference;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reg);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIp();

        nameET = findViewById(R.id.snameEditText);
        numberET = findViewById(R.id.snumberEditText);
        addressET = findViewById(R.id.saddressEditText);
        cityET = findViewById(R.id.scityEdiText);
        stateET = findViewById(R.id.sstateEditText);
        emailET = findViewById(R.id.semailEditText);
        passwordET = findViewById(R.id.spasswordEditText);
        serviceET = findViewById(R.id.sserviceEditText);
        experienceET = findViewById(R.id.sexperienceEditText);
        registerBT = findViewById(R.id.sregisterButton);

        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name2 = nameET.getText().toString();
                String number2 = numberET.getText().toString();
                String address2 = addressET.getText().toString();
                String city2 = cityET.getText().toString();
                String state2 = stateET.getText().toString();
                String email2 = emailET.getText().toString();
                String password2 = passwordET.getText().toString();
                String service2 = serviceET.getText().toString();
                String experience2 = experienceET.getText().toString();

                service_reg(name2, number2, address2, city2, state2, email2, password2, service2, experience2);
            }
        });
    }



    private void service_reg(String name2, String number2, String address2, String city2, String state2, String email2, String password2, String service2, String experience2) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/home_care/api/serviceRegister.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Intent intent = new Intent(ServiceRegActivity.this, UploadActivity.class);
                    intent.putExtra("email",email2);
                    startActivity(intent);

                } else {
                    Toast.makeText(ServiceRegActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ServiceRegActivity.this,""+error,Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name2);
                params.put("number",number2);
                params.put("address",address2);
                params.put("city",city2);
                params.put("state",state2);
                params.put("email",email2);
                params.put("password",password2);
                params.put("service",service2);
                params.put("experience",experience2);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ServiceRegActivity.this);
        requestQueue.add(stringRequest);
    }
}