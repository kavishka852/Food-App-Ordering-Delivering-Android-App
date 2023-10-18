package com.example.myapplicationlogin.Admin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationlogin.R;

import Sql.AdminDbHelper;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private AdminDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        dbHelper = new AdminDbHelper(this);
        usernameEditText = findViewById(R.id.uDname);
        passwordEditText = findViewById(R.id.udpwd);
        loginButton = findViewById(R.id.buttonL);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (checkLogin(username, password)) {
                    // Successful login, navigate to the admin dashboard or another activity
                    Intent intent = new Intent(AdminLoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Failed login
                    Toast.makeText(AdminLoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkLogin(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id"};
        String selection = "username=? AND password=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("Admin", columns, selection, selectionArgs, null, null, null);

        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }
}
