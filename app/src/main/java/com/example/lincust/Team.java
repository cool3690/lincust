package com.example.lincust;

/**
 * Created by kstanoev on 1/14/2015.
 */
public class Team {
    private String date, item, money,size,save;

    public Team(String date, String item, String money, String size,String save)
    {
        this.setDate(date);
        this.setItem(item);
        this.setMoney(money);
        this.setSize(size);
        this.setSave(save);
        /*
        this.setDraws(draws);
        this.setLosses(losses);
        this.setPoints(points);
        */
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLosses() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

}
