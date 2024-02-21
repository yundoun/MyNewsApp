package com.example.mynewsapp;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNewsFragment extends Fragment {
    private List<Button> pageButtonsFirst = new ArrayList<>(); // 페이지 버튼 리스트
    private List<Button> pageButtonsSecond = new ArrayList<>(); // 페이지 버튼 리스트
    protected int currentPageNumber = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPageButtons(view, R.id.page_btn_first, pageButtonsFirst); // 버튼을 추가하는 메소드 호출
        addPageButtons(view, R.id.page_btn_second, pageButtonsSecond);
    }

    // 각 버튼 클릭 시, 적절한 동작을 정의합니다 (다음 페이지 그룹 로딩 또는 특정 페이지 데이터 로딩).
    protected void updatePageSelection(int pageNumber, List<Button> pageButtons) {
        for (Button btn : pageButtons) {
            btn.setSelected(pageButtons.indexOf(btn) + 1 == pageNumber);
        }
        currentPageNumber = pageNumber; // 현재 페이지 번호 업데이트
        loadPageData(pageNumber); // 페이지 데이터 로딩
    }

    // 버튼을 동적으로 생성합니다. 이때, ContextCompat을 통해 리소스에 안전하게 접근합니다.
    protected Button createButton(String text, int width, int height, int margin) {
        Button button = new Button(getContext());
        button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.page_btn)); // 배경 설정
        button.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.page_btn_selector)); // 텍스트 색상 selector 적용

        // 모든 버튼에 대해 동일한 마진 설정 적용
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(margin, 0, margin, 0); // 시작과 끝 마진 동일하게 설정
        button.setLayoutParams(params);
        button.setPadding(0, 0, 0, 0);
        button.setText(text);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12); // 텍스트 사이즈 설정
        button.setOnClickListener(v -> {
            if ("다음".equals(text)) {
                loadNextPageGroup();
            } else {
                try {
                    int pageNumber = Integer.parseInt(text);
                    loadPageData(pageNumber);
                } catch (NumberFormatException e) {
                    // 번호 변환 시 오류 처리 (적절한 오류 처리 추가)
                }
            }
        });
        return button;
    }

    // 페이지 버튼들을 ViewGroup에 추가합니다.
    protected void addPageButtons(View rootView, int buttonsLayoutId, List<Button> pageButtons) {
        LinearLayout buttonsLayout = rootView.findViewById(buttonsLayoutId);
        buttonsLayout.removeAllViews(); // 기존에 추가된 버튼 제거
        pageButtons.clear(); // 페이지 버튼 리스트 초기화

        int pageButtonWidth = dpToPx(35); // 가로 넓이를 픽셀로 변환
        int pageButtonHeight = dpToPx(25); // 세로 넓이를 픽셀로 변환
        int buttonMargin = dpToPx(1);


        for (int i = 1; i <= 6; i++) {
            Button pageButton = createButton(String.valueOf(i), pageButtonWidth, pageButtonHeight, buttonMargin);
            final int pageNumber = i;
            pageButton.setOnClickListener(v -> {
                updatePageSelection(pageNumber, pageButtons);
            });
            buttonsLayout.addView(pageButton);
            pageButtons.add(pageButton);
        }

        Button nextButton = createButton("다음", pageButtonWidth, pageButtonHeight, buttonMargin);
        nextButton.setOnClickListener(v -> {
            if (currentPageNumber < pageButtons.size()) {
                updatePageSelection(currentPageNumber + 1, pageButtons); // 다음 페이지로 이동
            } else {
                updatePageSelection(1, pageButtons); // 마지막 페이지에서 "다음"을 클릭하면 첫 페이지로 이동
            }
        });
        buttonsLayout.addView(nextButton);
        updatePageSelection(1, pageButtons); // 초기 선택 상태를 1로 설정
    }
    protected int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    protected abstract void loadPageData(int page);

    protected abstract void loadNextPageGroup();

}
