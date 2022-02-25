package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DBNAME= "Login.db";
    public DbHelper(Context context) {
        super(context, "Login.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key,email TEXT,password TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists users");
    }
    public Boolean insertdata(String username,String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = sqLiteDatabase.insert("users",null,contentValues);
        if(result ==-1) return  false;
        else
            return true;
    }
//    public ArrayList<String> readuser(){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor usercursor = sqLiteDatabase.rawQuery("select * from"+ users "where ")
//    }

    public Boolean checkusername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where username = ?",new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }
        else return false;
    }
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else return false;
    }
}
