package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class PeopleFragment extends Fragment {

    private View convertView;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance(String param1, String param2) {
        PeopleFragment fragment = new PeopleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Person person = (Person) o;
                String telephoneNumber = person.telephoneNumber;
                Log.d("TELEPHONE NUMBER", telephoneNumber);
            }
        });

        return convertView;
    }

}