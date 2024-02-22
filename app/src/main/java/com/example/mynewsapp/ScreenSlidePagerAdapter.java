package com.example.mynewsapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    public ScreenSlidePagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragment_01_General();
            case 1:
                return new Fragment_02_Politics();
            case 2:
                return new Fragment_03_Economics();
            case 3:
                return new Fragment_04_Society();
            case 4:
                return new Fragment_05_Culture();
            case 5:
                return new Fragment_06_World();
            case 6:
                return new Fragment_07_TechScience();
            default:
                return null; // 이 경우가 발생하지 않도록 주의
        }


    }



    @Override
    public int getItemCount() {
        return 7; // 탭의 총 개수
    }
}