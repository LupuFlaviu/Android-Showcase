package com.test.showcase.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticlePreviewModel {
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("byline")
    @Expose
    private String byline;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("published_date")
    @Expose
    private String publishedDate;

    public ArticlePreviewModel(String url, String title, String date, String author) {
        this.url = url;
        this.title = title;
        this.publishedDate = date;
        this.byline = author;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
