package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener {

    private FragmentAdapter adapter;
    private ViewPager2 pager2;
    private TabLayout tabLayout;

    private TextView tv;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv1);
    }

    @Override
    public void onFragmentInteraction(String token) {

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager2 = (ViewPager2) findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        tabLayout.addTab(tabLayout.newTab().setText("People"));

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

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        this.token = token;
        new JSONTask().execute(token);

    }
    public class JSONTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params){
            URL url = null;
            String s = "";
            List<Person> result = new ArrayList<>();
            try {
                url = new URL("https://api.fhict.nl/people");
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + token);
                connection.connect();
                InputStream is = connection.getInputStream();
                Scanner scn = new Scanner(is);
                s = scn. useDelimiter("\\Z").next();

                JSONArray people = new JSONArray(s);

                for(int idx = 0; idx < people.length(); idx++){
                    JSONObject onePerson = people.getJSONObject(idx);

                    String givenName = onePerson.getString("givenName");
                    String surName = onePerson.getString("surName");
                    String initials = onePerson.getString("initials");
                    String mail = onePerson.getString("mail");
                    String telephoneNumber = onePerson.getString("telephoneNumber");

                    // add new person to list
//                    result.add(new Person(givenName, surName, initials, mail, telephoneNumber));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String dataFetched){
            super.onPostExecute(dataFetched);
            tv.setText(dataFetched);
        }
    }
}