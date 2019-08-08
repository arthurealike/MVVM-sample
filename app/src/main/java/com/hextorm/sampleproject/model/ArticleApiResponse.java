package com.hextorm.sampleproject.model;

import com.google.gson.annotations.SerializedName;
import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.article.IArticle;

import java.util.List;

//RESULT
public class ArticleApiResponse implements IArticle.ConvertForItem {

    @SerializedName("id")
    private final long mId;

    @SerializedName("title")
    private final String mTitle;

    @SerializedName("type")
    private final String mType;

    @SerializedName("views")
    private final int mViews;

    @SerializedName("published_date")
    private final String mDate;

    @SerializedName("abstract")
    private final String mAbstract;

    @SerializedName("url")
    private final String mUrl;

    @SerializedName("media")
    private final List<Medium> media;

    private boolean mIsFavourite;

    public ArticleApiResponse(int mId, String mTitle, String mType, int mViews, String mDate, String mAbstract, String mUrl, List<Medium> media) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mType = mType;
        this.mViews = mViews;
        this.mDate = mDate;
        this.mAbstract = mAbstract;
        this.mUrl = mUrl;
        this.media = media;
        this.mIsFavourite = false;
    }

    public long getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmType() {
        return mType;
    }

    public boolean ismIsFavourite() {
        return mIsFavourite;
    }

    public void setmIsFavourite(boolean mIsFavourite) {
        this.mIsFavourite = mIsFavourite;
    }

    public String getmUrl() {
        return mUrl;
    }

    public int getmViews() {
        return mViews;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmAbstract() {
        return mAbstract;
    }

    public List<Medium> getMedia() {
        return media;
    }

    @Override
    public Article convert() {
        return new Article((int) mId, mTitle, mType, mViews, mUrl, media.get(0).getMediaMetadata().get(2).getUrl(), mDate, mIsFavourite);
    }

    public class Medium {
        @SerializedName("media-metadata")
        private final List<MediaMetaData> mediaMetadata;

        public Medium(List<MediaMetaData> mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
        }

        public final List<MediaMetaData> getMediaMetadata() {
            return mediaMetadata;
        }
    }

    public class MediaMetaData {
        @SerializedName("url")
        private final String url;

        public MediaMetaData(String url) {
            this.url = url;
        }


        public String getUrl() {
            return url;
        }
    }
}


