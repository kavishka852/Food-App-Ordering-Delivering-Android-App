//package com.example.myapplicationlogin.Admin;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import com.example.myapplicationlogin.R;
//import com.example.myapplicationlogin.databinding.ActivityOrdersBinding;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class AdOrdersActivity extends AppCompatActivity {
//    ActivityOrdersBinding binding;
//    BottomNavigationView bottomNavigationView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ad_orders);
//
//        bottomNavigationView=findViewById(R.id.bottom_navigator);
//        bottomNavigationView.setSelectedItemId(R.id.orders);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch(item.getItemId()){
//                    case R.id.dashbord:
//                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.orders:
//                        return true;
//                    case R.id.Manage:
//                        startActivity(new Intent(getApplicationContext(),AddFoodActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        });
//    }
//}

package com.example.myapplicationlogin.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplicationlogin.Adapters.OrdersAdapter;
import com.example.myapplicationlogin.Model.OrdersModel;
import com.example.myapplicationlogin.R;
import com.example.myapplicationlogin.databinding.ActivityOrdersBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import Sql.DbHelperfood;

public class AdOrdersActivity extends AppCompatActivity {
    ActivityOrdersBinding binding;
    BottomNavigationView bottomNavigationView;
    RecyclerView orderRecyclerview; // Declare RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_orders); // Set the correct layout file

        orderRecyclerview = findViewById(R.id.orderRecyclerview); // Find the RecyclerView by its id

        DbHelperfood helperfood = new DbHelperfood(this);
        ArrayList<OrdersModel> list = helperfood.getOrdersModels();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        orderRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        orderRecyclerview.setLayoutManager(layoutManager);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.orders);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashbord:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.orders:
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
