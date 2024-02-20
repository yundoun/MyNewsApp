package com.example.mynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsRvAdapter_Rank extends RecyclerView.Adapter<NewsRvAdapter_Rank.MyViewHolder> {

    private Context context;
    ArrayList<Api_NewsItem> newsItems;

    public NewsRvAdapter_Rank(Context context, ArrayList<Api_NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsRvAdapter_Rank.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃을 확장하고 각 행에 모양을 제공할 곳이며
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_rank, parent, false); // 수정된 부분
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRvAdapter_Rank.MyViewHolder holder, int position) {
        String ranking = String.valueOf(position + 1);
        holder.rank.setText(ranking);
        // 특정 위치에 있는 데이터를 Recycler View의 각 항목에 표시하는데 사용됨
        // 우리가 할당할 곳, 화면에 돌아올 때 각 행에 값을 부여 , 데이터 바인딩
        Api_NewsItem newsItem = newsItems.get(position); // 수정된 부분
        holder.title.setText(newsItem.getTitle());
        holder.company.setText(newsItem.getCompany());
        holder.date.setText(newsItem.getDate());
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rank, title, company, date;
        ImageView url;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvRank_title);
            company = itemView.findViewById(R.id.tvRank_company);
            date = itemView.findViewById(R.id.tvRank_date);
            url = itemView.findViewById(R.id.iv_rank);
            rank = itemView.findViewById(R.id.tv_ranking);
        }
    }
}
