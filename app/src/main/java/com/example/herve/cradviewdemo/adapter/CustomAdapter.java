package com.example.herve.cradviewdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.herve.cradviewdemo.R;
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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.HomeHolder> {


    private ArrayList<MedicineItemBean> data;

    private Context mContext;

    /*普通的Item*/
    private int viewType_simple = 1;
    /*间隔的*/
    private int viewType_interval = 0;


    public CustomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<MedicineItemBean> data) {
        this.data = data;
    }


    @Override
    public int getItemViewType(int position) {

        MedicineItemBean medicineItemBean = data.get(position);

        if (!medicineItemBean.getType()) {

            return viewType_interval;
        }


        return viewType_simple;
    }

    /*设置item的类型*/
    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = null;
        if (viewType == viewType_interval) {
            rootView = LayoutInflater.from(mContext).inflate(R.layout.interval_item, parent, false);

            return new IntervalHolder(rootView);

        }
        rootView = LayoutInflater.from(mContext).inflate(R.layout.simple_item, parent, false);


        return new SimpleHolder(rootView);
    }

    /*绑定数据*/

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {

        MedicineItemBean medicineItemBean = data.get(position);

        if (holder instanceof IntervalHolder) {
            IntervalHolder intervalHolder = (IntervalHolder) holder;

            intervalHolder.tvTime.setText(medicineItemBean.getTitle());

        } else {
            SimpleHolder simpleHolder = (SimpleHolder) holder;

            addItemView(simpleHolder, medicineItemBean);


        }

    }

    /*初始化item*/
    private void addItemView(SimpleHolder simpleHolder, MedicineItemBean medicineItemBean) {


        if (medicineItemBean.isSelect()) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_title_view, simpleHolder.llCardRootView, false);

            TextView tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);

            tvTime.setText(medicineItemBean.getTime());
            if (medicineItemBean.getTitle() != null) {
                tvTitle.setText(medicineItemBean.getTitle());
                itemView.setBackgroundColor(Color.GREEN);
            } else {
                tvTitle.setVisibility(View.GONE);
            }
            simpleHolder.llCardRootView.addView(itemView);
        }

        for (int i = 0; i < medicineItemBean.getMedicineBeans().size(); i++) {

            MedicineBean medicineBean = medicineItemBean.getMedicineBeans().get(i);


            View itemView = LayoutInflater.from(mContext).inflate(R.layout.medicine_item, simpleHolder.llCardRootView, false);
            Switch switch1 = (Switch) itemView.findViewById(R.id.switch1);
            TextView tvName = (TextView) itemView.findViewById(R.id.tv_name);
            TextView tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
            switch1.setChecked(medicineBean.isSelect());

            tvName.setText(medicineBean.getMedicineName());

            tvDetail.setText(medicineBean.getCount() + "(" + medicineBean.getWeight() + "mg)");

            simpleHolder.llCardRootView.addView(itemView);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class SimpleHolder extends HomeHolder {
        private LinearLayout llCardRootView;

        public SimpleHolder(View itemView) {
            super(itemView);
            llCardRootView = (LinearLayout) itemView.findViewById(R.id.ll_card_root_view);

        }
    }


    public class IntervalHolder extends HomeHolder {
        private TextView tvTime;


        public IntervalHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);

        }
    }

    public class HomeHolder extends RecyclerView.ViewHolder {


        public HomeHolder(View itemView) {
            super(itemView);

        }
    }
}
