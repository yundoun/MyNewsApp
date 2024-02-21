package com.example.mynewsapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.mynewsapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding; // 바인딩 클래스 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // 바인딩 객체 초기화
        setContentView(binding.getRoot()); // setContentView에 바인딩 레이아웃의 루트 뷰 전달

        initViews();
        configTab();
    }

    private void initViews() {
        binding.ivSearch.setOnClickListener(v -> toggleSearchView(true));
        binding.btnDelete.setOnClickListener(v -> toggleSearchView(false));
    }

    private void toggleSearchView(boolean showSearch) {
        binding.titlebarLayout.setVisibility(showSearch ? View.GONE : View.VISIBLE);
        binding.searchLayout.setVisibility(showSearch ? View.VISIBLE : View.GONE);
        updateViewLineConstraint(showSearch ? R.id.searchLayout : R.id.titlebarLayout);
    }

    private void updateViewLineConstraint(int anchorId) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(binding.mainLayout);
        constraintSet.connect(R.id.viewLine, ConstraintSet.TOP, anchorId, ConstraintSet.BOTTOM, 0);
        constraintSet.applyTo(binding.mainLayout);
    }

    private void configTab() {
        binding.viewPager.setAdapter(new ScreenSlidePagerAdapter(this));

        String[] tabTitles = getResources().getStringArray(R.array.tabTitles);

        new TabLayoutMediator(binding.tabMain, binding.viewPager, (tab, position) -> {
            if (position < tabTitles.length) {
                tab.setText(tabTitles[position]);
            } else {
                tab.setText(position + 1);
            }
        }).attach();
    }
}

