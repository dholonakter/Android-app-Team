package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class PeopleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        String[] personName = {"aaaaaaaaa", "bbbbbbbbb", "cccccccccc" };
        String[] personEmail = {"aaaa email", "bbbbb email", "ccccc email"};
        Integer[] personImage = {R.drawable.ic_baseline_email, R.drawable.ic_baseline_email, R.drawable.ic_baseline_email};

        ListView listView = (ListView) view.findViewById(R.id.mainView);

        ArrayAdapter<Integer> imageViewAdapter = new ArrayAdapter<Integer>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                personImage
        );

        ArrayAdapter<String> nameViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_2,
                personName
        );

        ArrayAdapter<String> emailViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                personEmail
        );

        listView.setAdapter(imageViewAdapter);
        listView.setAdapter(nameViewAdapter);
        listView.setAdapter(emailViewAdapter);

        return view;
    }
}