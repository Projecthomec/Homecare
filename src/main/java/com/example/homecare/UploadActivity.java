package com.example.homecare;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {

    private static String  TAG = "Upload Activity";

    TextView certificateTV;
    TextView idTV;
    Button file1BT;
    Button file2BT;
    Button submitBT;

    private String certificate,idproof;
    private GlobalPreference globalPreference;
    private String ip,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIp();

        email = getIntent().getStringExtra("email");

       // Toast.makeText(this, ""+ip, Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this, ""+email, Toast.LENGTH_SHORT).show();

        certificateTV = findViewById(R.id.file1TextView);
        idTV = findViewById(R.id.file2TextView);
        file1BT = findViewById(R.id.file1Button);
        file2BT = findViewById(R.id.file2Button);
        submitBT = findViewById(R.id.submitButton);

        file1BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Certificate"),100);
            }
        });

        file2BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Id Proof"),200);

            }
        });

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(UploadActivity.this,"button clicked",Toast.LENGTH_SHORT).show();

                uploading();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        Log.d(TAG,"inside api"+requestCode);

        if (resultCode == RESULT_OK){
            if (requestCode == 100){
                Uri filepath = data.getData();

                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte [] imageBytes = baos.toByteArray();
                    certificate = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    certificateTV.setText("certificate.jpg");

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            else if (requestCode == 200){
                Uri filepath = data.getData();

                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte [] imageBytes = baos.toByteArray();
                    idproof = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    idTV.setText("idproof.jpg");

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploading() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/home_care/api/supload.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Intent intent = new Intent(UploadActivity.this,ServiceLoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(UploadActivity.this, "failed" + response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UploadActivity.this,""+error,Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("certificate",certificate);
                params.put("idproof",idproof);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}