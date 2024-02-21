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

import com.example.mynewsapp.databinding.ActivityMainBinding;
import com.example.mynewsapp.databinding.Fragment01GeneralBinding;

import java.util.ArrayList;
import java.util.List;

public class Fragment_01_General extends BaseNewsFragment {
    //Fragment의 경우, 뷰가 생성될 때(onCreateView) 바인딩을 초기화하고,
    // 뷰가 소멸될 때(onDestroyView) 바인딩을 해제해야 합니다.
    // 이 과정은 Fragment의 라이프사이클과 관련이 있으며, 메모리 누수를 방지하기 위해 필요합니다.

    private Fragment01GeneralBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 바인딩 객체를 초기화. inflate 메소드 사용
        binding = Fragment01GeneralBinding.inflate(inflater, container, false);

        setupRecyclerViews();

        return binding.getRoot();
    }

    private void setupRecyclerViews() {
        binding.rvNewsflash.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRank.setLayoutManager(new LinearLayoutManager(getContext()));

        // NewsApi 데이터 로드 메소드 호출
        Api_Manager newsApiManager = new Api_Manager();
        newsApiManager.loadNewsData(new Api_Manager.NewsDataListener() {
            @Override
            public void onDataLoaded(List<Api_NewsItem> newsItems) {
                // 데이터 로드 성공 시 어댑터에 데이터 설정
                binding.rvNewsflash.setAdapter(new NewsRvAdapter_NewsFlash(getContext(), new ArrayList<>(newsItems)));
                binding.rvRank.setAdapter(new NewsRvAdapter_Rank(getContext(), new ArrayList<>(newsItems)));
            }

            @Override
            public void onError(String errorMessage) {
                // 데이터 로드 실패 처리
                Toast.makeText(getContext(), "데이터 로드 실패: " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.d("Fragment_01_General", errorMessage);
            }
        });
    }
    @Override
    protected void loadPageData(int page) {

    }
    @Override
    protected void loadNextPageGroup() {

    }
}
