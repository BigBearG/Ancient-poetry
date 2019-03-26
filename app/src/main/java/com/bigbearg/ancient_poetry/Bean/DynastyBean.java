package com.bigbearg.ancient_poetry.Bean;

public class DynastyBean {
    private String dynastyName;
    private int count;


    public DynastyBean() {
    }



    public DynastyBean(String dynastyName, int count) {
        this.dynastyName = dynastyName;
        this.count = count;
    }

    public String getDynastyName() {
        return dynastyName;
    }

    public void setDynastyName(String dynastyName) {
        this.dynastyName = dynastyName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
