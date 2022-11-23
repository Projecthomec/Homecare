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

public class CustomerHomeActivity extends AppCompatActivity {

    Button profileBT;
    Button hireBT;
    Button reviewBT;
    Button statusBT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        profileBT = findViewById(R.id.profileButton);
        hireBT = findViewById(R.id.bookButton);
        reviewBT = findViewById(R.id.reviewButton);
        statusBT = findViewById(R.id.statusButton);

        profileBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this,WebActivity.class);
                intent.putExtra("function","uprofile");
                startActivity(intent);
            }
        });



        hireBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this,HireServiceActivity.class);
                startActivity(intent);
            }
        });

        statusBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this,WebActivity.class);
                intent.putExtra("function","status");
                startActivity(intent);
            }
        });



        reviewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this,WebActivity.class);
                intent.putExtra("function","review");
                startActivity(intent);
            }
        });

    }

    /*------------Menu Code------------*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                Intent intent = new Intent(CustomerHomeActivity.this,MainActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}