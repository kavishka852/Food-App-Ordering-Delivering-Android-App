package Sql;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "AdminDB";
    private static final int DATABASE_VERSION = 3;

    // Create the admin table
    private static final String CREATE_ADMIN_TABLE = "CREATE TABLE Admin (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "password TEXT);";

    public AdminDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADMIN_TABLE);

        // Insert an admin record
        db.execSQL("INSERT INTO Admin (username, password) VALUES ('admin', 'adminpassword');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Admin");
        onCreate(db);
    }
}
