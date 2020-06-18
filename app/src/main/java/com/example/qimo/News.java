package com.example.qimo;

import org.litepal.crud.LitePalSupport;

public class News extends LitePalSupport {

    private int priority = 0;
    private String title;
    private String author;
    private String content;
    private int imageId = R.drawable.grape;

    public News(String title, String author, String content) {
        this.title=title;
        this.author=author;
        this.content=content;
    }

    public News() {

    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
