package com.ranjeet.sqliteapps.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.DatabaseHelper2;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper2 mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mydb=new DatabaseHelper2(this);
    }
}
