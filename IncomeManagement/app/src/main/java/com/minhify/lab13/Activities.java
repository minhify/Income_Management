package com.minhify.lab13;

public class Activities {

    String name;
    int type;
    String date;
    String time;
    int amount;
    String ofUser;
    String id;

    public Activities() {}

    public Activities(String name, int type, String date, String time, int amount, String ofUser) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.ofUser = ofUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfUser() {
        return ofUser;
    }

    public void setOfUser(String ofUser) {
        this.ofUser = ofUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


