package com.test.showcase.androidshowcase.articleList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.test.showcase.data.ArticleListService;
import com.test.showcase.data.ArticlesRemoteDataSource;
import com.test.showcase.data.model.ArticlePreviewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArticleListViewModel extends ViewModel {

    private ArticlesRemoteDataSource articlesRemoteDataSource;
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public ObservableBoolean filterZoneIsVisible = new ObservableBoolean(false);
    public MutableLiveData<List<ArticlePreviewModel>> articleList = new MutableLiveData<>();
    public List<String> articleSections = ArticleListService.SECTIONS;

    private String currentArticleSection = ArticleListService.DEFAULT_SECTION;

    @Inject
    public ArticleListViewModel(ArticlesRemoteDataSource articlesRemoteDataSource) {
        this.articlesRemoteDataSource = articlesRemoteDataSource;

        articleList.setValue(new ArrayList<>());

        loadData(currentArticleSection, ArticleListService.DEFAULT_PERIOD);
    }


    private void loadData(String section, String period) {
        isLoading.set(true);

        articlesRemoteDataSource.getListOfArticles(section, period)
                .doOnSuccess(it -> {
                    articleList.setValue(it);
                    isLoading.set(false);
                })
                .doOnError(it -> isLoading.set(false))
                .subscribe();
    }

    public void toggleFilterZoneVisibility() {
        filterZoneIsVisible.set(!filterZoneIsVisible.get());
    }

    public void setArticleSectionPosition(int position) {
        if (position >= ArticleListService.SECTIONS.size()) {
            return;
        }

        String newArticleSection = ArticleListService.SECTIONS.get(position);
        if (newArticleSection == currentArticleSection) {
            return;
        }

        currentArticleSection = newArticleSection;

        loadData(currentArticleSection, ArticleListService.DEFAULT_PERIOD);
    }

    public String getCurrentArticleSection() {
        return currentArticleSection;
    }

    @Override
    protected void onCleared() {
        articleList.setValue(null);

        super.onCleared();
    }
}