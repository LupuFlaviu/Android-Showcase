package com.test.showcase.data;

import com.test.showcase.data.model.ArticlePreviewModel;
import com.test.showcase.data.model.ArticlesPreviewModel;
import com.test.showcase.data.rx.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ArticlesRemoteDataSource {

    private ArticleListService articleListService;
    private RxSchedulers rxSchedulers;

    @Inject
    public ArticlesRemoteDataSource(ArticleListService articleListService, RxSchedulers rxSchedulers){
        this.articleListService = articleListService;
        this.rxSchedulers = rxSchedulers;
    }

    public Single<List<ArticlePreviewModel>> getListOfArticles(String section, String period) {

        return articleListService.getArticles(section, period)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.main())
                .map(ArticlesPreviewModel::getArticles);
    }
}
