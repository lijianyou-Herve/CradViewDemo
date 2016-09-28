package com.example.herve.cradviewdemo.bean;

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

public class MedicineItemBean {

    private boolean select;
    /*昨天 false，今天true*/
    private boolean type;
    /*标题*/
    private String title;

    private String time;

    private ArrayList<MedicineBean> medicineBeans;


    public MedicineItemBean() {
    }

    public MedicineItemBean(boolean select, boolean type, String title, String time, ArrayList<MedicineBean> medicineBean) {
        this.select = select;
        this.type = type;
        this.title = title;
        this.time = time;
        this.medicineBeans = medicineBean;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<MedicineBean> getMedicineBeans() {
        return medicineBeans;
    }

    public void setMedicineBeans(ArrayList<MedicineBean> medicineBean) {
        this.medicineBeans = medicineBean;
    }
}
