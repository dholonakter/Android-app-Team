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

public class PeopleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String peopleJsonString;
    private List<People> peopleList;

    public PeopleFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PeopleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PeopleFragment newInstance(String param1, String param2) {
        PeopleFragment fragment = new PeopleFragment();
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
            peopleJsonString = getArguments().getString("peopleJsonString");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_people, container, false);
        List<People> peopleList = getPeopleList();
        final ListView listView = (ListView) view.findViewById(R.id.peopleListView);
        listView.setAdapter(new PeopleViewAdapter(getActivity(), peopleList));
        return view;
    }

    private List<People> getPeopleList() {
        List<People> peopleList = new ArrayList<>();
        try {
            // [Reference]: https://stackoverflow.com/questions/10164741/get-jsonarray-without-array-name
            JSONArray jsonArray = new JSONArray(peopleJsonString);

            // get all elements from the JSONArray, and put into ArrayList of NewsEntry objects
            for (int i = 0; i < jsonArray.length(); i++) {
                // each array element is an object
                JSONObject pObject = jsonArray.getJSONObject(i);
                String id = pObject.getString("id");
                String displayName = pObject.getString("displayName");
                String mail = pObject.getString("mail");
                peopleList.add(new People(id, displayName, mail));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return peopleList;
    }

}