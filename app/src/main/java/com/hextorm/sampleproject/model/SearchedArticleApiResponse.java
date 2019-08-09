package com.hextorm.sampleproject.model;

import com.google.gson.annotations.SerializedName;
import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.article.IArticle;

import java.util.ArrayList;
import java.util.List;

public class SearchedArticleApiResponse implements IArticle.ConvertForItem {

    @SerializedName("response")
    private Response response;

    public SearchedArticleApiResponse(String status, String copyright, Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public Article convert() {
        return null;
    }

    public class Response implements IArticle.ConvertForList {
        @SerializedName("docs")
        private List<Doc> docs = null;

        public Response(List<Doc> docs) {
            this.docs = docs;
        }

        public List<Doc> getDocs() {
            return docs;
        }

        public void setDocs(List<Doc> docs) {
            this.docs = docs;
        }

        @Override
        public List<Article> convert() {
            List<Article> articles = new ArrayList<>();
            for(Doc doc:docs) {
                articles.add(new Article(101,doc._abstract,"searched_article",404,doc.webUrl,"none",doc.pubDate,false));
            }
            return articles;
        }
    }

    public class Doc {
        @SerializedName("web_url")
        private String webUrl;

        @SerializedName("snippet")
        private String snippet;

        @SerializedName("abstract")
        private String _abstract;

        @SerializedName("pub_date")
        private String pubDate;

        public Doc(String webUrl, String snippet, String leadParagraph, String _abstract, String pubDate) {
            this.webUrl = webUrl;
            this.snippet = snippet;
            this._abstract = _abstract;
            this.pubDate = pubDate;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public String getSnippet() {
            return snippet;
        }

        public String get_abstract() {
            return _abstract;
        }

        public String getPubDate() {
            return pubDate;
        }

    }

}