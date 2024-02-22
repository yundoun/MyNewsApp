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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mynewsapp.databinding.BaseFragmentLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class Fragment_01_General extends BaseNewsFragment implements DetailInterface{
    //Fragment의 경우, 뷰가 생성될 때(onCreateView) 바인딩을 초기화하고,
    // 뷰가 소멸될 때(onDestroyView) 바인딩을 해제해야 합니다.
    // 이 과정은 Fragment의 라이프사이클과 관련이 있으며, 메모리 누수를 방지하기 위해 필요합니다.

    private BaseFragmentLayoutBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 바인딩 객체를 초기화. inflate 메소드 사용
        binding = BaseFragmentLayoutBinding.inflate(inflater, container, false);
        binding.tvNewsflash.setText(R.string.General_Newsflash);
        binding.tvRank.setText(R.string.General_Rank);
        setupRecyclerViews();

        Log.d("DetailFragment", "onCreateView called");
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
                // onDataLoaded 콜백 내부 또는 어댑터를 설정하는 곳에서
                NewsRvAdapter_NewsFlash newsFlashAdapter = new NewsRvAdapter_NewsFlash(getContext(), new ArrayList<>(newsItems), Fragment_01_General.this);
                binding.rvNewsflash.setAdapter(newsFlashAdapter);
                binding.rvRank.setAdapter(new NewsRvAdapter_Rank(getContext(), new ArrayList<>(newsItems)));
            }

            @Override
            public void onError(String errorMessage) {
                // 데이터 로드 실패 처리
                String message = getContext().getString(R.string.data_load_fail) + errorMessage;
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                Log.d(getContext().getString(R.string.Fragment_01_General), errorMessage);
            }
        });
    }
    @Override
    protected void loadPageData(int page) {

    }
    @Override
    protected void loadNextPageGroup() {

    }

    @Override
    public void onItemClick(int position) {
        Log.d("FragmentTransition", "Item clicked: " + position);
        // 현재 프래그먼트 매니저에서 DetailFragment 찾기
        DetailFragment existingFragment = (DetailFragment) getParentFragmentManager().findFragmentByTag("DETAIL_FRAGMENT");

        // DetailFragment가 이미 추가되어 있지 않은 경우에만 추가
        if (existingFragment == null) {
            DetailFragment newFragment = new DetailFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment, "DETAIL_FRAGMENT");
            transaction.addToBackStack(null); // 뒤로가기 스택에 추가
            transaction.commit();
        }
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // 바인딩 해제
    }
}
