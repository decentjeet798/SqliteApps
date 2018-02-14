package com.ranjeet.sqliteapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.pojo.DataModel4;

import java.util.List;

/**
 * Created by admin on 12/11/2017.
 */

public class RecycleAdapter4 extends RecyclerView.Adapter<RecycleAdapter4.Myholder> {
    List<DataModel4> dataModel4ArrayList;

    public RecycleAdapter4(List<DataModel4> dataModel4ArrayList) {
        this.dataModel4ArrayList = dataModel4ArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView name,city,country,company;

        public Myholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name1);
            city = (TextView) itemView.findViewById(R.id.city1);
            country = (TextView) itemView.findViewById(R.id.country1);
            company = (TextView) itemView.findViewById(R.id.name2);
        }
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview4,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        DataModel4 dataModel4 = dataModel4ArrayList.get(position);
        holder.name.setText(dataModel4.getName());
        holder.city.setText(dataModel4.getCity());
        holder.country.setText(dataModel4.getCounty());
        holder.company.setText(dataModel4.getCompany());

    }

    @Override
    public int getItemCount() {
        return dataModel4ArrayList.size();
    }
}
