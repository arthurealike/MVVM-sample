package com.hextorm.sampleproject.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hextorm.sampleproject.ArticleEntity;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert
    void insert(ArticleEntity articleEntity);

    @Insert
    void insertAll(List<ArticleEntity> articleEntities);

    @Update
    void update(ArticleEntity articleEntity);

    @Delete
    void delete(ArticleEntity articleEntity);

    @Query("SELECT * FROM article_table WHERE title = :title_name")
    ArticleEntity getArticleByTitle(String title_name);

    @Query("UPDATE article_table SET isFavourite = :completed WHERE title = :title_name")
    void updateCompleted(String title_name, boolean completed);


    @Query("DELETE FROM article_table")
    void deleteAllArticles();

    @Query("SELECT * FROM article_table ORDER BY views DESC")
    List<ArticleEntity> getAllArticles();
}
