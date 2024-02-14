package com.example.mynewsapp;

public class NewsRvDataModel {
    String title;
    String company;
    String date;
    public NewsRvDataModel(String title, String company, String date) {
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
}
