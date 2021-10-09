package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class PeopleFragment extends Fragment {

    String[] personName = {"aaaaaaaaa", "bbbbbbbbb", "cccccccccc" };
    String[] personEmail = {"aaaa email", "bbbbb email", "ccccc email"};
    Integer[] personImage = {R.drawable.ic_baseline_email, R.drawable.ic_baseline_email, R.drawable.ic_baseline_email};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.adapter_row, container, false);
    }

}