package com.test.showcase.androidshowcase;

import android.arch.lifecycle.ViewModel;

import com.test.showcase.androidshowcase.articleList.ArticlesListActivity;
import com.test.showcase.androidshowcase.articleList.ArticleListViewModel;
import com.test.showcase.androidshowcase.util.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector
    public abstract ArticlesListActivity mainActivity();

    @Binds
    @IntoMap
    @ViewModelKey(value=ArticleListViewModel.class)
    public abstract ViewModel bindMainViewModel(ArticleListViewModel viewModel);
}
