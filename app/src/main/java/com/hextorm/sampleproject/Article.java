package com.hextorm.sampleproject;

public class Article {

    private int mId;

    private String mTitle;

    private String mType;

    private int mViews;

    private boolean mIsFavourite;

    private String mUrl;

    private String mPhotoUrl;

    private String mDate;

    public Article(int mId, String mTitle, String mType, int mViews, String mUrl, String mPhotoUrl, String mDate, boolean mIsFavourite) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mType = mType;
        this.mViews = mViews;
        this.mUrl = mUrl;
        this.mPhotoUrl = mPhotoUrl;
        this.mDate = mDate;
        this.mIsFavourite = false;
    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmType() {
        return mType;
    }

    public int getmViews() {
        return mViews;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmPhotoUrl() {
        return mPhotoUrl;
    }

    public String getmDate() {
        return mDate;
    }

    public boolean ismIsFavourite() {
        return mIsFavourite;
    }
}
