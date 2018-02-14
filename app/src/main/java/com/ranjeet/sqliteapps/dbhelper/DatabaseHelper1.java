package com.ranjeet.sqliteapps.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper1 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Androidteam.db";
    public static final String TABLE_NAME = "Employe_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "GENDER";
    public static final String COL4 = "DOJ";
    public static final String COL5 = "AGE";
    public static final String COL6 = "SALARY";

    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + "(ID  INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,GENDER TEXT,DOJ INTEGER,AGE INTEGER,SALARY INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String gender, String doj, String age, String salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, gender);
        contentValues.put(COL4, doj);
        contentValues.put(COL5, age);
        contentValues.put(COL6, salary);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public boolean updateData(String id, String name, String gender, String doj, String age, String salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, name);
        contentValues.put(COL3, gender);
        contentValues.put(COL4, doj);
        contentValues.put(COL5, age);
        contentValues.put(COL6, salary);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}
