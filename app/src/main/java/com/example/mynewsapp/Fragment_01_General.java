package com.example.mynewsapp;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_01_General extends BaseNewsFragment {

    private RecyclerView recyclerView, recyclerView2;
    private NewsRvAdapter_NewsFlash adapter;
    private NewsRvAdapter_Rank adapterRank;


    @Override
    protected void loadPageData(int page) {

    }

    @Override
    protected void loadNextPageGroup() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View firstView = inflater.inflate(R.layout.fragment_01_general, container, false);
        // Fragment에서는 findViewById를 직접 사용할 수 없고, View 인스턴스를 통해 접근해야 한다.
        // 또한, Button 인스턴스를 생성할 때 new Button(this) 대신 new Button(getContext())
        // 또는 firstView.getContext()를 사용해야 합니다.
        recyclerView = firstView.findViewById(R.id.rv_newsflash);
        recyclerView2 = firstView.findViewById(R.id.rv_rank);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        // NewsApi 데이터 로드 메소드 호출
        Api_Manager newsApiManager = new Api_Manager();
        newsApiManager.loadNewsData(new Api_Manager.NewsDataListener() {
            @Override
            public void onDataLoaded(List<Api_NewsItem> newsItems) {
                // 데이터 로드 성공 시 어댑터에 데이터 설정
                adapter = new NewsRvAdapter_NewsFlash(getContext(), new ArrayList<>(newsItems));
                recyclerView.setAdapter(adapter);
                adapterRank = new NewsRvAdapter_Rank(getContext(), new ArrayList<>(newsItems));
                recyclerView2.setAdapter(adapterRank);
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
