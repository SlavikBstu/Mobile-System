package com.example.apache.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by Apache on 05.03.2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "DataBase", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table MyDB (ID integer primary key autoincrement, Name text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists MyDB");
        onCreate(db);
    }

    public void insert(String text){
        ContentValues values = new ContentValues();
        values.put("Name", text);
        this.getWritableDatabase().insertOrThrow("MyDB", "", values);
    }

    public void list(TextView view){
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from MyDB", null);
        while (cursor.moveToNext()){
            view.append("ID: " + cursor.getString(0) + "; Name: " + cursor.getString(1) + "\n");
        }
    }
}
