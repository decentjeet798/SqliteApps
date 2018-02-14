package com.ranjeet.sqliteapps;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ranjeet.sqliteapps.activities.MainActivity8;
import com.ranjeet.sqliteapps.adapter.Food_list_Adapter8;
import com.ranjeet.sqliteapps.pojo.Jobseeker;

import java.util.ArrayList;

/**
 * Created by admin on 12/22/2017.
 */

public class JobseekersList extends AppCompatActivity {
    ListView gridView;
    ArrayList<Jobseeker>  list;
    Food_list_Adapter8 adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity8);
        gridView= (ListView) findViewById(R.id.grid);
        list=new ArrayList();

        adapter=new Food_list_Adapter8(this,R.layout.jobseekers_items8,list);
        gridView.setAdapter(adapter);
        //get all data from sqlite database
        Cursor cursor= MainActivity8.mydb.getData("SELECT * FROM SEEKER");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String email=cursor.getString(2);
            String phone=cursor.getString(3);
            byte[] image=cursor.getBlob(4);
            list.add(new Jobseeker(id,name,email,phone,image));
        }
        adapter.notifyDataSetChanged();

    }
}
