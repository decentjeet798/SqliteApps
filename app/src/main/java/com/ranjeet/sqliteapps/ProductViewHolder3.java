package com.ranjeet.sqliteapps;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 12/12/2017.
 */

public class ProductViewHolder3 extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView deleteProduct;
    public ImageView editProduct;

    public ProductViewHolder3(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.product_name);
        deleteProduct = (ImageView) itemView.findViewById(R.id.delete_product);
        editProduct = (ImageView) itemView.findViewById(R.id.edit_product);
    }
}


