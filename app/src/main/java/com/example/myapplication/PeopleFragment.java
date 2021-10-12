package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class PeopleFragment extends Fragment {

    private View convertView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (convertView == null) {
            // if it is new, initialise it using 'fragment_json' layout
            // Inflate the layout for this fragment
            convertView = inflater.inflate(R.layout.fragment_people, container, false);
        }

        ListView listView = convertView.findViewById(R.id.lview1);

        return convertView;
    }
//    private void getAllWidgets(View view) {
//        listView = (ListView) view.findViewById(R.id.listPeople);
//        allPeople = getResources().obtainTypedArray(R.array.people);
//    }
//    private void setAdapter()
//    {
//        for (int i = 0; i < allPeople.length(); i++) {
//            allPeopleNames.add(allPeople.getString(i));
//        }
//        PeopleListViewAdapter listViewAdapter= new PeopleListViewAdapter(MainActivity.getInstance(), al);
//        listView.setAdapter(listViewAdapter);
//    }

}