package com.ranjeet.sqliteapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.pojo.Employee;

import java.util.ArrayList;


public class MyAdapter6 extends BaseAdapter {
    Context context;
    ArrayList<Employee> empList;
    private static LayoutInflater inflater = null;

    public MyAdapter6(Context context, ArrayList<Employee> empList) {
        this.context = context;
        this.empList = empList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return empList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null)
            convertView = inflater.inflate(R.layout.layout_grid_item6, null);

        TextView codeTextView = (TextView) convertView.findViewById(R.id.tv_emp_id);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.tv_emp_name);
        TextView emailTextView = (TextView) convertView.findViewById(R.id.tv_emp_email);
        TextView addressTextView = (TextView) convertView.findViewById(R.id.tv_emp_address);

        Employee e = new Employee();
        e = empList.get(i);
        codeTextView.setText("Code: " + String.valueOf(e.getCode()));
        nameTextView.setText("Name: " + e.getName());
        emailTextView.setText("Email: " + e.getEmail());
        addressTextView.setText("Address: " + e.getAddress());
        return convertView;
    }
}