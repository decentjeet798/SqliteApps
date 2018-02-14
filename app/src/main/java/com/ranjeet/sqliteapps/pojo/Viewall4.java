package com.ranjeet.sqliteapps.pojo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.adapter.RecycleAdapter4;
import com.ranjeet.sqliteapps.dbhelper.DatabaseHelper4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12/11/2017.
 */

public class Viewall4 extends Activity {
    Button show;
    DatabaseHelper4 database;
    RecyclerView recyclerView;
    RecycleAdapter4 recycler;
    List<DataModel4> datamodel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view4);
        show = (Button) findViewById(R.id.view);
        datamodel =new ArrayList<DataModel4>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DatabaseHelper4(Viewall4.this);
                datamodel=  database.getdata();
                recycler =new RecycleAdapter4(datamodel);


                Log.i("HIteshdata",""+datamodel);
                RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(reLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recycler);


            }
        });

    }
}
