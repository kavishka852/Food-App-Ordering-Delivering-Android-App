package com.example.myapplicationlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplicationlogin.Admin.AdminLoginActivity;
import com.example.myapplicationlogin.Driver.DloginActivity;

public class logindash extends AppCompatActivity {

    Button btnc,btnA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindash);

        btnc = findViewById(R.id.btn_c);
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logindash.this,Branches.class);
                startActivity(intent);
            }
        });

        btnA = findViewById(R.id.buttonA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logindash.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        btnc = findViewById(R.id.button10);
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logindash.this, DloginActivity.class);
                startActivity(intent);
            }
        });
    }
}