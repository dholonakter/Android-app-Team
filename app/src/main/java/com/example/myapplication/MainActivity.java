package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    String tokenValue;
    String scheduleJsonString, peopleJsonString;
    String peopleURL ="https://api.fhict.nl/people";
    String scheduleURL ="https://api.fhict.nl/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        // login form as first fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TokenFragment tokenFragment = new TokenFragment();
        fragmentTransaction.replace(R.id.fragment_container, tokenFragment, "LOGIN");
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(tokenValue == null){
            return false;
        }
        if (item.getItemId() == R.id.itemPeople) {
            FragmentManager fragMgr = getSupportFragmentManager();
            FragmentTransaction fragTrans = fragMgr.beginTransaction();
            PeopleFragment peopleFragment = new PeopleFragment();

            Bundle bundle=new Bundle();
            bundle.putString("peopleJsonString", peopleJsonString);
            peopleFragment.setArguments(bundle);  //https://stackoverflow.com/questions/45540721/passing-json-response-data-from-an-activity-to-a-fragment-in-android

            fragTrans.replace(R.id.fragment_container, peopleFragment, "PEOPLE");
            fragTrans.commit();
        }
        else if (item.getItemId() == R.id.itemSchedule) {

        }
        return true;
    }

    @Override
    public void onFragmentInteraction(String token) {
        tokenValue = token;
        new JSONTask().execute(token, "people");

    }
    private String GetJsonDataByURL(String URL){
        URL url = null;
        String s = null;
        try {
            url = new URL(URL);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + tokenValue); // strings[0] will be token
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
    private class JSONTask extends AsyncTask<String, Void, String> {
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

    }
}