package com.example.qimo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class AddNewsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private EditText titleEdit;

    private EditText authorEdit;

    private EditText contentdEdit;

    private Button submit;

    private Button cansel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        //        展示toolbat
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submit=(Button)findViewById(R.id.add_news);
        cansel=(Button)findViewById(R.id.add_news_cansel);
        titleEdit = (EditText) findViewById(R.id.add_title);
        authorEdit = (EditText) findViewById(R.id.add_author);
        contentdEdit = (EditText) findViewById(R.id.add_content);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_add = titleEdit.getText().toString();
                String author_add = authorEdit.getText().toString();
                String content_add = contentdEdit.getText().toString();
//                判断输入是否为空
                if(title_add.equals("") || author_add.equals("") || content_add.equals("")){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(AddNewsActivity.this);
                    dialog.setTitle("Warning");
                    dialog.setMessage("请完整输入新闻信息");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                }else{
                    News news=new News();
                    news.setTitle(title_add);
                    news.setAuthor(author_add);
                    news.setContent(content_add);
                    news.save();
                    Toast.makeText(AddNewsActivity.this, "添加新闻成功", Toast.LENGTH_SHORT).show();
//                    finish();
                    Intent intent=new Intent(AddNewsActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
//        点击取消按钮之后返回新闻主界面
        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent=new Intent(AddNewsActivity.this,MainActivity.class);
//                startActivity(intent);
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
                AlertDialog.Builder dialog=new AlertDialog.Builder(AddNewsActivity.this);
                dialog.setTitle("Warning");
                dialog.setMessage("You hava to login!");
                dialog.setCancelable(false);
                dialog.setPositiveButton("login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(AddNewsActivity.this,LoginActivity.class);
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
                Toast.makeText(this, "You click Delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Toast.makeText(this, "You click settings", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return true;
    }
}
