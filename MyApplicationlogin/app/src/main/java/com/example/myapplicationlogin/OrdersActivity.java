package com.example.myapplicationlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.myapplicationlogin.Adapters.MainAdapter;
import com.example.myapplicationlogin.Adapters.OrdersAdapter;
import com.example.myapplicationlogin.Model.MainModel;
import com.example.myapplicationlogin.Model.OrdersModel;
import com.example.myapplicationlogin.databinding.ActivityFinalPageBinding;
import com.example.myapplicationlogin.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

import Sql.DbHelperfood;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        binding= ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        DbHelperfood helperfood = new DbHelperfood(this);
        ArrayList<OrdersModel> list = helperfood.getOrdersModels();


        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerview.setLayoutManager(layoutManager);
    }
}
