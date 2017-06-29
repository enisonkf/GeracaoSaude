package com.example.kelvinferreira.geracaosaude;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Indices;

public class IndicesAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Indices> itemsArrayList;

    public IndicesAdapter(Context context, ArrayList<Indices> itemsArrayList) {
        this.context=context;
        this.itemsArrayList=itemsArrayList;
    }

    @Override
    public int getCount() {
        return itemsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.activity_show_indices,null);
        }
        TextView date = (TextView) convertView.findViewById(R.id.tv_data_list);
        TextView imc = (TextView) convertView.findViewById(R.id.tv_imc_list);

        TextView peso = (TextView) convertView.findViewById(R.id.tv_peso_list);
        TextView alt = (TextView) convertView.findViewById(R.id.tv_alt_list);

        Indices indices =itemsArrayList.get(position);
        date.setText(indices.getData());
        imc.setText(new DecimalFormat("#0.00").format(indices.getImc()));
        peso.setText(new DecimalFormat("#0.00").format(indices.getPeso())+" Kg.");
        alt.setText(new DecimalFormat("#0.00").format(indices.getAltura())+" m.");

        return convertView;
    }
}
