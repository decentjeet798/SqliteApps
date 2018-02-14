package com.ranjeet.sqliteapps.activities;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.DatabaseHelper1;

public class MainActivity1 extends AppCompatActivity {
    DatabaseHelper1 mydb;
    EditText editName, editgender, editdoj, editTextId, editTextage, editTextsalary;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        mydb = new DatabaseHelper1(this);

        init();
        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }


    void init() {
        //  geetting alll views Ids
        editTextId = (EditText) findViewById(R.id.etid);
        editName = (EditText) findViewById(R.id.etname);
        editgender = (EditText) findViewById(R.id.etgender);
        editdoj = (EditText) findViewById(R.id.etdoj);
        editTextage = (EditText) findViewById(R.id.etage);
        editTextsalary = (EditText) findViewById(R.id.etsalary);
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity1.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity1.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = mydb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(), editgender.getText().toString(), editdoj.getText().toString(), editTextage.getText().toString(),
                                editTextsalary.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity1.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity1.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(
                                editName.getText().toString(),
                                editgender.getText().toString(),
                                editdoj.getText().toString(),
                                editTextage.getText().toString(),
                                editTextsalary.getText().toString()
                        );
                        if (isInserted)
                            Toast.makeText(MainActivity1.this, "Data Inserted in Table", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity1.this, "Data not Inserted in Table", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Gender :" + res.getString(2) + "\n");
                            buffer.append("Doj :" + res.getString(3) + "\n");
                            buffer.append("Age :" + res.getString(4) + "\n");
                            buffer.append("Salary :" + res.getString(5) + "\n\n");
                        }

                        // Show all data
                        showMessage("All Datas", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.pdelete);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/


