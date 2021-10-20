package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScheduleViewAdapter extends BaseAdapter {
    private List<Schedule> scheduleData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ScheduleViewAdapter(Context acontext, List<Schedule> scheduleList) {
        this.context = acontext;
        this.scheduleData = scheduleList;
        layoutInflater = LayoutInflater.from(acontext);
    }

    @Override
    public int getCount() {
        return scheduleData.size();
    }

    @Override
    public Object getItem(int position) {
        return scheduleData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.schedule_inflater, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.sTitle);
            holder.startView = (TextView) convertView.findViewById(R.id.sStart);
            holder.endView = (TextView) convertView.findViewById(R.id.sEnd);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Schedule schedule = this.scheduleData.get(position);
        holder.titleView.setText(schedule.getTitle());
        holder.startView.setText(schedule.getStart());
        holder.endView.setText(schedule.getEnd());

        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        TextView startView;
        TextView endView;
    }
}


