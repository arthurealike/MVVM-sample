package com.hextorm.sampleproject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hextorm.sampleproject.article.IArticle;

@Entity(tableName = "article_table")
public class ArticleEntity implements IArticle.ConvertForItem {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "article_id")
    private final int mId;

    @Nullable
    @ColumnInfo(name = "title")
    private final String mTitle;

    @Nullable
    @ColumnInfo(name = "type")
    private final String mType;

    @Nullable
    @ColumnInfo(name = "views")
    private final int mViews;

    @Nullable
    @ColumnInfo(name = "isFavourite")
    private boolean mIsFavourite;

    @Ignore
    public ArticleEntity(int mId, String title, String type, int views) {
        this(mId,title,type,views,false);
    }

    public ArticleEntity(@NonNull int mId, @Nullable String title,
                         @Nullable String type, int views , boolean isFavourite) {
        this.mId = mId;
        this.mTitle =title;
        this.mType =type;
        this.mViews=views;
        this.mIsFavourite=isFavourite;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
    }

    public boolean getIsFavourite() {
        return mIsFavourite;
    }

    public int getViews() {
        return mViews;
    }

    @Override
    public Article convert() {
        return new Article((int) mId, mTitle, mType, mViews,null,null,null,mIsFavourite);
    }
}
