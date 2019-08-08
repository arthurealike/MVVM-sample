package com.hextorm.sampleproject.article;

import com.hextorm.sampleproject.Article;

import java.util.List;

public interface IArticle {
    //This method converts ArticleEntity(Room) or
    // ArticleApiResponse(Retrofit2) into Article.class
    interface ConvertForList {
        List<Article> convert();
    }

    interface ConvertForItem {
        Article convert();
    }
}


