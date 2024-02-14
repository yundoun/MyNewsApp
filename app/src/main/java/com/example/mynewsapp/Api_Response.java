package com.example.mynewsapp;

import java.util.List;

public class Api_Response {
    private List<Api_NewsItem> itemList; // "itemList" 키 아래 있는 배열에 해당

    // itemList의 getter
    public List<Api_NewsItem> getItemList() {
        return itemList;
    }
}
