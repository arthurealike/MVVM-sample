package com.hextorm.sampleproject.data.remote;

import com.hextorm.sampleproject.Constants;
import com.hextorm.sampleproject.article.IArticle;
import com.hextorm.sampleproject.model.ArticleApiResponse;
import com.hextorm.sampleproject.model.ArticleApiResponseWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {

    @GET(Constants.END_POINT)
    Call<ArticleApiResponseWrapper> getPopularArticles(@Query("api-key") String api_key);

    @GET("/svc/search/v2/articlesearch.json?q=election&api-key=")
    Call<ArticleApiResponseWrapper> getSearchedArticles(@Path("api-key") String api_key);

    @GET("/svc/search/v2/articlesearch.json" + Constants.API_KEY)
    Call<ArticleApiResponse> getSpesificArticle(
            @Query("q") String query,
            @Query("fq") String filteredQuery,
            @Query("begin_date") String beginDate,
            @Query("end_date") String endDate,
            @Query("sort") String sort,
            @Query("page") Integer page);
}
