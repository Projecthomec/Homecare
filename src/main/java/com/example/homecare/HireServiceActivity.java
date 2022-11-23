package com.example.homecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HireServiceActivity extends AppCompatActivity {

    ImageView searchIV;
    ImageView plumberIV;
    ImageView electIV;
    ImageView carpenterIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_service);

        searchIV = findViewById(R.id.searchImageView);
        plumberIV = findViewById(R.id.plumberImageView);
        electIV = findViewById(R.id.electImageView);
        carpenterIV = findViewById(R.id.carpentrImageView);

        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireServiceActivity.this,WebActivity.class);
                intent.putExtra("function","hireServices");
                startActivity(intent);
            }
        });

        plumberIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireServiceActivity.this,WebActivity.class);
                intent.putExtra("function","hirePlumber");
                startActivity(intent);
            }
        });

        electIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireServiceActivity.this,WebActivity.class);
                intent.putExtra("function","hireElectrician");
                startActivity(intent);
            }
        });

        carpenterIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireServiceActivity.this,WebActivity.class);
                intent.putExtra("function","hireCarpenter");
                startActivity(intent);
            }
        });
    }
}