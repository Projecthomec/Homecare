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

public class CustomerRegActivity extends AppCompatActivity {

    EditText nameET;
    EditText numberET;
    EditText addressET;
    EditText cityET;
    EditText stateET;
    EditText pincodeET;
    EditText emailET;
    EditText passwordET;
    Button registerBT;

    private GlobalPreference globalPreference;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIp();

        Toast.makeText(CustomerRegActivity.this,""+ip,Toast.LENGTH_SHORT).show();

        nameET = findViewById(R.id.nameEditText);
        numberET = findViewById(R.id.numberEditText);
        addressET = findViewById(R.id.addressEditText);
        cityET = findViewById(R.id.cityEdiText);
        stateET = findViewById(R.id.stateEditText);
        pincodeET = findViewById(R.id.pincodeEditText);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        registerBT = findViewById(R.id.registerButton);

        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameET.getText().toString();
                String number = numberET.getText().toString();
                String address = addressET.getText().toString();
                String city= cityET.getText().toString();
                String state = stateET.getText().toString();
                String pin = pincodeET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                register(name,number,address,city,state,pin,email,password);
            }
        });
    }



    private void register(String name, String number, String address, String city, String state, String pin, String email, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/home_care/api/customerRegister.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")){
                    Intent intent = new Intent(CustomerRegActivity.this,CustomerLoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CustomerRegActivity.this,""+response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CustomerRegActivity.this,""+error,Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("number",number);
                params.put("address",address);
                params.put("city",city);
                params.put("state",state);
                params.put("pin",pin);
                params.put("email",email);
                params.put("password",password);
                return params;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(CustomerRegActivity.this);
        requestQueue.add(stringRequest);
    }

}