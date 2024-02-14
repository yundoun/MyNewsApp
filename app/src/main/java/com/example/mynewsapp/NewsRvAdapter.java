package com.example.mynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsRvAdapter extends RecyclerView.Adapter<NewsRvAdapter.MyViewHolder> {

    private Context context;
    ArrayList<Api_NewsItem> newsItems;

    public NewsRvAdapter(Context context, ArrayList<Api_NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃을 확장하고 각 행에 모양을 제공할 곳이며
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_01_general, parent, false); // 수정된 부분
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRvAdapter.MyViewHolder holder, int position) {
       // 특정 위치에 있는 데이터를 Recycler View의 각 항목에 표시하는데 사용됨
        // 우리가 할당할 곳, 화면에 돌아올 때 각 행에 값을 부여 , 데이터 바인딩
        Api_NewsItem newsItem = newsItems.get(position); // 수정된 부분
        holder.tv_title.setText(newsItem.getTitle());
        holder.tv_company.setText(newsItem.getCompany());
        holder.tv_date.setText(newsItem.getDate());
    }

    @Override
    public int getItemCount() {
        // 총 항목 수
        return newsItems.size(); // 수정된 부분
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // RecyclerView 행 레이아웃의 모든 뷰를 가져와서 변수에 할당하는 것 (=onCreat)
        // = 데이터를 뷰에 바인딩할 때 매번 findViewById를 호출하여 성능이 저하되는 것을 방지할 수 있습니다.

        TextView tv_title, tv_company, tv_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_company = itemView.findViewById(R.id.tv_company);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
