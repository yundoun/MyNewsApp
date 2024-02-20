package com.example.mynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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

        if (position < 5) {
            // 1번부터 5번 항목까지는 이미지뷰를 보이게 합니다.
            holder.image.setVisibility(View.VISIBLE);
            // 제목 텍스트뷰의 제약 조건을 이미지뷰에 맞춰 조절

            Glide.with(context)
                    .load(newsItem.getUrl()) // 여기서 getUrl()은 Api_NewsItem에서 이미지 URL을 반환하는 메소드입니다.
                    .into(holder.image); // 이미지를 로드할 ImageView 지정

            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holder.title.getLayoutParams();
            params.endToEnd = ConstraintLayout.LayoutParams.UNSET; // 끝 제약을 해제
            params.endToStart = R.id.iv_rank; // 끝을 이미지뷰의 시작으로 설정
            holder.title.setLayoutParams(params); // 변경된 파라미터를 적용
        } else {
            // 6번 이후의 항목에서는 이미지뷰를 숨깁니다.
            holder.image.setVisibility(View.GONE);
            // 제목 텍스트뷰의 제약 조건을 부모 끝에 맞춰 전체 너비를 사용하도록 조절
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holder.title.getLayoutParams();
            params.endToStart = ConstraintLayout.LayoutParams.UNSET; // 시작 제약을 해제
            params.endToEnd = R.id.item_rank; // 끝을 부모의 끝으로 설정
            holder.title.setLayoutParams(params); // 변경된 파라미터를 적용
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rank, title, company, date;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvRank_title);
            company = itemView.findViewById(R.id.tvRank_company);
            date = itemView.findViewById(R.id.tvRank_date);
            image = itemView.findViewById(R.id.iv_rank);
            rank = itemView.findViewById(R.id.tv_ranking);
        }
    }
}
