package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PeopleViewAdapter extends BaseAdapter {

    private ArrayList<String> allPeople;
    private Context context;
    private LayoutInflater inflater;
    public PeopleViewAdapter(Context context, ArrayList<String> allPeople) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.allPeople = allPeople;
    }

    public void setAllPeople(ArrayList<String> allPeople) {
        this.allPeople = allPeople;
    }

    @Override
    public int getCount() {
        return allPeople.size();
    }
    @Override
    public Object getItem(int position) {
        return allPeople.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.person_inflater, parent, false);

        }
        TextView tvPersonName = (TextView) view.findViewById(R.id.tvPersonName);
        TextView tvPersonNumber = (TextView) view.findViewById(R.id.tvPersonNumber);
        tvPersonName.setText(allPeople.get(position));
        tvPersonNumber.setText((position+1)+"");
        return view;
    }
}