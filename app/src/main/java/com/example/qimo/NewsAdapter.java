package com.example.qimo;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContent;

    private List<News> mNewsList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView newsView;
        ImageView newsImage;
        TextView newstitle;

        public ViewHolder(View view){
            super(view);
            newsView=(CardView)view;
            newsImage=(ImageView)view.findViewById(R.id.news_image);
            newstitle=(TextView)view.findViewById(R.id.newsTitle);
        }
    }

    public NewsAdapter(List<News> newsList) {
        mNewsList=newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContent == null){
            mContent = parent.getContext();
        }
        View view= LayoutInflater.from(mContent).inflate(R.layout.news_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
//        为每一条新闻添加点击事件
        holder.newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转到新闻展示页面
                int position=holder.getAdapterPosition();
                News news=mNewsList.get(position);
                Toast.makeText(v.getContext(), "you click view"+news.getTitle(), Toast.LENGTH_SHORT).show();
//                int news_priority=news.getPriority();
                int news_imageId = news.getImageId();
                String news_title=news.getTitle();
                String news_author=news.getAuthor();
                String news_content=news.getContent();
                Intent intent = new Intent(v.getContext(), ShowNewsActivity.class);
//                intent.putExtra("show_news_priority",news_priority);
                intent.putExtra("show_news_image",news_imageId);
                intent.putExtra("show_news_title",news_title);
                intent.putExtra("show_news_author",news_author);
                intent.putExtra("show_news_content",news_content);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news=mNewsList.get(position);
        holder.newstitle.setText(news.getTitle());
        Glide.with(mContent).load(news.getImageId()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }


}
