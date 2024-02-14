package com.example.mynewsapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

// NewsApiService.java
public interface NewsApi {
    @GET("jabistar/data/main/data.json")
    Call<NewsResponse> getNews();
}