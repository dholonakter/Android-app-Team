package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    private String token, people, calendar;
    String scheduleJsonString, peopleJsonString;
    String peopleURL ="https://api.fhict.nl/people";
    String scheduleURL ="https://api.fhict.nl/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPeople = findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragMgr = getSupportFragmentManager();
                FragmentTransaction fragTrans = fragMgr.beginTransaction();
                PeopleFragment peopleFragment = new PeopleFragment();

                Bundle bundle=new Bundle();
                bundle.putString("peopleJsonString", peopleJsonString);
                peopleFragment.setArguments(bundle);

                fragTrans.add(R.id.fragment_container, peopleFragment, "PEOPLE");
                fragTrans.commit();
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        TokenFragment tokenFragment = new TokenFragment();
//        fragmentTransaction.replace(R.id.fragment_container, tokenFragment, "LOGIN");
        fragmentTransaction.commit();
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
        new JSONTask().execute(token, "people");

    }
    public class JSONTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings){
            if(strings[1] == "schedule")
            {
                return scheduleJsonString = GetJsonDataByURL(scheduleURL);
            }
            if(strings[1] == "people"){
                return peopleJsonString = GetJsonDataByURL(peopleURL);
            }
            return "";


        }
        private String GetJsonDataByURL(String URL){
            String s = null;
            try {
                URL url = new URL("https://api.fhict.nl/");
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + token);
                connection.connect();
                InputStream is = connection.getInputStream();
                Scanner scn = new Scanner(is);
                s = scn.useDelimiter("\\Z").next();
                is.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
//            Intent intent = new Intent(MainActivity.this, TabBarActivity.class);
//            intent.putExtra("people", people);
//            intent.putExtra("calendar", calendar);
//            startActivity(intent);
            if(s!=null){
                // Do you work here on success
            }else{
                // null response or Exception occur
            }
        }

    }
}