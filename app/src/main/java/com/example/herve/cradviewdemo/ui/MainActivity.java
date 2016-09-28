package com.example.herve.cradviewdemo.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.herve.cradviewdemo.R;
import com.example.herve.cradviewdemo.adapter.HomeFragmentAdapter;
import com.example.herve.cradviewdemo.fragments.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout activityMain;
    private TabLayout tabHome;
    private ViewPager vpHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUi();

        initData();


    }

    private void initData() {
        final HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager());

        ArrayList<Fragment> data = new ArrayList<>();
        data.add(HomeFragment.newInstance());
        data.add(HomeFragment.newInstance());
        data.add(HomeFragment.newInstance());
        data.add(HomeFragment.newInstance());
        homeFragmentAdapter.setData(data);

        vpHome.setAdapter(homeFragmentAdapter);
        tabHome.setupWithViewPager(vpHome);


        for (int position = 0; position < data.size(); position++) {
            tabHome.getTabAt(position).setIcon(homeFragmentAdapter.getIcon(position));

        }

    }

    private void initUi() {


        activityMain = (LinearLayout) findViewById(R.id.activity_main);
        tabHome = (TabLayout) findViewById(R.id.tab_home);
        vpHome = (ViewPager) findViewById(R.id.vp_home);


    }
}
