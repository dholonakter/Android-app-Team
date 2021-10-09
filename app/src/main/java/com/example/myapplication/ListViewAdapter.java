package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<String> allPeople;
    private Context context;
    private LayoutInflater inflater;
    public ListViewAdapter(Context context, ArrayList<String> allPeople) {
        inflater = LayoutInflater.from(context);
        this.context = context;
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
        final PersonHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.person_inflater, parent, false);
            holder = new PersonHolder();
            assert view != null;
            holder.tvPersonName = (TextView) view.findViewById(R.id.tvPersonName);
            holder.tvPersonNumber = (TextView) view.findViewById(R.id.tvPersonNumber);
            view.setTag(holder);
        } else {
            holder = (PersonHolder) view.getTag();
        }
        holder.tvPersonName.setText(allPeople.get(position));
        holder.tvPersonNumber.setText((position+1)+"");
        return view;
    }
}
class PersonHolder {
    TextView tvPersonName;
    TextView tvPersonNumber;
}