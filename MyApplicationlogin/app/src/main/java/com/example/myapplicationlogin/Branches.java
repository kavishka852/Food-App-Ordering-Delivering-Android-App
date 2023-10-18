package com.example.myapplicationlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Sql.DBHelper;

public class Branches extends AppCompatActivity {

    Button login,Reg;
    Toolbar toolbar;
    DBHelper dbHelper;

    @Override
    public void onBackPressed() {
        Branches.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);

        dbHelper = new DBHelper(this);
        login =(Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Branches.this,Login.class);
                startActivity(intent);
            }
        });
        Reg = findViewById(R.id.btnSignup);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Branches.this,resgistration.class);
                startActivity(intent);
            }
        });
    }
}