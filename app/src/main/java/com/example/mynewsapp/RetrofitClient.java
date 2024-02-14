package com.example.mynewsapp;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //Retrofit 인스턴스를 생성하고, 위에서 정의한 서비스 인터페이스를 초기화합니다.
    // 이 과정에서 기본 URL과 데이터 컨버터(Gson)를 설정합니다.
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    return retrofit;
    }
}
