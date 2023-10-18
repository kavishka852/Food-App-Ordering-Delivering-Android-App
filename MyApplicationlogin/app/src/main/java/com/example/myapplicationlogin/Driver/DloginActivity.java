package com.example.myapplicationlogin.Driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationlogin.OrdersActivity;
import com.example.myapplicationlogin.R;

import Sql.DriverDbHelper;

public class DloginActivity extends AppCompatActivity {
    EditText email1, password1;
    Button btnSubmit1;
    TextView createAcc;
    DriverDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlogin);
        dbHelper = new DriverDbHelper(this);
        email1 = findViewById(R.id.uDname);
        password1 = findViewById(R.id.udpwd);
        btnSubmit1 = findViewById(R.id.buttonL);

        btnSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = email1.getText().toString();
                String password = password1.getText().toString();

                if (checkLogin(username, password)) {
                    // Successful login, navigate to the admin dashboard or another activity
                    Intent intent = new Intent(DloginActivity.this, OrdersActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Failed login
                    Toast.makeText(DloginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkLogin(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id"};
        String selection = "username=? AND password=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("Driver", columns, selection, selectionArgs, null, null, null);

        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }
}