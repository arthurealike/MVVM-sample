package com.hextorm.sampleproject.article;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import android.content.Context;

import android.util.Log;

import com.hextorm.sampleproject.Article;

import com.hextorm.sampleproject.Injection;
import com.hextorm.sampleproject.MyApplication;
import com.hextorm.sampleproject.data.ArticleRepository;

import com.hextorm.sampleproject.data.DataSource;


import java.util.List;

public class ArticleViewModel extends ViewModel implements DataSource.LoadArticlesCallback {

    private MutableLiveData<List<Article>> articleList = new MutableLiveData<>();

    private MutableLiveData<Boolean> articleListState = new MutableLiveData<>();

    private MutableLiveData<Boolean> networkState = new MutableLiveData<>();

    private MutableLiveData<Boolean> progressBarVisibility = new MutableLiveData<>();

    public final Context mContext;

    public final ArticleRepository mArticleRepository;

    public ArticleViewModel() {
        Log.i("ArticleViewModel: "," constructor injection");
        this.mContext = MyApplication.getAppContext();
        this.mArticleRepository = Injection.provideRepository(mContext);

//        networkState.postValue(NetworkState.haveNetworkConnection(mContext));
        articleListState.postValue(false);
        progressBarVisibility.setValue(true);

        mArticleRepository.getArticles(this);

        if (articleList == null)
            Log.d("ViewModelArticle*articleModelList", "null");
    }

    /**
     * Callback functions will be called by Repository -> mArticleRepository
     * @param articles
     */


    @Override
    public void onTaskLoaded(List<Article> articles) {
        articleList.setValue(articles);
        articleListState.setValue(true);
        progressBarVisibility.setValue(false);
    }


    @Override
    public void onDataNotAvailable() {
        articleListState.setValue(false);
    }


    public MutableLiveData<List<Article>> getArticleList() { return articleList;
    }

    public MutableLiveData<Boolean> getProgressBarVisibility() {
        return progressBarVisibility;
    }

    public MutableLiveData<Boolean> getNetworkState() {
        return networkState;
    }
}
