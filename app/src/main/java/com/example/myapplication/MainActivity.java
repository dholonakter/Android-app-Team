package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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

    public static MainActivity instance;
    private PersonAdapter adapter;
    private PeopleFragment peopleFragment;
    private ViewPager viewPager;
    private TabLayout allTabs;

    private TextView tv;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv1);

//        Button btn1 = findViewById(R.id.btn1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // start PeopleActivity for result
//                Intent intent = new Intent(MainActivity.this, PeopleFragment.class);
//                startActivityForResult(intent, 1);
//            }
//        });

//        allTabs = findViewById(R.id.tabs);
    }

//    public static MainActivity getInstance() {
//        return instance;
//    }
//    private void getAllWidgets() {
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        allTabs = (TabLayout) findViewById(R.id.tabs);
//    }

//    private void setupViewPager() {
//        adapter = new PersonAdapter(getSupportFragmentManager());
//        peopleFragment = new PeopleFragment();
//        adapter.addFragment(peopleFragment, "People");
//        setViewPageAdapter();
//    }

//    private void setViewPageAdapter() {
//        viewPager.setAdapter(adapter);
//        allTabs.setupWithViewPager(viewPager);
//    }

    @Override
    public void onFragmentInteraction(String token) {
        this.token = token;
        new JSONTask().execute(token);

    }
    public class JSONTask extends AsyncTask<String, Void, List<Person>> {

        protected List<Person> doInBackground(String... params){
            URL url = null;
            String s = null;
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
                    result.add(new Person(givenName, surName, initials, mail, telephoneNumber));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Person> people){
            super.onPostExecute(people);
            tv.setText(people.toString());
        }
    }
}