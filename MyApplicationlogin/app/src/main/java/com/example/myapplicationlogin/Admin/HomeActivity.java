
package com.example.myapplicationlogin.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView; // Import RecyclerView

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplicationlogin.Adapters.MainAdapter;
import com.example.myapplicationlogin.Model.MainModel;
import com.example.myapplicationlogin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView; // Declare RecyclerView
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Set the correct layout file

        recyclerView = findViewById(R.id.recyclerView); // Find the RecyclerView by its id

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger1 ,"Chicken Burger",  "500", "Chicken Burger with Cheese"));
        list.add(new MainModel(R.drawable.burger2 ,"Chicken Burger",  "600", "Chicken Burger with Masala"));
        list.add(new MainModel(R.drawable.burger4 ,"Chicken Burger",  "700", "Chicken Burger with Coke"));
        list.add(new MainModel(R.drawable.sandwich1 ,"Chicken Sandwich",  "500", "Chicken Sandwich with extra moyonis "));
        list.add(new MainModel(R.drawable.sandwich2 ,"Chicken Sandwich with Cheese",  "600", "Chicken Sandwich with Coke"));
        list.add(new MainModel(R.drawable.pizza1 ,"Cheese Pizza",  "900", " Large Cheese Pizza "));

        MainAdapter adapter = new MainAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.dashbord);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashbord:
                        return true;
                    case R.id.orders:
                        startActivity(new Intent(getApplicationContext(), AdOrdersActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Manage:
                        startActivity(new Intent(getApplicationContext(), AddFoodActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}
