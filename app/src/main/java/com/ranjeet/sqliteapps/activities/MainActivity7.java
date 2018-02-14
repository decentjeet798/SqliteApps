

package com.ranjeet.sqliteapps.activities;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.DatabaseHandler7;
import com.ranjeet.sqliteapps.pojo.Contact7;


public class MainActivity7 extends AppCompatActivity {
    DatabaseHandler7 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main7);



        final EditText name= (EditText) findViewById(R.id.name);
        final EditText phone= (EditText) findViewById(R.id.phone);
        Button button= (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHandler7(getApplicationContext());
               String n= name.getText().toString();
                String p=phone.getText().toString();
// Inserting Contacts
            /*    Log.d("Insert: ", "Inserting ..");
                db.addContact(new Contact7(n, p));
                db.addContact(new Contact7("Srinivas", "9199999999"));
                db.addContact(new Contact7("Tommy", "9522222222"));
                db.addContact(new Contact7("Karthik", "9533333333"));*/

                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Contact7> contacts = db.getAllContacts();
                for (Contact7 cn : contacts) {
                    String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " +cn.getPhoneNumber();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }

            }
        });

    }



}