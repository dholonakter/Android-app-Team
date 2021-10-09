package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;
    private PagerAdapter adapter;
    private PeopleFragment peopleFragment;
    private ViewPager viewPager;
    private TabLayout allTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=this;
        getAllWidgets();
        setupViewPager();
    }

    public static MainActivity getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupViewPager() {
        adapter = new PagerAdapter(getSupportFragmentManager());
        peopleFragment = new PeopleFragment();
        adapter.addFragment(peopleFragment, "People");
        setViewPageAdapter();
    }
    private void setViewPageAdapter() {
        viewPager.setAdapter(adapter);
        allTabs.setupWithViewPager(viewPager);
    }
}