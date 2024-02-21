package com.example.mynewsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment_02_Politics extends Fragment {

    private com.example.mynewsapp.databinding.BaseFragmentLayoutBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = com.example.mynewsapp.databinding.BaseFragmentLayoutBinding.inflate(inflater, container,false);

        binding.tvNewsflash.setText(R.string.Politics_Newsflash);
        binding.tvRank.setText(R.string.Politics_Rank);

        return binding.getRoot();
    }
}