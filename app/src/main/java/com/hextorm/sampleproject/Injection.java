package com.hextorm.sampleproject;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hextorm.sampleproject.data.ArticleRepository;
import com.hextorm.sampleproject.data.local.ArticleDatabase;
import com.hextorm.sampleproject.data.local.ArticleLocalDataSource;
import com.hextorm.sampleproject.data.remote.ArticleRemoteDataSource;

public class Injection {
   public static ArticleRepository provideRepository(@NonNull Context context) {
       ArticleDatabase database = ArticleDatabase.getInstance(context);
       return ArticleRepository.getInstance(ArticleRemoteDataSource.getInstance(), ArticleLocalDataSource.getInstance(database.articleDao()));
   }
}
