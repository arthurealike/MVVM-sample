package com.hextorm.sampleproject.data;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.data.local.ArticleLocalDataSource;
import com.hextorm.sampleproject.data.remote.ArticleRemoteDataSource;

import java.util.List;

public class ArticleRepository implements DataSource {

    private final ArticleRemoteDataSource remoteDataSource;
    private final ArticleLocalDataSource localDataSource;

    private MutableLiveData<List<Article>> articleList = new MutableLiveData<>();

    static ArticleRepository INSTANCE;

    public static ArticleRepository getInstance(ArticleRemoteDataSource remoteDataSource,
                                                ArticleLocalDataSource localDataSource) {
        if (INSTANCE == null)
            INSTANCE = new ArticleRepository(remoteDataSource, localDataSource);
        return INSTANCE;

    }

    public ArticleRepository(ArticleRemoteDataSource remoteDataSource, ArticleLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback) {
        remoteDataSource.getPopularArticles(callback);
    }

    @Override
    public void getArticle(@NonNull final LoadArticlesCallback callback) {

    }

    @Override
    public void saveArticles(List<Article> articles) {

    }

    @Override
    public void saveArticle() {

    }
}
