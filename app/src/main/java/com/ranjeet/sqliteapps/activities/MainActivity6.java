package com.ranjeet.sqliteapps.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.ranjeet.sqliteapps.adapter.MyAdapter6;
import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.DatabaseHelper6;
import com.ranjeet.sqliteapps.pojo.Employee;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {
    GridView gridView;
    ArrayList<Employee> employeeList;
    MyAdapter6 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        gridView = (GridView) findViewById(R.id.gv_emp);

        DatabaseHelper6 databaseHelper = new DatabaseHelper6(MainActivity6.this);
        employeeList = new ArrayList<Employee>();
        employeeList = databaseHelper.getAllEmployee();
        adapter = new MyAdapter6(getApplicationContext(), employeeList);
        gridView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
