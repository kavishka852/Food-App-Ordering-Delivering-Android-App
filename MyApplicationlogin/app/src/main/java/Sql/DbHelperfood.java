package Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplicationlogin.Model.OrdersModel;

import java.util.ArrayList;

public class DbHelperfood extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 4;

    public DbHelperfood(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE orders(" + "_itemid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," + "phone TEXT," + "address TEXT," + "branch TEXT," +
                "price INTEGER," + "image INTEGER," + "foodname TEXT," + "description TEXT," + "quantity INTERGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders ");
        onCreate(db);
    }

//    public boolean insertOrder(String name, String phone, String address, String branch, int price, int image, String qty, String desc, int foodName){
//        SQLiteDatabase database = getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("phone", phone);
//        values.put("address", address);
//        values.put("branch", branch);
//        values.put("price", price);
//        values.put("image", image);
//        values.put("quantity", qty);
//        values.put("description", desc);
//        values.put("foodnme", foodName);
//        long id = database.insert("orders",null, values);
//        if (id <= 0){
//            return false;
//        }
//        else{
//            return true;
//        }
//
//    }

    public Boolean insetorders(String name, String phone, String address, String branch, int price, int image, String foodName, String desc ,int  qty) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("branch", branch);
        contentValues.put("price", price);
        contentValues.put("image", image);
        contentValues.put("foodname", foodName);
        contentValues.put("description", desc);
        contentValues.put("quantity", qty);
        long id = DB.insert("orders", null, contentValues);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrdersModels() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select _itemid,foodname,image,price from orders", null);

            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) + " ");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+ " ");
                orders.add(model);

            }


        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int _itemid){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where _itemid="+ _itemid, null);

        if(cursor != null)
            cursor.moveToFirst();



        return cursor;
    }

    public Boolean updateOrder(String name, String phone, String address, String branch, int price, int image, String foodName, String desc ,int  qty,int _itemid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("branch", branch);
        contentValues.put("price", price);
        contentValues.put("image", image);
        contentValues.put("foodname", foodName);
        contentValues.put("description", desc);
        contentValues.put("quantity", qty);
        long row = DB.update("orders",contentValues,"_itemid="+_itemid,null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int deleteOreder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","_itemid="+id,null);
    }
}