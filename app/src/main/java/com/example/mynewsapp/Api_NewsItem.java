package com.example.mynewsapp;

public class Api_NewsItem {
    private String title;
    private String company;
    private String date;
    private String url;

    public Api_NewsItem(String title, String company, String date, String url) {
        this.title = title;
        this.company = company;
        this.date = date;
        this.url = url;
    }

    public String getTitle() { return title; }

    public String getCompany() {
        return company;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
