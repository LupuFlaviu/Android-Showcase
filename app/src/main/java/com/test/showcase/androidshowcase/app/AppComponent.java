package com.test.showcase.androidshowcase.app;

import com.test.showcase.androidshowcase.ActivitiesModule;
import com.test.showcase.androidshowcase.util.di.ViewModelBuilder;
import com.test.showcase.data.di.NetworkModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(
        modules = {AndroidSupportInjectionModule.class,
                AppModule.class,
                NetworkModule.class,
                ViewModelBuilder.class,
                ActivitiesModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App>{}
}