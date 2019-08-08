package com.hextorm.sampleproject.model;

import com.google.gson.annotations.SerializedName;
import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.article.IArticle;

import java.util.ArrayList;
import java.util.List;

public class ArticleApiResponseWrapper implements IArticle.ConvertForList {

    @SerializedName("results")
    private List<ArticleApiResponse> list= null;

    public List<ArticleApiResponse> getList() {
        return list;
    }

    public void setList(List<ArticleApiResponse> list) {
        this.list = list;
    }

    @Override
    public List<Article> convert() {
        List<Article> articles=new ArrayList<>();
        for(ArticleApiResponse item:list) {
            articles.add(item.convert());
        }
        return articles;
    }
}
