package com.ranjeet.sqliteapps.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ranjeet.sqliteapps.pojo.Product3;
import com.ranjeet.sqliteapps.ProductViewHolder3;
import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.SqliteDatabase3;

import java.util.List;

/**
 * Created by admin on 12/12/2017.
 */

public class ProductAdapter3 extends RecyclerView.Adapter<ProductViewHolder3>{
    private Context context;
    private List<Product3> listProducts;
    private SqliteDatabase3 mDatabase;
    public ProductAdapter3(Context context, List<Product3> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
        mDatabase = new SqliteDatabase3(context);
    }
    @Override
    public ProductViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_layout3, parent, false);
        return new ProductViewHolder3(view);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder3 holder, int position) {
        final Product3 singleProduct = listProducts.get(position);
        holder.name.setText(singleProduct.getName());
        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(singleProduct);
            }
        });
        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database
                mDatabase.deleteProduct(singleProduct.getId());
                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }
    @Override
    public int getItemCount() {
        return listProducts.size();
    }
    private void editTaskDialog(final Product3 product){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_product_layout3, null);
        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText quantityField = (EditText)subView.findViewById(R.id.enter_quantity);
        if(product != null){
            nameField.setText(product.getName());
            quantityField.setText(String.valueOf(product.getQuantity()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit product");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("EDIT PRODUCT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final int quantity = Integer.parseInt(quantityField.getText().toString());
                if(TextUtils.isEmpty(name) || quantity <= 0){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateProduct(new Product3(product.getId(), name, quantity));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
