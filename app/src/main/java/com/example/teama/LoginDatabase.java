package com.example.teama;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class LoginDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Login.db";
    public static final String TABLE_NAME = "User_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Email";
    public static final String COL_3 = "password";
    public static final String COL_4 = "ID";



    public LoginDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (NAME TEXT, EMAIL TEXT, PASSWORD TEXT, ID INTEGER PRIMARY KEY AUTOINCREMENT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,password);
       long result = db.insert(TABLE_NAME,null,contentValues);
       if(result == -1) {
           return false;
       }else
        return true;
    }
}
