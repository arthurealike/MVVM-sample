package com.hextorm.sampleproject.data.local;

import android.support.annotation.NonNull;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.ArticleEntity;
import com.hextorm.sampleproject.article.IArticle;
import com.hextorm.sampleproject.data.DataSource;
import com.hextorm.sampleproject.model.ArticleEntityWrapper;

import java.util.ArrayList;
import java.util.List;

public class ArticleLocalDataSource implements DataSource {

    private static volatile ArticleLocalDataSource INSTANCE;

    ArticleDao articleDao;

    public ArticleLocalDataSource(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public static ArticleLocalDataSource getInstance(ArticleDao articleDao) {
        if (INSTANCE == null) {
            synchronized (ArticleLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ArticleLocalDataSource(articleDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback) {
       List<ArticleEntity> articleEntities = new ArrayList<>();
       articleEntities = articleDao.getAllArticles();
       if(articleEntities.isEmpty()) {
           callback.onDataNotAvailable();
           return;
       }
       ArticleEntityWrapper articleEntityWrapper = new ArticleEntityWrapper(articleEntities);
       callback.onTaskLoaded(articleEntityWrapper.convert());

    }

    @Override
    public void getArticle(@NonNull final LoadArticlesCallback callback) {


    }

    @Override
    public void saveArticles(List<Article> articles) {
        List<ArticleEntity> articleEntities = new ArrayList<>();
        for (Article article : articles) {
            articleEntities.add(new ArticleEntity(article.getmId(),
                    article.getmTitle(), article.getmType()
                    , article.getmViews(), article.ismIsFavourite()));
        }
        articleDao.insertAll(articleEntities);
    }

    @Override
    public void saveArticle() {

    }
}
