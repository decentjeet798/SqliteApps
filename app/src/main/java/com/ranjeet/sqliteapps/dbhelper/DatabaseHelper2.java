package com.ranjeet.sqliteapps.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 12/7/2017.
 */

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static  final  String DATABASE_NAME="Androidteamss.db";
    public  static  final  String TABLE_NAME="Employes_table";
    public  static  final  String COL1="ID";
    public  static  final  String COL2="NAME";
    public  static  final  String COL3="GENDER";
    public  static  final  String COL4="DOJ";
    public  static  final  String COL5="AGE";
    public  static  final  String COL6="SALARY";


    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " +TABLE_NAME +"(ID  INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,GENDER TEXT,DOJ INTEGER,AGE INTEGER,SALARY INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);

    }
}
