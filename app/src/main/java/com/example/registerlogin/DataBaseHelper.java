package com.example.registerlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {

        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table register(username text,mail text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists register");
    }


    public boolean insert(String name,String mail,String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put("username",name);
        contentValues.put("mail",mail);
        contentValues.put("password",password);

        long result = sqLiteDatabase.insert("register",null,contentValues);

        if (result==-1) return false;
        else return true;
    }

    public Boolean checkmail(String mail)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery("select * from register where mail=?",new String[] {mail});

        if (cursor.getCount()>0) return false;
            else return true;
    }


    public Boolean login(String mail,String password)
    {
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from register where mail=? and password=?",new String[] {mail,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }


    public Cursor getdata()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        Cursor cursor= sqLiteDatabase.rawQuery("select * from register",null);
        return cursor;
    }
}
