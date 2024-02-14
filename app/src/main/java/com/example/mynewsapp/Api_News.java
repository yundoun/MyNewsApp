package com.example.mynewsapp;

import retrofit2.Call;
import retrofit2.http.GET;

// NewsApiService.java
public interface Api_News {
    @GET("jabistar/data/main/data.json")
    Call<Api_Response> getNews();
}