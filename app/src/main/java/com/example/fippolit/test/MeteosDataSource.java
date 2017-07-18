package com.example.fippolit.test;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by fippolit on 17/07/2017.
 */

public class MeteosDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    public MeteosDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Meteo createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Meteo newMeteo = cursorToComment(cursor);
        cursor.close();
        return newMeteo;
    }

    public void deleteComment(Meteo meteo) {
        long id = meteo.getId();
        System.out.println("Meteo deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Meteo> getAllComments() {
        List<Meteo> meteos = new ArrayList<Meteo>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Meteo meteo = cursorToComment(cursor);
            meteos.add(meteo);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return meteos;
    }

    private Meteo cursorToComment(Cursor cursor) {
        Meteo meteo = new Meteo();
        meteo.setId(cursor.getLong(0));
        meteo.setComment(cursor.getString(1));
        return meteo;
    }
}
