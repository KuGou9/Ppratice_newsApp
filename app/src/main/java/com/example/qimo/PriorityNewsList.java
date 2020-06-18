package com.example.qimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class PriorityNewsList extends AppCompatActivity {

    private List<News> priorityNewsList=new ArrayList<>();

    private Button backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_news_list);
        //        展示toolbat
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 初始化新闻内容
        initPriorityNews();
        //        对新闻内同进行展示
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.priority_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter=new NewsAdapter(priorityNewsList);
        recyclerView.setAdapter(adapter);

        backToMain=(Button) findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent=new Intent(PriorityNewsList.this,MainActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void initPriorityNews() {
        priorityNewsList=null;
//        将重点标记的新闻查询出来
        priorityNewsList= LitePal.where("priority=?","2").find(News.class);

    }
}
