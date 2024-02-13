package com.example.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout searchLayout, titleBarLayout;
    private ImageView searchIcon;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchLayout = findViewById(R.id.search_layout);
        titleBarLayout = findViewById(R.id.titlebar_layout);
        searchIcon = findViewById(R.id.iv_search);
        backButton = findViewById(R.id.btn_input_delete);

        // 검색 아이콘 클릭 이벤트 처리
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // titlebar_layout을 숨기고 search_layout을 보이게 함
                titleBarLayout.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                updateViewLineConstraint(R.id.search_layout); // view_line을 검색창 아래에 두기
            }
        });

        // 뒤로 가기 버튼 클릭 이벤트 처리
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색 레이아웃을 숨기고 titlebar_layout을 다시 보이게 함
                searchLayout.setVisibility(View.GONE);
                titleBarLayout.setVisibility(View.VISIBLE);
                updateViewLineConstraint(R.id.titlebar_layout); // view_line 타이틀바 아래에 위치
            }
        });
    }
    private void updateViewLineConstraint(int anchorId) {
        ConstraintLayout rootLayout = findViewById(R.id.main_layout);
        View viewLine = findViewById(R.id.view_line);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(rootLayout);
        constraintSet.connect(R.id.view_line, ConstraintSet.TOP, anchorId, ConstraintSet.BOTTOM, 0);
        constraintSet.applyTo(rootLayout);
    }

}

