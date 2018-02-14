package com.ranjeet.sqliteapps.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ranjeet.sqliteapps.pojo.DataModel4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12/11/2017.
 */

public class DatabaseHelper4 extends SQLiteOpenHelper {
    public static String DATABASE = "database.db";
    public static String TABLE ="mytable";
    public static String NAME ="name";
    public static String COMPANY ="company";
    public static String CITY ="city";
    public static String COUNTRY ="country";
    String br;
    public DatabaseHelper4(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";
        br = "CREATE TABLE "+TABLE+"("+NAME+ " Text, "+COMPANY+ " Text, "+CITY+ " Text, "+COUNTRY+ " Text);";
        db.execSQL(br);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");

    }
    public void insertdata(String name,String company ,String city,String country){
        System.out.print("Hello "+br);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(COMPANY, company);
        contentValues.put(CITY,city);
        contentValues.put(COUNTRY,country);
        db.insert(TABLE,null,contentValues);
    }
    public List<DataModel4> getdata(){
        // DataModel4 dataModel4 = new DataModel4();
        List<DataModel4> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel4 dataModel4 = null;
        while (cursor.moveToNext()) {
            dataModel4 = new DataModel4();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
            String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
            String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
            dataModel4.setName(name);
            dataModel4.setCity(city);
            dataModel4.setCounty(country);
            dataModel4.setCompany(company);
            stringBuffer.append(dataModel4);
            // stringBuffer.append(dataModel4);
            data.add(dataModel4);
        }

        for (DataModel4 mo:data ) {

            Log.i("Hellomo",""+mo.getCity());
        }

        //

        return data;
    }

}
