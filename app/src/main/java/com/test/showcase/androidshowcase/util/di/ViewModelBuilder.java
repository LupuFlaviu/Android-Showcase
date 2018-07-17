package com.test.showcase.androidshowcase.util.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelBuilder {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(DaggerAwareViewModelFactory factory);
}
