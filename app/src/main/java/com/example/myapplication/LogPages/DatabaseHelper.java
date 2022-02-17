package com.example.myapplication.LogPages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Queue;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int dbversion = 3;
    private static final String dbname = "libraryDataBase";

    private static final String TABLE_NAME_USERS = "dbusers";
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
        Log.d("mytag", "chanegd");
        String query = "CREATE TABLE " + TABLE_NAME_USERS + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USERNAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + EMAIL_COL + " TEXT)";

        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    public boolean addNewUser(String username, String password, String email) {
        //create instance of db
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            //check username isn't repeated
            Cursor cursor = db.rawQuery("SELECT " + USERNAME_COL
                            + " FROM " + TABLE_NAME_USERS
                            + " WHERE " + USERNAME_COL + "= " + username
                    , null);

            //if the name is repeated return error
            if (cursor.getCount() > 0) {
                return false;
            }
        }
        catch (Exception e){}
        // variable for content values.
        ContentValues values = new ContentValues();
        // along with its key and value pair.
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(EMAIL_COL, email);
        db.insert(TABLE_NAME_USERS, null, values);

        db.close();
        return true;
    }

    public boolean checkLogIn(String username, String password) {
        //create instance of db
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "";
        try {


           query= "SELECT " + USERNAME_COL + ", " + PASSWORD_COL
                    + " FROM " + TABLE_NAME_USERS
                    + " WHERE " +USERNAME_COL + " = ? "
           + " and " + PASSWORD_COL + " = ? ";
            //check username and password
            Cursor cursor = db.rawQuery(query
                    , new String[]{username, password});



            Log.d("mytag", "text "+String.valueOf( cursor.getCount()));
            if (cursor.getCount() > 0) {
                return true;
            }
        }catch (Exception e){

            Log.d("mytag", query);
            Log.d("mytag", e.toString());
        }
        return false;
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);

        // Create tables again
        onCreate(db);
    }
}
