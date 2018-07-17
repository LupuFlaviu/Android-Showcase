package com.test.showcase.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesPreviewModel {

    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<ArticlePreviewModel> articles;

    public ArticlesPreviewModel(int numResults, List<ArticlePreviewModel> listOfArticlePreviewModel) {
        this.numResults = numResults;
        this.articles = listOfArticlePreviewModel;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<ArticlePreviewModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlePreviewModel> articles) {
        this.articles = articles;
    }
}
