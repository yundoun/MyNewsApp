package com.example.mynewsapp;

public class NewsRvDataModel {
    String title;
    String company;
    String date;
    String url;
    public NewsRvDataModel(String title, String company, String date, String url) {
        this.title = title;
        this.company = company;
        this.date = date;
    }
    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDate() {
        return date;
    }

    public String getUrl(){return date;}
}
