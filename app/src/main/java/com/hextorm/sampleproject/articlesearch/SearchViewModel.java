package com.hextorm.sampleproject.articlesearch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.Injection;
import com.hextorm.sampleproject.MyApplication;
import com.hextorm.sampleproject.data.ArticleRepository;
import com.hextorm.sampleproject.data.DataSource;

import java.util.List;

public class SearchViewModel extends ViewModel implements DataSource.LoadArticlesCallback {

    @Nullable
    MutableLiveData<List<Article>> articleList = new MutableLiveData<>();

    private MutableLiveData<Boolean> progressBarVisibility = new MutableLiveData<>();

    public final ArticleRepository mArticleRepository;

    public SearchViewModel() {
        mArticleRepository = Injection.provideRepository(MyApplication.getAppContext());
        progressBarVisibility.setValue(false);
    }

    void searchArticles(String keyword) {
        progressBarVisibility.setValue(true);
        mArticleRepository.getArticles(this,keyword);
    }

    @Override
    public void onTaskLoaded(@Nullable List<Article> articles) {
        articleList.setValue(articles);
        progressBarVisibility.setValue(false);
    }

    @Override
    public void onDataNotAvailable() {
        articleList.setValue(null);
    }

    public MutableLiveData<List<Article>> getArticleList() {
        return articleList;
    }

    public MutableLiveData<Boolean> getProgressBarVisibility() {
        return progressBarVisibility;
    }
}
