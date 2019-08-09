package com.hextorm.sampleproject.data.remote;

import com.hextorm.sampleproject.Constants;
import com.hextorm.sampleproject.article.IArticle;
import com.hextorm.sampleproject.model.ArticleApiResponse;
import com.hextorm.sampleproject.model.ArticleApiResponseWrapper;
import com.hextorm.sampleproject.model.SearchedArticleApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {

    @GET("/svc/mostpopular/v2/viewed/7.json")
    Call<ArticleApiResponseWrapper> getPopularArticles(@Query("api-key") String api_key);

    @GET("/svc/search/v2/articlesearch.json")
    Call<SearchedArticleApiResponse> getSearchedArticles(@Query("q") String query, @Query("api-key") String api_key);

}
