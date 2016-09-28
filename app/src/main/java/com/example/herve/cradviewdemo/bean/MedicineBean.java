package com.example.herve.cradviewdemo.bean;

public class MedicineBean {
        private boolean select;
        private String medicineName;
        private int count;
        private int weight;


    public MedicineBean() {
    }

    public MedicineBean(boolean select, String medicineName, int count, int weight) {
            this.select = select;
            this.medicineName = medicineName;
            this.count = count;
            this.weight = weight;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getMedicineName() {
            return medicineName;
        }

        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }