
package com.example.myapplicationlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplicationlogin.databinding.ActivityDetailsBinding;

import Sql.DbHelperfood;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    DbHelperfood dbHelperfood;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelperfood = new DbHelperfood(this);

        if (getIntent().getIntExtra("type", 0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailimg.setImageResource(image);
            binding.detPrice.setText(String.format("%d", price));
            binding.foodname.setText(name);
            binding.detailsDes.setText(description);

            binding.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = dbHelperfood.insetorders(
                            binding.editName.getText().toString(),
                            binding.editPhone.getText().toString(),
                            binding.editAddress.getText().toString(),
                            binding.editBranch.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            quantity
                    );
                    if (isInserted)
                        Toast.makeText(DetailsActivity.this, "Data Success.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailsActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            });

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quantity++;
                    binding.qty.setText(String.valueOf(quantity));
                }
            });

            binding.min.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (quantity > 1) {
                        quantity--;
                        binding.qty.setText(String.valueOf(quantity));
                    }
                }
            });
        } else {
            int _itemid = getIntent().getIntExtra("_itemid", 0);
            Cursor cursor = dbHelperfood.getOrderById(_itemid);
            int image = cursor.getInt(6);

            binding.detailimg.setImageResource(image);
            binding.detPrice.setText(String.format("%d", cursor.getInt(5)));
            binding.foodname.setText(cursor.getString(7));
            binding.detailsDes.setText(cursor.getString(8));

            binding.editName.setText(cursor.getString(1));
            binding.editPhone.setText(cursor.getString(2));
            binding.editAddress.setText(cursor.getString(3));
            binding.editBranch.setText(cursor.getString(4));

            binding.orderBtn.setText("Update Now");
            binding.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = dbHelperfood.updateOrder(
                            binding.editName.getText().toString(),
                            binding.editPhone.getText().toString(),
                            binding.editAddress.getText().toString(),
                            binding.editBranch.getText().toString(),
                            Integer.parseInt(binding.detPrice.getText().toString()),
                            image,
                            binding.foodname.getText().toString(),
                            binding.detailsDes.getText().toString(),
                            1,
                            _itemid
                    );
                    if (isUpdated)
                        Toast.makeText(DetailsActivity.this, "Data Updated.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailsActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            });

            Button trackButton = findViewById(R.id.trackButton);
            trackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String customerAddress = binding.editAddress.getText().toString();
                    String branchAddress = binding.editBranch.getText().toString();

                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + branchAddress);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    } else {
                        Toast.makeText(DetailsActivity.this, "Google Maps not installed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
