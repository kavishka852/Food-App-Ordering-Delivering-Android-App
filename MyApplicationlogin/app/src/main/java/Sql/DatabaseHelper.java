package Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplicationlogin.Model.FoodItem;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food_database";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE food_items (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price TEXT, description TEXT, image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS food_items");
        onCreate(db);
    }

    public long insertFood(String name, String price, String description, byte[] image) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("description", description);
        values.put("image", image);

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert("food_items", null, values);
        db.close();

        return id;
    }

    public int updateFood(String name, String price, String description, byte[] image) {
        ContentValues values = new ContentValues();
        values.put("price", price);
        values.put("description", description);
        values.put("image", image);

        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.update("food_items", values, "name=?", new String[]{name});
        db.close();

        return rowsAffected;
    }

    public int deleteFood(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("food_items", "name=?", new String[]{name});
        db.close();

        return rowsAffected;
    }

    public FoodItem getFoodByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("food_items", null, "name=?", new String[]{name}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndex("id"));
            String foodName = cursor.getString(cursor.getColumnIndex("name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));

            cursor.close();
            db.close();

            return new FoodItem(id, foodName, price, description, image);
        }

        db.close();
        return null;
    }

}
