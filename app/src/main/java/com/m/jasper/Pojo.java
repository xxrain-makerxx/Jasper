package com.m.jasper;

public class Pojo {
    public Pojo(String descrp, String data) {
        this.descrp = descrp;
        this.data = data;
    }
    public Pojo(){

    }

    public String getDescrp() {
        return descrp;
    }

    public String getData() {
        return data;
    }

    String descrp;

    public Pojo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public void setData(String data) {
        this.data = data;
    }

    String data;
}
