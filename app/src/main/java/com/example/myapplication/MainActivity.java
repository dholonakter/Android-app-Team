package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener {

//    public static MainActivity instance;
//    private PagerAdapter adapter;
//    private PeopleFragment peopleFragment;
//    private ViewPager viewPager;
//    private TabLayout allTabs;
    private TextView tv;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView1);
//        instance=this;
//        getAllWidgets();
//        setupViewPager();
    }
//
//    public static MainActivity getInstance() {
//        return instance;
//    }
//    private void getAllWidgets() {
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        allTabs = (TabLayout) findViewById(R.id.tabs);
//    }
//    private void setupViewPager() {
//        adapter = new PagerAdapter(getSupportFragmentManager());
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
    public class JSONTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params){
            String s = null;
            try {
                URL url = new URL("https://api.fhict.nl/people");
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + token);
                connection.connect();
                InputStream is = connection.getInputStream();
                Scanner scn = new Scanner(is);
                s = scn. useDelimiter("\\Z").next();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
            tv.setText(s);
        }
    }
}