package com.ranjeet.sqliteapps.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.DataHelper;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        // Create DataHelper object and insert some sample data
        DataHelper datahelper=new DataHelper(this);
        datahelper.insertProvince("Kandal");
        datahelper.insertProvince("Kep");
        datahelper.insertProvince("Koh Kong");
        datahelper.insertProvince("Takeo");
        // Get sample data from the database and display them in the spinner
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayList<String> list=datahelper.getAllProvinces();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout5, R.id.text, list);
        spinner.setAdapter(adapter);

    }
}
