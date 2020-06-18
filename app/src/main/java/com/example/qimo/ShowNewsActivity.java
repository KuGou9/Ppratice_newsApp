package com.example.qimo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;
import org.litepal.LitePalDB;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowNewsActivity extends AppCompatActivity {

    private static final String TAG = "";
    private DrawerLayout mDrawerLayout;

    private int to_show_image;
    private String to_show_title;
    private String to_show_author;
    private String to_show_content;

    private ImageView show_news_image;
    private TextView show_news_title;
    private TextView show_news_author;
    private TextView show_news_content;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private FloatingActionButton mark_news;
    private Boolean flag = false;

    News change_news;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
//        获得展示详细内容的各个组件
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        show_news_image = (ImageView) findViewById(R.id.showNewsImage);
        show_news_title = (TextView) findViewById(R.id.showNewsTitle);
        show_news_content=(TextView)findViewById(R.id.showNewsContent);
        show_news_author=(TextView)findViewById(R.id.showNewsAuthor);
        mark_news=(FloatingActionButton) findViewById(R.id.mark_news);
        collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        获取到传过来的新闻标题和内容
        Intent show_intent=getIntent();
        to_show_title=show_intent.getStringExtra("show_news_title");
        to_show_author=show_intent.getStringExtra("show_news_author");
        to_show_content=show_intent.getStringExtra("show_news_content");
//        如果没有图片传入，那么使用水果葡萄的图片作为默认
        to_show_image = show_intent.getIntExtra("show_news_image", R.drawable.grape);
//        展示内容
        setSupportActionBar(toolbar);
        Glide.with(this).load(to_show_image).into(show_news_image);
        show_news_title.setText(to_show_title);
        show_news_author.setText(to_show_author);
        show_news_content.setText(to_show_content);
//        标记重点新闻
        mark_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == false){
                    flag = true;
                    show_news_title.setTextColor(Color.RED);
                    change_news = new News();
                    change_news.setPriority(2);
                    change_news.updateAll("title=?",to_show_title);
                    Toast.makeText(ShowNewsActivity.this, "已将新闻标记为重点新闻", Toast.LENGTH_SHORT).show();
                }else{
                    flag = false;
                    show_news_title.setTextColor(Color.BLACK);
                    change_news = new News();
                    change_news.setToDefault("priority");
                    change_news.updateAll("title=?",to_show_title);
                    Toast.makeText(ShowNewsActivity.this, "已取消重点标记", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                AlertDialog.Builder dialog=new AlertDialog.Builder(ShowNewsActivity.this);
                dialog.setTitle("Warning");
                dialog.setMessage("You hava to login!");
                dialog.setCancelable(false);
                dialog.setPositiveButton("login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(ShowNewsActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("cansel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                break;

            case R.id.delete:
                LitePal.deleteAll(News.class,"title=?",to_show_title);
                Intent intent=new Intent(ShowNewsActivity.this,MainActivity.class);
                Toast.makeText(this, "You delete the news", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

            case R.id.settings:
                Toast.makeText(this, "You click settings", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return true;
    }

}
