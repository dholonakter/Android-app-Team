package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabBarActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        tabLayout.addTab(tabLayout.newTab().setText("People"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));

        String peopleJsonString = getIntent().getStringExtra("people");
        ArrayList<Person> people = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(peopleJsonString);
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject objectCalendarItem = jsonArray.getJSONObject(i);
                String id = objectCalendarItem.getString("id");
                String name = objectCalendarItem.getString("givenName");
                people.add(new Person(name, id));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String calendar = getIntent().getStringExtra("calendar");
        ArrayList<Calendar> calendarItems = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(calendar);
            String items = jsonObject.getString("items");
            JSONArray jsonArray = new JSONArray(items);
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject objectCalendarItem = jsonArray.getJSONObject(i);
                String start = objectCalendarItem.getString("start");
                String end = objectCalendarItem.getString("end");
                String title = objectCalendarItem.getString("title");
                boolean allDay = objectCalendarItem.getBoolean("allDay");
                calendarItems.add(new Calendar(start, end, title, allDay));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        PagerAdapter fm = new PagerAdapter(getSupportFragmentManager(), people, calendarItems);
//        pager2.setAdapter(fm);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}