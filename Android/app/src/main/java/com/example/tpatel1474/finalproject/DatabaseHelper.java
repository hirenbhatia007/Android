package com.example.tpatel1474.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tpatel1474 on 12/10/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper  {
    public static final String DATABASE_NAME ="food.db";
    public static final String TABLE_NAME ="Food_data";
    public static final String COL_1 ="Id";
    public static final String COL_2 ="Name";
    public static final String COL_3 ="Cuisine";
    public static final String COL_4 ="Price";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (Id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CUISINE TEXT,PRICE TEXT) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name,String Cuisine,String Price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Cuisine);
        contentValues.put(COL_4,Price);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String Id,String Name,String Cuisine,String Price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Id);
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Cuisine);
        contentValues.put(COL_4,Price);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { Id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
