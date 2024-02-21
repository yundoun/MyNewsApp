package com.example.mynewsapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // 상세 화면 레이아웃

        // 인텐트에서 데이터 추출
        String data = getIntent().getStringExtra("key");
        // 데이터를 화면에 표시하는 로직 구현
    }
}
