
package com.alibaba.uilearning.activity.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBlite extends SQLiteOpenHelper {
    public DBlite(Context context) {
        super(context, RuiXin.DBNAME, null, RuiXin.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RuiXin.TNAME + "(" +
                RuiXin.TID + " integer primary key autoincrement not null," +
                RuiXin.EMAIL + " text not null," +
                RuiXin.USERNAME + " text not null," +
                RuiXin.DATE + " interger not null," +
                RuiXin.SEX + " text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void add(String email, String username, String date, String sex) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RuiXin.EMAIL, email);
        values.put(RuiXin.USERNAME, username);
        values.put(RuiXin.DATE, date);
        values.put(RuiXin.SEX, sex);
        db.insert(RuiXin.TNAME, "", values);
    }
}
