
package Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DriverDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DriverDB";
    private static final int DATABASE_VERSION = 1;

    // Create the driver table
    private static final String CREATE_DRIVER_TABLE = "CREATE TABLE IF NOT EXISTS Driver (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "password TEXT);";

    public DriverDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DRIVER_TABLE);

        // Insert a driver record if none exists
        String insertQuery = "INSERT OR IGNORE INTO Driver (username, password) VALUES ('driver', 'driverpassword');";
        db.execSQL(insertQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Driver");
        onCreate(db);
    }
}
