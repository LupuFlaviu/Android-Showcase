package com.test.showcase.androidshowcase.app;

import com.test.showcase.androidshowcase.app.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

}