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

public class Fragment_01_General extends Fragment {

    private RecyclerView recyclerView, recyclerView2;
    private NewsRvAdapter_NewsFlash adapter;
    private NewsRvAdapter_Rank adapterRank;

    private void addPageButtons(View rootView, int buttonsLayoutId) {
        LinearLayout buttonsLayout = rootView.findViewById(buttonsLayoutId);
        buttonsLayout.removeAllViews(); // 기존에 추가된 버튼이 있다면 제거

        int buttonSize = dpToPx(30);

        // 버튼 기본 마진 설정
        int margin = dpToPx(1);
        int largeMargin = dpToPx(70);

        for (int i = 1; i <= 6; i++) {
            Button pageButton = new Button(getContext());
            pageButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.page_btn)); // 배경 설정
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(buttonSize, buttonSize);

            // 첫 번째 버튼과 마지막 버튼의 경우 추가 마진 설정
            if (i == 1) {
                // 첫 번째 버튼 마진 설정
                buttonParams.setMargins(largeMargin, 0, margin, 0);
            } else if (i == 6) {
                // 마지막 번호 버튼의 경우, 다음 버튼 전에 마진 설정
                buttonParams.setMargins(margin, 0, margin, 0);
            } else {
                // 중간 버튼들의 마진 설정
                buttonParams.setMargins(margin, 0, margin, 0);
            }
            pageButton.setLayoutParams(buttonParams);
            pageButton.setPadding(0, 0, 0, 0); // 상, 우, 하, 좌 패딩을 0으로 설정
            pageButton.setText(String.valueOf(i));
            pageButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); // 텍스트 사이즈를 12sp로 설정
            final int pageNumber = i;
            pageButton.setOnClickListener(v -> loadPageData(pageNumber));
            buttonsLayout.addView(pageButton);
        }

        // "다음" 버튼 추가 및 마진 설정
        Button nextButton = new Button(getContext());
        nextButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.page_btn)); // 배경 설정
        // 가로 100dp, 세로 50dp로 설정 (dp 값을 픽셀로 변환)
        int widthPx = dpToPx(40); // 가로 넓이를 픽셀로 변환
        int heightPx = dpToPx(32); // 세로 넓이를 픽셀로 변환

        LinearLayout.LayoutParams nextButtonParams = new LinearLayout.LayoutParams(
                widthPx, // 가로 넓이를 픽셀 단위로 지정
                heightPx); // 세로 넓이를 픽셀 단위로 지정

        // 첫 번째 버튼의 왼쪽 마진과 마지막 버튼의 오른쪽 마진 조정
        nextButtonParams.setMargins(margin, 0, 50, 0);
        nextButton.setLayoutParams(nextButtonParams);
        nextButton.setPadding(0, 0, 0, 0); // 상, 우, 하, 좌 패딩을 0으로 설정

        nextButton.setText("다음");
        nextButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12); // 텍스트 사이즈 설정
        nextButton.setOnClickListener(v -> loadNextPageGroup());
        buttonsLayout.addView(nextButton);
    }


    private void loadPageData(int page) {
        // 페이지 번호에 따른 데이터 로딩 로직 구현
        // 예: 서버에서 해당 페이지의 뉴스 데이터를 요청하고, 응답 받기
    }

    private void loadNextPageGroup() {
        // "다음" 페이지 그룹 로드하는 로직 구현
        // 예: 현재 페이지 그룹을 업데이트하고, 새로운 페이지 버튼 세트 추가
    }

    // dp를 픽셀 단위로 변환하는 헬퍼 메소드
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
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

        // 첫 번째 리사이클러뷰 아래 페이지 버튼 추가
        addPageButtons(firstView, R.id.page_btn_first);

        // 두 번째 리사이클러뷰 아래 페이지 버튼 추가를 위한 레이아웃 ID 전달
        addPageButtons(firstView, R.id.page_btn_second); // 'page_btn_second'는 두 번째 버튼들을 위한 LinearLayout의 ID

        return firstView;
    }
}
