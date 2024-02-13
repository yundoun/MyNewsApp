package com.example.mynewsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout searchLayout, titleBarLayout;
    private ImageView searchIcon;
    private ImageView backButton;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        configTab();
    }

    private void initViews() {
        searchLayout = findViewById(R.id.search_layout);
        titleBarLayout = findViewById(R.id.titlebar_layout);
        searchIcon = findViewById(R.id.iv_search);
        backButton = findViewById(R.id.btn_input_delete);
        viewPager = findViewById(R.id.view_pager);

        searchIcon.setOnClickListener(v -> toggleSearchView(true));
        backButton.setOnClickListener(v -> toggleSearchView(false));
    }

    private void toggleSearchView(boolean showSearch) {
        titleBarLayout.setVisibility(showSearch ? View.GONE : View.VISIBLE);
        searchLayout.setVisibility(showSearch ? View.VISIBLE : View.GONE);
        updateViewLineConstraint(showSearch ? R.id.search_layout : R.id.titlebar_layout);
    }

    private void updateViewLineConstraint(int anchorId) {
        ConstraintLayout rootLayout = findViewById(R.id.main_layout);
        View viewLine = findViewById(R.id.view_line);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(rootLayout);
        constraintSet.connect(R.id.view_line, ConstraintSet.TOP, anchorId, ConstraintSet.BOTTOM, 0);
        constraintSet.applyTo(rootLayout);
    }

    private void configTab() {
        viewPager.setAdapter(new ScreenSlidePagerAdapter(this));

        TabLayout tabs = findViewById(R.id.tabMain);
        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("종합");
                    break;
                case 1:
                    tab.setText("정치");
                    break;
                case 2:
                    tab.setText("경제");
                    break;
                case 3:
                    tab.setText("사회");
                    break;
                case 4:
                    tab.setText("문화");
                    break;
                case 5:
                    tab.setText("세계");
                    break;
                case 6:
                    tab.setText("IT/과학");
                    break;
                default:
                    tab.setText("탭 " + (position + 1));
                    break;
            }
        }).attach();
    }

}

