package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String scheduleJsonString;
    private List<Schedule> scheduleList;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            scheduleJsonString = getArguments().getString("scheduleJsonString");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        // create and populate schedule entry list
        List<Schedule> scheduleList = getScheduleList();
        final ListView listView = (ListView) view.findViewById(R.id.scheduleListView);
        listView.setAdapter(new ScheduleViewAdapter(getActivity(), scheduleList));

        return view;
    }

    private List<Schedule> getScheduleList() {
        List<Schedule> scheduleList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(scheduleJsonString);
            // get all elements from the JSONArray, and put into ArrayList of NewsEntry objects
            for (int i = 0; i < jsonArray.length(); i++) {
                // each array element is an object
                JSONObject sObject = jsonArray.getJSONObject(i);
                String title = sObject.getString("title");
                String start = sObject.getString("start");
                String end = sObject.getString("end");
                scheduleList.add(new Schedule(title, start, end));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return scheduleList;
    }
}
