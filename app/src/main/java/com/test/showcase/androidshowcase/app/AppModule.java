package com.test.showcase.androidshowcase.app;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public Context providesContext(App application) {
        return application.getApplicationContext();
    }
}