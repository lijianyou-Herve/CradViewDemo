package com.example.herve.cradviewdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static com.example.herve.cradviewdemo.R.id.spinner;
import static com.example.herve.cradviewdemo.R.id.switch1;

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
            TitleViewHolder titleViewHolder;
            if (simpleHolder.llCardRootView.getChildAt(0) == null) {
                titleViewHolder = new TitleViewHolder(mContext, simpleHolder.llCardRootView);
                titleViewHolder.itemView.setTag(titleViewHolder);
                simpleHolder.llCardRootView.addView(titleViewHolder.itemView);

            } else {
                titleViewHolder = (TitleViewHolder) simpleHolder.llCardRootView.getChildAt(0).getTag();
            }

            titleViewHolder.tvTime.setText(medicineItemBean.getTime());
            if (medicineItemBean.getTitle() != null) {
                titleViewHolder.tvTitle.setVisibility(View.VISIBLE);
                titleViewHolder.tvTitle.setText(medicineItemBean.getTitle());
                titleViewHolder.itemView.setBackgroundColor(Color.GREEN);
            } else {
                titleViewHolder.itemView.setBackgroundColor(Color.GRAY);
                titleViewHolder.tvTitle.setVisibility(View.GONE);
            }
        }

        /*对复用的View的做处理*/
        int dataSize = medicineItemBean.getMedicineBeans().size() + 1;
        int viewsSize = simpleHolder.llCardRootView.getChildCount();

        for (int i = viewsSize; i > dataSize; i--) {
            simpleHolder.llCardRootView.removeViewAt(i - 1);
        }

        for (int i = 0; i < medicineItemBean.getMedicineBeans().size(); i++) {

            int position = i;

            MedicineBean medicineBean = medicineItemBean.getMedicineBeans().get(i);

            MedicineViewHolder medicineViewHolder;

            if (medicineItemBean.isSelect()) {
                position += 1;
            }

            if (simpleHolder.llCardRootView.getChildAt(position) == null) {
                medicineViewHolder = new MedicineViewHolder(mContext, simpleHolder.llCardRootView);
                medicineViewHolder.itemView.setTag(medicineViewHolder);
                simpleHolder.llCardRootView.addView(medicineViewHolder.itemView);

            } else {
                medicineViewHolder = (MedicineViewHolder) simpleHolder.llCardRootView.getChildAt(position).getTag();
            }

            medicineViewHolder.switch1.setChecked(medicineBean.isSelect());

            medicineViewHolder.tvName.setText(medicineBean.getMedicineName());

            medicineViewHolder.tvDetail.setText(medicineBean.getCount() + "(" + medicineBean.getWeight() + "mg)");

        }

    }

    class TitleViewHolder {
        TextView tvTime;
        TextView tvTitle;
        Spinner spinner;
        View itemView;

        public TitleViewHolder(Context mContext, ViewGroup parent) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_title_view, parent, false);

            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
        }
    }

    class MedicineViewHolder {
        Switch switch1;
        TextView tvName;
        TextView tvDetail;
        View itemView;

        public MedicineViewHolder(Context mContext, ViewGroup parent) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.medicine_item, parent, false);
            switch1 = (Switch) itemView.findViewById(R.id.switch1);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class SimpleHolder extends HomeHolder {
        private LinearLayout llCardRootView;
        private CardView cardView;

        public SimpleHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_View);

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
