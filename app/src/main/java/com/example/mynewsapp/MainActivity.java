package com.example.mynewsapp;

import static java.lang.Compiler.disable;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mynewsapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding; // 바인딩 클래스 변수 선언

    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Fragment detailFragment = getSupportFragmentManager().findFragmentByTag("DETAIL_FRAGMENT");
            if (detailFragment != null) {
                // DetailFragment가 화면에 있으면 제거
                getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
                // 여기서 콜백을 비활성화하지 않고, 대신에 이 콜백을 활성화 상태로 유지합니다.
            } else {
                // DetailFragment가 제거된 후, 뒤로가기 버튼을 다시 클릭하면 앱이 종료되도록 함
                if (isEnabled()) {
                    disable(); // 콜백 비활성화
                }
                // 기본 뒤로가기 동작을 수행하여 앱을 종료
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // 바인딩 객체 초기화
        setContentView(binding.getRoot()); // setContentView에 바인딩 레이아웃의 루트 뷰 전달

        initViews();
        configTab();

        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
        // ViewPager2에 페이지 변경 리스너 추가
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Fragment detailFragment = getSupportFragmentManager().findFragmentByTag("DETAIL_FRAGMENT");
                if (detailFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
                }
            }
        });

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

