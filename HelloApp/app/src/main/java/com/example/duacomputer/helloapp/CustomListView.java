package com.example.duacomputer.helloapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dua computer on 11/15/2016.
 */
public class CustomListView extends BaseAdapter {

    ArrayList<MyClass> list;
    LayoutInflater inflate;

    Context cont;
    TextView txtname,txtemail,txtage,txtkey;


    public CustomListView(ArrayList<MyClass> list, Context cont) {
        this.list = list;
        this.cont = cont;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflate.from(cont).inflate(R.layout.custom_layout,viewGroup,false);

        txtname = (TextView) view.findViewById(R.id.csname);
        txtemail = (TextView) view.findViewById(R.id.csemail);
        txtage = (TextView) view.findViewById(R.id.csage);
        txtkey = (TextView) view.findViewById(R.id.cskey);
        txtname.setText(list.get(i).getName());
        txtemail.setText(list.get(i).getEmail());
        txtage.setText(list.get(i).getAge()+"");
        txtkey.setText(list.get(i).getKey());





        return view;
    }
}
