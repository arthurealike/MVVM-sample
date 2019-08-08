package com.hextorm.sampleproject.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.hextorm.sampleproject.ArticleEntity;

@Database(entities = {ArticleEntity.class}, version = 1, exportSchema = false)
public abstract class ArticleDatabase extends RoomDatabase {

    private static ArticleDatabase INSTANCE;

    public abstract ArticleDao articleDao();

    private static final Object sLock = new Object();

    public static synchronized ArticleDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ArticleDatabase.class, "article_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return INSTANCE;
        }

    }

}
