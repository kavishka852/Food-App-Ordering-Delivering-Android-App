//package com.example.myapplicationlogin.Admin;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.myapplicationlogin.Model.FoodItem;
//import com.example.myapplicationlogin.R;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import java.io.ByteArrayOutputStream;
//
//import Sql.DatabaseHelper;
//
//public class AddFoodActivity extends AppCompatActivity {
//    BottomNavigationView bottomNavigationView;
//    private Button captureImageButton, loadButton;
//    private ImageView imageView;
//    private DatabaseHelper databaseHelper;
//    private EditText foodNameEditText, foodPriceEditText, foodDescriptionEditText, searchFoodEditText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_food);
//
//        captureImageButton = findViewById(R.id.captureButton);
//        loadButton = findViewById(R.id.showButton);
//        imageView = findViewById(R.id.imageView7);
//        databaseHelper = new DatabaseHelper(this);
//        foodNameEditText = findViewById(R.id.foodNameEditText);
//        foodPriceEditText = findViewById(R.id.foodPriceEditText);
//        foodDescriptionEditText = findViewById(R.id.foodDescriptionEditText);
//        searchFoodEditText = findViewById(R.id.foodNameEditText);
//
//        captureImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Check if the CAMERA permission is granted
//                if (ContextCompat.checkSelfPermission(AddFoodActivity.this, android.Manifest.permission.CAMERA)
//                        != android.content.pm.PackageManager.PERMISSION_GRANTED) {
//                    // If permission is not granted, request it
//                    ActivityCompat.requestPermissions(AddFoodActivity.this,
//                            new String[]{android.Manifest.permission.CAMERA}, 0);
//                } else {
//                    // Permission is already granted, start the camera intent
//                    startCameraIntent();
//                }
//            }
//        });
//
//        loadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String foodName = searchFoodEditText.getText().toString().trim();
//                if (!foodName.isEmpty()) {
//                    loadFoodData(foodName);
//                } else {
//                    Toast.makeText(AddFoodActivity.this, "Please enter a food name to search", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        // Add your code to save food data to the database when the "Add Food Item" button is clicked
//        Button addButton = findViewById(R.id.addButton3);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveFoodData();
//            }
//        });
//
//        // Add your code to update food data when the "Update Food" button is clicked
//        Button updateButton = findViewById(R.id.updateButton2);
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateFoodData();
//            }
//        });
//
//        // Add your code to delete food data when the "Delete Food" button is clicked
//        Button deleteButton = findViewById(R.id.deleteButton4);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteFood();
//            }
//        });
//
//        bottomNavigationView = findViewById(R.id.bottom_navigator);
//        bottomNavigationView.setSelectedItemId(R.id.Manage);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.dashbord:
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.orders:
//                        startActivity(new Intent(getApplicationContext(), AdOrdersActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.Manage:
//                        return true;
//                }
//                return false;
//            }
//        });
//    }
//
//    private void startCameraIntent() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 0);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(bitmap);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 0) {
//            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, start the camera intent
//                startCameraIntent();
//            } else {
//                // Permission denied, handle this case (e.g., show a message to the user)
//                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void saveFoodData() {
//        String foodName = foodNameEditText.getText().toString().trim();
//        String foodPrice = foodPriceEditText.getText().toString().trim();
//        String foodDescription = foodDescriptionEditText.getText().toString().trim();
//
//        // Check if all fields are filled
//        if (!foodName.isEmpty() && !foodPrice.isEmpty() && !foodDescription.isEmpty()) {
//            // Convert ImageView to byte[]
//            byte[] imageBytes = convertImageViewToByteArray(imageView);
//
//            // Save the image and food data to the database
//            long imageId = databaseHelper.insertFood(foodName, foodPrice, foodDescription, imageBytes);
//
//            // Store the image id in the shared preferences
//            SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//            sharedPreferences.edit().putLong("image_id", imageId).apply();
//
//            // Clear input fields and image view
//            foodNameEditText.getText().clear();
//            foodPriceEditText.getText().clear();
//            foodDescriptionEditText.getText().clear();
//            imageView.setImageResource(0); // Clear the ImageView
//        } else {
//            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void updateFoodData() {
//        String foodName = foodNameEditText.getText().toString().trim();
//        String foodPrice = foodPriceEditText.getText().toString().trim();
//        String foodDescription = foodDescriptionEditText.getText().toString().trim();
//
//        // Check if all fields are filled
//        if (!foodName.isEmpty() && !foodPrice.isEmpty() && !foodDescription.isEmpty()) {
//            // Convert ImageView to byte[]
//            byte[] imageBytes = convertImageViewToByteArray(imageView);
//
//            // Update the food data in the database
//            int rowsAffected = databaseHelper.updateFood(foodName, foodPrice, foodDescription, imageBytes);
//
//            if (rowsAffected > 0) {
//                Toast.makeText(this, "Food updated successfully", Toast.LENGTH_SHORT).show();
//                // Clear input fields and image view
//                foodNameEditText.getText().clear();
//                foodPriceEditText.getText().clear();
//                foodDescriptionEditText.getText().clear();
//                imageView.setImageResource(0); // Clear the ImageView
//            } else {
//                Toast.makeText(this, "Failed to update food", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void deleteFood() {
//        String foodName = foodNameEditText.getText().toString().trim();
//
//        // Check if the food name is not empty
//        if (!foodName.isEmpty()) {
//            // Delete the food data from the database
//            int rowsAffected = databaseHelper.deleteFood(foodName);
//
//            if (rowsAffected > 0) {
//                Toast.makeText(this, "Food deleted successfully", Toast.LENGTH_SHORT).show();
//                // Clear input fields and image view
//                foodNameEditText.getText().clear();
//                foodPriceEditText.getText().clear();
//                foodDescriptionEditText.getText().clear();
//                imageView.setImageResource(0); // Clear the ImageView
//            } else {
//                Toast.makeText(this, "Failed to delete food", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "Please enter a food name", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void loadFoodData(String foodName) {
//        // Retrieve food data from the database and populate the EditText and ImageView
//        FoodItem foodItem = databaseHelper.getFoodByName(foodName);
//
//        if (foodItem != null) {
//            foodNameEditText.setText(foodItem.getName());
//            foodPriceEditText.setText(foodItem.getPrice());
//            foodDescriptionEditText.setText(foodItem.getDescription());
//            byte[] imageBytes = foodItem.getImage();
//            if (imageBytes != null && imageBytes.length > 0) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                imageView.setImageBitmap(bitmap);
//            } else {
//                imageView.setImageResource(0);
//            }
//        } else {
//            Toast.makeText(this, "Food not found", Toast.LENGTH_SHORT).show();
//            // Clear input fields and image view
//            foodNameEditText.getText().clear();
//            foodPriceEditText.getText().clear();
//            foodDescriptionEditText.getText().clear();
//            imageView.setImageResource(0); // Clear the ImageView
//        }
//    }
//
//    private byte[] convertImageViewToByteArray(ImageView imageView) {
//        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//        return outputStream.toByteArray();
//    }
//}
package com.example.myapplicationlogin.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplicationlogin.Model.FoodItem;
import com.example.myapplicationlogin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import Sql.DatabaseHelper;

public class AddFoodActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private Button captureImageButton, loadButton, pickImageButton, addButton, deleteButton, updateButton;
    private ImageView imageView;
    private DatabaseHelper databaseHelper;
    private EditText foodNameEditText, foodPriceEditText, foodDescriptionEditText, searchFoodEditText;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        captureImageButton = findViewById(R.id.captureButton);
        loadButton = findViewById(R.id.showButton);
        pickImageButton = findViewById(R.id.pickImageButton);
        addButton = findViewById(R.id.addButton3);
        deleteButton = findViewById(R.id.deleteButton4);
        updateButton = findViewById(R.id.updateButton2);
        imageView = findViewById(R.id.imageView7);
        databaseHelper = new DatabaseHelper(this);
        foodNameEditText = findViewById(R.id.foodNameEditText);
        foodPriceEditText = findViewById(R.id.foodPriceEditText);
        foodDescriptionEditText = findViewById(R.id.foodDescriptionEditText);
        searchFoodEditText = findViewById(R.id.foodNameEditText);

        captureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the CAMERA permission is granted
                if (ContextCompat.checkSelfPermission(AddFoodActivity.this, android.Manifest.permission.CAMERA)
                        != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    // If permission is not granted, request it
                    ActivityCompat.requestPermissions(AddFoodActivity.this,
                            new String[]{android.Manifest.permission.CAMERA}, 0);
                } else {
                    // Permission is already granted, start the camera intent
                    startCameraIntent();
                }
            }
        });

        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = searchFoodEditText.getText().toString().trim();
                if (!foodName.isEmpty()) {
                    loadFoodData(foodName);
                } else {
                    Toast.makeText(AddFoodActivity.this, "Please enter a food name to search", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFoodData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFood();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFood();
            }
        });

        // Other initialization code...
    }

    private void startCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    private void saveFoodData() {
        String foodName = foodNameEditText.getText().toString().trim();
        String foodPrice = foodPriceEditText.getText().toString().trim();
        String foodDescription = foodDescriptionEditText.getText().toString().trim();

        if (!foodName.isEmpty() && !foodPrice.isEmpty() && !foodDescription.isEmpty()) {
            byte[] imageBytes = convertImageViewToByteArray(imageView);

            long imageId = databaseHelper.insertFood(foodName, foodPrice, foodDescription, imageBytes);

            SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
            sharedPreferences.edit().putLong("image_id", imageId).apply();

            foodNameEditText.getText().clear();
            foodPriceEditText.getText().clear();
            foodDescriptionEditText.getText().clear();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteFood() {
        String foodName = foodNameEditText.getText().toString().trim();

        if (!foodName.isEmpty()) {
            int rowsAffected = databaseHelper.deleteFood(foodName);

            if (rowsAffected > 0) {
                Toast.makeText(this, "Food deleted successfully", Toast.LENGTH_SHORT).show();
                foodNameEditText.getText().clear();
                foodPriceEditText.getText().clear();
                foodDescriptionEditText.getText().clear();
                imageView.setImageResource(0);
            } else {
                Toast.makeText(this, "Failed to delete food", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a food name", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateFood() {
        String foodName = foodNameEditText.getText().toString().trim();
        String foodPrice = foodPriceEditText.getText().toString().trim();
        String foodDescription = foodDescriptionEditText.getText().toString().trim();

        if (!foodName.isEmpty() && !foodPrice.isEmpty() && !foodDescription.isEmpty()) {
            byte[] imageBytes = convertImageViewToByteArray(imageView);

            int rowsAffected = databaseHelper.updateFood(foodName, foodPrice, foodDescription, imageBytes);

            if (rowsAffected > 0) {
                Toast.makeText(this, "Food updated successfully", Toast.LENGTH_SHORT).show();
                foodNameEditText.getText().clear();
                foodPriceEditText.getText().clear();
                foodDescriptionEditText.getText().clear();
                imageView.setImageResource(0);
            } else {
                Toast.makeText(this, "Failed to update food", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFoodData(String foodName) {
        FoodItem foodItem = databaseHelper.getFoodByName(foodName);

        if (foodItem != null) {
            foodNameEditText.setText(foodItem.getName());
            foodPriceEditText.setText(foodItem.getPrice());
            foodDescriptionEditText.setText(foodItem.getDescription());
            byte[] imageBytes = foodItem.getImage();
            if (imageBytes != null && imageBytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(0);
            }
        } else {
            Toast.makeText(this, "Food not found", Toast.LENGTH_SHORT).show();
            foodNameEditText.getText().clear();
            foodPriceEditText.getText().clear();
            foodDescriptionEditText.getText().clear();
            imageView.setImageResource(0);
        }
    }

    private byte[] convertImageViewToByteArray(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }

    // Other methods...
}
