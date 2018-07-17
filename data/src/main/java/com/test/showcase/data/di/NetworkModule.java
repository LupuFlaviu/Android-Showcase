package com.test.showcase.data.di;

import com.test.showcase.data.ArticleListService;
import com.test.showcase.data.BuildConfig;
import com.test.showcase.data.interceptor.AuthInterceptor;
import com.test.showcase.data.rx.RxSchedulers;
import com.test.showcase.data.rx.RxSchedulersImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new AuthInterceptor());
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ArticleListService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public ArticleListService provideArticleListService(Retrofit retrofit) {
        return retrofit.create(ArticleListService.class);
    }

    @Provides
    public RxSchedulers provideRxScheduler() {
        return new RxSchedulersImpl();
    }
}
