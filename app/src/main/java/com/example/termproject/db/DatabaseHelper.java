package com.example.termproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cord.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "cord";
    public static final String COL_ID = "_ID";
    public static final String COL_IMAGE = "image";
    public static final String COL_NAME = "name";

    private static final String SQL_CREATE_TABLE_CORD
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_NAME + " TEXT,"
            + COL_IMAGE + " TEXT "
            + ")";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_CORD);

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "C");
        cv.put(COL_IMAGE, "");
        db.insert(TABLE_NAME, null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "D");
        cv.put(COL_IMAGE, "");
        db.insert(TABLE_NAME, null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
