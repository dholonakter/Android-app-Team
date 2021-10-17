package com.example.myapplication;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PeopleFragment extends Fragment {

    private ListView listView;
    TypedArray allPeople;
    ArrayList<String> allPeopleNames = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_people, null);
        getAllWidgets(rootView);
        setAdapter();
        return rootView;
    }
    private void getAllWidgets(View view) {
        listView = (ListView) view.findViewById(R.id.listPeople);
        allPeople = getResources().obtainTypedArray(R.array.people);
    }
    private void setAdapter()
    {
        for (int i = 0; i < allPeople.length(); i++) {
            allPeopleNames.add(allPeople.getString(i));
        }
//        ListViewAdapter listViewAdapter= new ListViewAdapter(MainActivity.getInstance(), allPeopleNames);
//        listView.setAdapter(listViewAdapter);
    }

}