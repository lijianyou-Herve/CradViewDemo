package com.example.herve.cradviewdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.herve.cradviewdemo.R;

import java.util.ArrayList;

/**
 * Created           :Herve on 2016/9/28.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/28
 * @ projectName     :CradViewDemo
 * @ version
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Integer> icons;
    private ArrayList<Fragment> data;

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
        icons = new ArrayList<>();
        icons.add(R.drawable.ic_home_black_24dp);
        icons.add(R.drawable.ic_alarm_black_24dp);
        icons.add(R.drawable.ic_account_box_black_24dp);
        icons.add(R.drawable.ic_camera_enhance_black_24dp);
    }


    public void setData(ArrayList<Fragment> data) {
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }


    public int getIcon(int position) {

        return icons.get(position);
    }
}
