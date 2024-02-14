package com.example.mynewsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Api_Manager {
    public void loadNewsData(NewsDataListener listener){
        Api_News api = Api_Retrofit_Client.getClient().create(Api_News.class);
        api.getNews().enqueue(new Callback<Api_Response>() {
            @Override
            public void onResponse(Call<Api_Response> call, Response<Api_Response> response) {
                if (response.isSuccessful()){
                    List<Api_NewsItem> items = response.body().getItemList();
                    listener.onDataLoaded(items);
                }else{
                    listener.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Api_Response> call, Throwable t) { // 여기도 NewsResponse로 수정
                listener.onError(t.getMessage());
            }
        });
    }

    public interface NewsDataListener {
        void onDataLoaded(List<Api_NewsItem> newsItems);
        void onError(String errorMessage);
    }
}
