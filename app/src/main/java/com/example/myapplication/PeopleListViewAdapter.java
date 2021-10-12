package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PeopleListViewAdapter extends BaseAdapter {

    private List<Person> peopleData;
    private Context context;
    private LayoutInflater inflater;
    public PeopleListViewAdapter(Context aContext, List<Person> peopleData) {
        inflater = LayoutInflater.from(aContext);
        this.context = aContext;
        this.peopleData = peopleData;
    }
    @Override
    public int getCount() {
        return peopleData.size();
    }
    @Override
    public Object getItem(int position) {
        return peopleData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.person_inflater, null);
            holder = new PersonHolder();
            holder.firstName = (TextView) convertView.findViewById(R.id.tvFirstName);
            holder.secondName = (TextView) convertView.findViewById(R.id.tvSecondName);
            holder.initials = (TextView) convertView.findViewById(R.id.tvInitials);
            holder.mail = (TextView) convertView.findViewById(R.id.tvMail);
            holder.telephone = (TextView) convertView.findViewById(R.id.tvTelephone);
            convertView.setTag(holder);
        } else {
            holder = (PersonHolder) convertView.getTag();
        }
        Person person = this.peopleData.get(position);
        holder.firstName.setText(person.getGivenName());
        holder.secondName.setText(person.getSurName());
        holder.initials.setText(person.getInitials());
        holder.mail.setText(person.getMail());
        holder.telephone.setText(person.getTelephoneNumber());
        return convertView;
    }
}
class PersonHolder {
    TextView firstName;
    TextView secondName;
    TextView initials;
    TextView mail;
    TextView telephone;
}