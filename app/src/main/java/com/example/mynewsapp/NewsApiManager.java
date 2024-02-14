package com.example.mynewsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiManager {
    public void loadNewsData(NewsDataListener listener){
        NewsApi api = RetrofitClient.getClient().create(NewsApi.class);
        api.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    List<NewsItem> items = response.body().getItemList();
                    listener.onDataLoaded(items);
                }else{
                    listener.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) { // 여기도 NewsResponse로 수정
                listener.onError(t.getMessage());
            }
        });
    }

    public interface NewsDataListener {
        void onDataLoaded(List<NewsItem> newsItems);
        void onError(String errorMessage);
    }
}
