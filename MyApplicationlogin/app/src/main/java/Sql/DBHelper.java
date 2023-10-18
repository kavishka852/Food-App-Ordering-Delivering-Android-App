package Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context ) {
        super(context,"UserData",null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(userID TEXT primary key,name TEXT,password PASSWORD)");
        DB.execSQL("CREATE TABLE ITEM("+"_itemid INTEGER PRIMARY KEY AUTOINCREMENT,"+"item_name TEXT,"+"price INTEGER,"+"discription TEXT)");

        addItemRecord(DB,"Bread Sandwich",400,"dis1");
        addItemRecord(DB,"Cheese Sandwich",350,"dis2");
        addItemRecord(DB,"Grilled Sandwich",500,"dis5");
        addItemRecord(DB,"Ham Sandwich",700,"dis6");
        addItemRecord(DB,"Ice cream Sandwich",500,"dis7");
        addItemRecord(DB,"Meat Ball Sandwich",600,"dis8");
        addItemRecord(DB,"Olive Sandwich",800,"dis9");
        addItemRecord(DB,"Prawn Sandwich",700,"dis10");
        addItemRecord(DB,"Vegetable Sandwich",400,"dis4");
        addItemRecord(DB,"Salman Sandwich",500,"dis5");
        addItemRecord(DB,"Tuna Sandwich",700,"dis6");
        addItemRecord(DB,"Beef Sandwich",500,"dis7");
        addItemRecord(DB,"Nutella Sandwich",500,"dis7");
        addItemRecord(DB,"Oreo Ice cream Sandwich",500,"dis7");
    }

    public Boolean insetUserData(String name,String email,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID",email);
        contentValues.put("name",name);
        contentValues.put("password",password);

        long result= DB.insert("UserDetails",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails ",null);
        return cursor;
    }

    private void addItemRecord(SQLiteDatabase db,String name,int price,String dis){
        ContentValues contentValues = new ContentValues();
        contentValues.put("item_name",name);
        contentValues.put("price",price);
        contentValues.put("discription",dis);
        db.insert("ITEM",null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
    }

}
