package com.ranjeet.sqliteapps.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ranjeet.sqliteapps.pojo.Product3;
import com.ranjeet.sqliteapps.adapter.ProductAdapter3;
import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.SqliteDatabase3;

import java.util.List;
// where is links ramani?
public class MainActivity3 extends AppCompatActivity {
    private static final String TAG = MainActivity3.class.getSimpleName();
    private SqliteDatabase3 mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);
        RecyclerView productView = (RecyclerView)findViewById(R.id.rec);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(linearLayoutManager);
        productView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase3(this);
        List<Product3> allProducts = mDatabase.listProducts();
        if(allProducts.size() > 0){
            productView.setVisibility(View.VISIBLE);
            ProductAdapter3 mAdapter = new ProductAdapter3(this, allProducts);
            productView.setAdapter(mAdapter);
        }else {
            productView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no product in the database. Start adding now", Toast.LENGTH_LONG).show();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new quick task
                addTaskDialog();
            }
        });
    }
    private void addTaskDialog(){


        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_product_layout3, null);

        // getting all views
         final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
         final EditText quantityField = (EditText)subView.findViewById(R.id.enter_quantity);
         final EditText priceField = (EditText)subView.findViewById(R.id.enter_price);

      // to create Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new products");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("ADD PRODUCT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                 String name = nameField.getText().toString();
                 int quantity = Integer.parseInt(quantityField.getText().toString());
                 int price = Integer.parseInt(priceField.getText().toString());

                //validation
               if(TextUtils.isEmpty(name) || quantity <= 0){
                    Toast.makeText(getApplicationContext(), "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    Product3 newProduct = new Product3(name, quantity ,price);
                    mDatabase.addProduct(newProduct);
                    //refresh the activity
                    finish();

                    startActivity(getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity3.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }
}
