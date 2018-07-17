package com.test.showcase.androidshowcase.articleList;

import com.test.showcase.data.model.ArticlePreviewModel;

public class ArticleListItemViewModel {
    public String title;
    public String author;
    public String date;
    public String url;

    public ArticleListItemViewModel(ArticlePreviewModel articlePreviewModel) {
        title = articlePreviewModel.getTitle();
        author = articlePreviewModel.getByline();
        date = articlePreviewModel.getPublishedDate();
        url = articlePreviewModel.getUrl();
    }
}
