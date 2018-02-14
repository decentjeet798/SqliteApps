package com.ranjeet.sqliteapps.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ranjeet.sqliteapps.pojo.Product3;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase3 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "productdb";
    private static final String TABLE_PRODUCTS = "productsTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "productname";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";

    public SqliteDatabase3(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("Database","Database is Created ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // query to create table--->   Create Table student(id Integer,name Text,):
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME + " TEXT," + COLUMN_QUANTITY + " INTEGER" + COLUMN_PRICE + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        Log.i("Database","Database Table is created...");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropquery="DROP TABLE IF EXISTS " + TABLE_PRODUCTS;
        db.execSQL(dropquery);
        onCreate(db);
    }


    public List<Product3> listProducts() {
        String sql = "select * from " + TABLE_PRODUCTS;

        // open database
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product3> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int quantity = Integer.parseInt(cursor.getString(2));

                Product3 pp=   new Product3(id, name, quantity);
                arrayList.add(pp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }



    public void addProduct(Product3 product) {

        // in values only we kept all details
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        values.put(COLUMN_PRICE, product.getPrice());


        // update
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
    }



    public void updateProduct(Product3 product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_QUANTITY, product.getQuantity());

        // update
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PRODUCTS, values, COLUMN_ID + "    = ?", new String[]{String.valueOf(product.getId())});
    }


    public Product3 findProduct(String name) {
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " = " + name;
        SQLiteDatabase db = this.getWritableDatabase();
        Product3 mProduct=null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(0));
            String productName = cursor.getString(1);
            int productQuantity = Integer.parseInt(cursor.getString(2));
            mProduct = new Product3(id, productName, productQuantity);
        }
        cursor.close();
        return mProduct;
    }


    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

    }
}
