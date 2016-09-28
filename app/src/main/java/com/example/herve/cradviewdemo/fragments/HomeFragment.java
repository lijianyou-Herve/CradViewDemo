package com.example.herve.cradviewdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.herve.cradviewdemo.R;
import com.example.herve.cradviewdemo.adapter.CustomAdapter;
import com.example.herve.cradviewdemo.bean.MedicineBean;
import com.example.herve.cradviewdemo.bean.MedicineItemBean;

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

public class HomeFragment extends Fragment {


    private Context mContext;
    private View rootView;
    private RecyclerView recycleViewHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, container, false);

        recycleViewHome = (RecyclerView) rootView.findViewById(R.id.recycle_view_home);


        initData();
        return rootView;
    }


    public static Fragment newInstance() {

        return new HomeFragment();
    }

    private void initData() {

        CustomAdapter customAdapter = new CustomAdapter(mContext);

        ArrayList<MedicineItemBean> medicineItemBeens = new ArrayList<>();


        for (int position = 0; position < 10; position++) {
            MedicineItemBean itemBean = new MedicineItemBean();

            if (position == 1) {
                itemBean.setType(false);
                itemBean.setTitle("昨天");
            } else {

                if (position == 0) {
                    itemBean.setTitle("这次服药");
                }
                itemBean.setSelect(true);
                itemBean.setTime("8:00");
                itemBean.setType(true);


                ArrayList<MedicineBean> medicineBeens = new ArrayList<>();

                for (int j = 0; j < position; j++) {
                    MedicineBean medicineBean = new MedicineBean();
                    if (position == 3) {
                        medicineBean.setSelect(true);
                    } else {
                        medicineBean.setSelect(false);
                    }
                    medicineBean.setCount(j);
                    medicineBean.setMedicineName("阿司匹林" + j);
                    medicineBean.setWeight(j * 100);
                    medicineBeens.add(medicineBean);
                }

                itemBean.setMedicineBeans(medicineBeens);
            }
            medicineItemBeens.add(itemBean);

        }


        customAdapter.setData(medicineItemBeens);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);

        recycleViewHome.setLayoutManager(linearLayoutManager);
        recycleViewHome.setAdapter(customAdapter);


    }

}
