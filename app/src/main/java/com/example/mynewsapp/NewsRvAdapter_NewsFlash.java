package com.example.mynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsapp.databinding.RvItemBinding;

import java.util.ArrayList;

public class NewsRvAdapter_NewsFlash extends RecyclerView.Adapter<NewsRvAdapter_NewsFlash.MyViewHolder> {
    private Context context;
    private ArrayList<Api_NewsItem> newsItems;
    private final DetailInterface detailInterface;

    public NewsRvAdapter_NewsFlash(Context context, ArrayList<Api_NewsItem> newsItems, DetailInterface detailInterface) {
        this.context = context;
        this.newsItems = newsItems;
        this.detailInterface = detailInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃을 확장하고 각 행에 모양을 제공할 곳이며 LayoutInflater를 사용하여 뷰 바인딩 인스턴스를 생성합니다.
        RvItemBinding binding = RvItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding, detailInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // 우리가 할당할 곳, 화면에 돌아올 때 각 행에 값을 부여 , 데이터 바인딩
        Api_NewsItem newsItem = newsItems.get(position); // 수정된 부분
        holder.binding.tvTitle.setText(newsItem.getTitle());
        holder.binding.tvCompany.setText(newsItem.getCompany());
        holder.binding.tvDate.setText(newsItem.getDate());
    }

    @Override
    public int getItemCount() {
        // 총 항목 수
        return newsItems.size(); // 수정된 부분
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RvItemBinding binding;

        public MyViewHolder(RvItemBinding binding, DetailInterface detailInterface) {
            super(binding.getRoot());
            this.binding = binding;


            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (detailInterface != null){

                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            detailInterface.onItemClick(pos);
                        }

                    }
                }
            });
        }
    }
}
