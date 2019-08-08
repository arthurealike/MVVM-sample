package com.hextorm.sampleproject.data;

import android.support.annotation.NonNull;

import com.hextorm.sampleproject.Article;

import java.util.List;

public interface DataSource {

    void getArticles(@NonNull final LoadArticlesCallback callback);
    void getArticle(@NonNull final LoadArticlesCallback callback);

    void saveArticles(List<Article> articles);
    void saveArticle();

    /**
     * Callback Interface for Viewmodels.
     */
    public interface LoadArticlesCallback {

        void onTaskLoaded(List<Article> articles);

        void onDataNotAvailable();
    }

    public interface GetArticlesCallback {

        void onTasksLoaded(Article article);

        void onDataNotAvailable();
    }

}
