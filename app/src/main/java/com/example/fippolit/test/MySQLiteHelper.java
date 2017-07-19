package com.example.fippolit.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by fippolit on 17/07/2017.
 *
 * This class is responsible for creating the database.
 * The onUpgrade() method will simply delete all existing data and re-create the table.
 * It also defines several constants for the table name and the table columns.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "meteostats";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";

    private static final String DATABASE_NAME = "meteo.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_DATE
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}
