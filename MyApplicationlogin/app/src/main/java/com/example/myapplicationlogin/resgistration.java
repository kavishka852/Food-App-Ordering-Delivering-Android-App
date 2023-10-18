package com.example.myapplicationlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Sql.DBHelper;

public class resgistration extends AppCompatActivity {

    EditText name,email,pass;
    Button signUpAcc;
    TextView login;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resgistration);
        name=findViewById(R.id.txtName);
        email=findViewById(R.id.editTextEmail);
        pass=findViewById(R.id.editRegPassword);
        signUpAcc = findViewById(R.id.btnReg);
        dbHelper = new DBHelper(this);
        signUpAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();
                boolean b =dbHelper.insetUserData(name1,email1,pass1);
                if (b){
                    Toast.makeText(resgistration.this,"Data inserted",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(resgistration.this,Login.class);
                    startActivity(i);
                }else {
                    Toast.makeText(resgistration.this,"Failed To insert Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
        login=findViewById(R.id.lnkLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(resgistration.this,Login.class);
                startActivity(i);
            }
        });
    }
    }
