package com.example.mynewsapp;

import java.util.List;

public class NewsResponse {
    private List<NewsItem> itemList; // "itemList" 키 아래 있는 배열에 해당

    // itemList의 getter
    public List<NewsItem> getItemList() {
        return itemList;
    }

    // itemList의 setter (필요한 경우)
    public void setItemList(List<NewsItem> itemList) {
        this.itemList = itemList;
    }
}
