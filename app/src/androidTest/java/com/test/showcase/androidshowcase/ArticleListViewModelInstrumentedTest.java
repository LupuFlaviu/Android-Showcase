package com.test.showcase.androidshowcase;

import android.app.Instrumentation;
import android.support.test.runner.AndroidJUnit4;

import com.test.showcase.data.ArticleListService;
import com.test.showcase.data.ArticlesRemoteDataSource;
import com.test.showcase.data.rx.RxSchedulersImpl;
import com.test.showcase.androidshowcase.articleList.ArticleListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Single;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ArticleListViewModelInstrumentedTest {

    @Mock
    private ArticleListService mockArticleListService;

    private ArticlesRemoteDataSource mockArticlesRemoteDataSource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockArticlesRemoteDataSource = new ArticlesRemoteDataSource(mockArticleListService, new RxSchedulersImpl());
    }

    @Test
    public void initialLoadIsSetAsExpected() {
        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.getArticlesPreviewModel()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();
        Instrumentation instrumentation = getInstrumentation();


        instrumentation.runOnMainSync(() ->
                viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource)));

        instrumentation.waitForIdleSync();


        Mockito.verify(mockArticleListService).getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD);

        assertTrue(!viewModel.get().isLoading.get());

        assertTrue("size of articles list is different",
                viewModel.get().articleList.getValue().size() == TestData.getListOfArticlePreviewModel().size());
    }


    @Test
    public void toggleFilterArea() {
        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.getArticlesPreviewModel()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();
        Instrumentation instrumentation = getInstrumentation();


        instrumentation.runOnMainSync(() -> {
            viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource));

            viewModel.get().toggleFilterZoneVisibility();
        });
        instrumentation.waitForIdleSync();


        assertTrue(viewModel.get().filterZoneIsVisible.get());
    }

    @Test
    public void setInvalidSectionPosition() {
        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.getArticlesPreviewModel()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();
        Instrumentation instrumentation = getInstrumentation();


        instrumentation.runOnMainSync(() -> {
            viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource));

            viewModel.get().setArticleSectionPosition(4);
        });
        instrumentation.waitForIdleSync();


        assertTrue(viewModel.get().getCurrentArticleSection().equals(ArticleListService.DEFAULT_SECTION));
    }

    @Test
    public void setValidSectionPosition() {
        int sectionPosition = 3;
        String section = ArticleListService.SECTIONS.get(sectionPosition);

        Mockito.when(mockArticleListService.getArticles(section, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.getArticlesPreviewModel()));

        Mockito.when(mockArticleListService.getArticles(ArticleListService.DEFAULT_SECTION, ArticleListService.DEFAULT_PERIOD))
                .thenReturn(Single.just(TestData.getArticlesPreviewModel()));

        AtomicReference<ArticleListViewModel> viewModel = new AtomicReference<>();
        Instrumentation instrumentation = getInstrumentation();


        instrumentation.runOnMainSync(() -> {
            viewModel.set(new ArticleListViewModel(mockArticlesRemoteDataSource));

            viewModel.get().setArticleSectionPosition(sectionPosition);
        });
        instrumentation.waitForIdleSync();


        assertTrue(viewModel.get().getCurrentArticleSection().equals(section));
    }
}
