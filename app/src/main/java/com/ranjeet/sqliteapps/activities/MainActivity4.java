package com.ranjeet.sqliteapps.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.pojo.Viewall4;
import com.ranjeet.sqliteapps.dbhelper.DatabaseHelper4;

public class MainActivity4 extends AppCompatActivity {
    Button submit,show;
    DatabaseHelper4 databaseHelper4;
    EditText etname,etemail,etcompany,etnumber;
    String name,city,company,country ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        etname = (EditText) findViewById(R.id.etname);
        etemail = (EditText) findViewById(R.id.etemail);
        etcompany = (EditText) findViewById(R.id.etcompany);
        etnumber = (EditText) findViewById(R.id.etphone);
        submit = (Button) findViewById(R.id.submit);
      ///  submit = (Button) findViewById(R.id.submit);
        show= (Button) findViewById(R.id.show);
        databaseHelper4 = new DatabaseHelper4(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etname.getText().toString();
                city = etemail.getText().toString();
                company=etcompany.getText().toString();
                country = etnumber.getText().toString();
                //Toast.makeText(MainActivity4.this,name, Toast.LENGTH_SHORT).show();
                if (name.isEmpty() && city.isEmpty()&& company.isEmpty()&& country.isEmpty()){

                    Toast.makeText(MainActivity4.this, "please fill details", Toast.LENGTH_SHORT).show();
                }else {

                    databaseHelper4.insertdata(name,company,city,country);

                   // clear inserted datas
                    etname.setText("");
                    etemail.setText("");
                    etcompany.setText("");
                    etnumber.setText("");

                    Intent intent = new Intent(MainActivity4.this,Viewall4.class);
                    startActivity(intent);

                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this,Viewall4.class);
                startActivity(intent);
            }
        });
    }
}
