package com.ranjeet.sqliteapps.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ranjeet.sqliteapps.pojo.Jobseeker;
import com.ranjeet.sqliteapps.R;

import java.util.ArrayList;
public class Food_list_Adapter8 extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Jobseeker> foodlist;
    public Food_list_Adapter8(Context context,int layout, ArrayList<Jobseeker> foodlist) {
        this.context = context;
        this.layout = layout;
        this.foodlist = foodlist;
    }
    @Override
    public int getCount() {
        return foodlist.size();
    }
    @Override
    public Object getItem(int position) {
        return foodlist.get(position);
    }
    @Override
    public long getItemId(int position) {

        return position;
    }
    private  class  ViewHolder{
        ImageView imageView;
        TextView textname,textemail,textphone;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row=view;
        ViewHolder holder=new ViewHolder();
        if(row==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.textname= (TextView) row.findViewById(R.id.name);
            holder.textemail= (TextView) row.findViewById(R.id.email);
            holder.textphone= (TextView) row.findViewById(R.id.phone);
            holder.imageView= (ImageView) row.findViewById(R.id.seekerimage);
            row.setTag(holder);
        }
        else {
            holder= (ViewHolder) row.getTag();
        }
        Jobseeker food=foodlist.get(position);
        holder.textname.setText(food.getName());
        holder.textemail.setText(food.getEmail());
        holder.textphone.setText(food.getPhone());
         byte[] foodimage=food.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(foodimage,0,foodimage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
