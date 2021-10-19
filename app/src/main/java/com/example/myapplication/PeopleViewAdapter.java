package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PeopleViewAdapter extends BaseAdapter {

    private List<People> peopleList;
    private LayoutInflater layoutInflater;
    private Context context;

    public PeopleViewAdapter(Context acontext, List<People> peopleData) {
        this.context = acontext;
        this.peopleList = peopleData;
        layoutInflater = LayoutInflater.from(acontext);
    }

    @Override
    public int getCount() {
        return peopleList.size();
    }
    @Override
    public Object getItem(int position) {
        return peopleList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.person_inflater, null);
            holder = new ViewHolder();
            holder.fullNameView = (TextView) convertView.findViewById(R.id.tbFullName);
            holder.mailView = (TextView) convertView.findViewById(R.id.tbEmail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        People people = this.peopleList.get(position);
        holder.fullNameView.setText(people.getDisplayName());
        holder.mailView.setText(people.getMail());
        return convertView;
    }

    static class ViewHolder {
        TextView fullNameView;
        TextView mailView;
    }
}