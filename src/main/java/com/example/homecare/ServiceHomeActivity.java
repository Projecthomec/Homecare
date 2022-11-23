package com.example.homecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ServiceHomeActivity extends AppCompatActivity {

    Button profileBT;
    Button viewRequestBT;
    Button viewReviewBT;
    Button viewPaymentBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_home);

        profileBT = findViewById(R.id.profileButton);
        viewRequestBT = findViewById(R.id.viewRequestsButton);
        viewReviewBT = findViewById(R.id.viewReviewButton);
        viewPaymentBT = findViewById(R.id.viewPaymentButton);


        profileBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHomeActivity.this,ServiceActivity.class);
                intent.putExtra("function","sprofile");
                startActivity(intent);
            }
        });

        viewRequestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHomeActivity.this,ServiceActivity.class);
                intent.putExtra("function","viewRequest");
                startActivity(intent);
            }
        });

        viewPaymentBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHomeActivity.this,ServiceActivity.class);
                intent.putExtra("function","viewPayment");
                startActivity(intent);
            }
        });

        viewReviewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHomeActivity.this,ServiceActivity.class);
                intent.putExtra("function","viewReview");
                startActivity(intent);
            }
        });

    }

    /*------------Menu Code------------*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_home_new,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                Intent intent = new Intent(ServiceHomeActivity.this,MainActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}