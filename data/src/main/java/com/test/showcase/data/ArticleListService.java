package com.test.showcase.data;

import com.test.showcase.data.model.ArticlesPreviewModel;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleListService {
    String BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/";
    String DEFAULT_SECTION = "all-sections";
    String  DEFAULT_PERIOD = "7";
    List<String> SECTIONS = Arrays.asList(DEFAULT_SECTION, "Arts", "Business Day", "Fashion & Style");

    @GET("{section}/{period}.json")
    Single<ArticlesPreviewModel> getArticles(@Path("section")String section, @Path("period")String period);

}
