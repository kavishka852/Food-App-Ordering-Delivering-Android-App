package com.example.myapplicationlogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplicationlogin.Adapters.MainAdapter;
import com.example.myapplicationlogin.Model.MainModel;
import com.example.myapplicationlogin.databinding.ActivityFinalPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import Sql.DBHelper;

public class FinalPage extends AppCompatActivity {

    TextView text;

ActivityFinalPageBinding binding;




    @Override
    public void onBackPressed() {
        FinalPage.this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityFinalPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list= new ArrayList<>();

        list.add(new MainModel(R.drawable.burger1 ,"Chicken Burger",  "500", "Chicken Burger with Cheese"));
        list.add(new MainModel(R.drawable.burger2 ,"Chicken Burger",  "600", "Chicken Burger with Masala"));
        list.add(new MainModel(R.drawable.burger4 ,"Chicken Burger",  "700", "Chicken Burger with Coke"));
        list.add(new MainModel(R.drawable.sandwich1 ,"Chicken Sandwich",  "500", "Chicken Sandwich with extra moyonis "));
        list.add(new MainModel(R.drawable.sandwich2 ,"Chicken Sandwich with Cheese",  "600", "Chicken Sandwich with Coke"));
        list.add(new MainModel(R.drawable.pizza1 ,"Cheese Pizza",  "900", " Large Cheese Pizza "));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        //Login Email Shower
        text = findViewById(R.id.changeText);
        Intent intent = getIntent();
        String s2 = intent.getStringExtra("email");
        text.setText("Hi, Welcome"+" "+s2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(FinalPage.this, OrdersActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}