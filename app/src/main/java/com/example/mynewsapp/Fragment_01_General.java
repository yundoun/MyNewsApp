package com.example.mynewsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Fragment1.java
public class Fragment_01_General extends Fragment {

    private RecyclerView recyclerView;
    private NewsRvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View firstView = inflater.inflate(R.layout.fragment_01_general, container, false);

        recyclerView = firstView.findViewById(R.id.rv_general);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //NewsApi 데이터 로드 메소드 호출

        Api_Manager newsApiManager = new Api_Manager();
        newsApiManager.loadNewsData(new Api_Manager.NewsDataListener() {
            @Override
            public void onDataLoaded(List<Api_NewsItem> newsItems) {
                // 데이터 로드 성공 시 어댑터에 데이터 설정
                adapter = new NewsRvAdapter(getContext(), new ArrayList<>(newsItems));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                // 데이터 로드 실패 처리
                Toast.makeText(getContext(), "데이터 로드 실패: " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.d("err", errorMessage);
            }

        });

        return firstView;
    }
}