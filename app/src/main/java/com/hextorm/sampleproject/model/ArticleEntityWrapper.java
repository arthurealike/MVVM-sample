package com.hextorm.sampleproject.model;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.ArticleEntity;
import com.hextorm.sampleproject.article.IArticle;

import java.util.ArrayList;
import java.util.List;

public class ArticleEntityWrapper implements IArticle.ConvertForList {
    private List<ArticleEntity> articleEntities = new ArrayList<>();

    public ArticleEntityWrapper(List<ArticleEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }

    public List<ArticleEntity> getArticleEntities() {
        return articleEntities;
    }

    public void setArticleEntities(List<ArticleEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }

    @Override
    public List<Article> convert() {
        List<Article> articles=new ArrayList<>();
        for(ArticleEntity item:articleEntities) {
            articles.add(item.convert());
        }
        return articles;
    }
}
