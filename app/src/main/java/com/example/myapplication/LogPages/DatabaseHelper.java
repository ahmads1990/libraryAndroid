package com.example.myapplication.LogPages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int dbversion = 1;
    private static final String dbname = "libraryDataBase";

    private static final String TABLE_USER_NAME = "dbusers";
    //columns
    private static final String ID_COL = "id";
    private static final String USERNAME_COL = "username";
    private static final String PASSWORD_COL = "password";
    private static final String EMAIL_COL = "email";

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create user table
        String query = "CREATE TABLE " + TABLE_USER_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + EMAIL_COL + " TEXT)";

        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    public boolean addNewUser(String username, String password, String email) {
        //create instance of db
        SQLiteDatabase db = this.getWritableDatabase();

        //check username isn't repeated
        Cursor cursor = db.rawQuery(" SELECT " + USERNAME_COL
                        + " FROM " + TABLE_USER_NAME
                        + " WHERE " + USERNAME_COL + "= " + username
                , null);

        //if the name is repeated return error
        if (cursor.getCount() > 0) {
            return false;
        }

        // variable for content values.
        ContentValues values = new ContentValues();
        // along with its key and value pair.
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(EMAIL_COL, email);
        db.insert(TABLE_USER_NAME, null, values);

        db.close();
        return true;
    }

    public boolean checkLogIn(String username, String password) {
        //create instance of db
        SQLiteDatabase db = this.getReadableDatabase();

        //check username and password
        Cursor cursor = db.rawQuery(" SELECT " + USERNAME_COL + ", " + PASSWORD_COL
                        + " FROM " + TABLE_USER_NAME
                        + " WHERE " + USERNAME_COL + " = " + username
                        + " AND " + PASSWORD_COL + " = " + password
                , null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
