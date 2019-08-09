package com.hextorm.sampleproject.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.Constants;
import com.hextorm.sampleproject.RetroClient;
import com.hextorm.sampleproject.data.DataSource;
import com.hextorm.sampleproject.model.ArticleApiResponseWrapper;
import com.hextorm.sampleproject.model.SearchedArticleApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRemoteDataSource implements DataSource {

    RestAPI restAPI;

    static ArticleRemoteDataSource INSTANCE;

    public static ArticleRemoteDataSource getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ArticleRemoteDataSource();
        return INSTANCE;

    }

    public ArticleRemoteDataSource() {
        restAPI = RetroClient.getRetrofit().create(RestAPI.class);
    }

    public void getArticles(@NonNull final LoadArticlesCallback callback) {

        Log.d("RemoteDataSource: ", "getArticles: " + "has just entered the method");
        restAPI.getPopularArticles(Constants.API_KEY).enqueue(new Callback<ArticleApiResponseWrapper>() {
            @Override
            public void onResponse(Call<ArticleApiResponseWrapper> call, Response<ArticleApiResponseWrapper> response) {
                Log.d("RemoteDataSource: ", "onResponse: " + "entered onResponse method");
                Log.d("RemoteDataSource: Url : ", restAPI.getPopularArticles(Constants.API_KEY).request().url().toString());
                callback.onTaskLoaded(response.body().convert());

                if (response.body() == null || response.body().getList().isEmpty() || !response.isSuccessful()) {
                    Log.e("RemoteDataSource: ", " There will be exist some problems");
                }
                if (response.isSuccessful()) {
                    // apiResponseModel = response.body();
                    Log.d("RemoteDataSource: ", "on response.isSuccessful");
                }
            }

            @Override
            public void onFailure(Call<ArticleApiResponseWrapper> call, Throwable t) {
                Log.e("RemoteDataSource: ", "onFailure: " + "data can not be loaded.");
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback,String keyword) {

        restAPI.getSearchedArticles(keyword, Constants.API_KEY).enqueue(new Callback<SearchedArticleApiResponse>() {
            @Override
            public void onResponse(Call<SearchedArticleApiResponse> call, Response<SearchedArticleApiResponse> response) {
                Log.d("RemoteDataSource: ", "onResponse: " + "entered onResponse method");
                Log.d("RemoteDataSource: Url : ", restAPI.getSearchedArticles("recep", Constants.API_KEY).request().url().toString());
                callback.onTaskLoaded(response.body().getResponse().convert());
            }

            @Override
            public void onFailure(Call<SearchedArticleApiResponse> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void getArticle(@NonNull LoadArticlesCallback callback) {

    }

    @Override
    public void saveArticles(List<Article> articles) {
        return;
    }

    @Override
    public void saveArticle() {
        return;
    }
}
