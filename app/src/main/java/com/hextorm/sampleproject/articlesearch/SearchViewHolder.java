package com.hextorm.sampleproject.articlesearch;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.BaseViewHolder;
import com.hextorm.sampleproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewHolder extends BaseViewHolder<Article> {
    @LayoutRes
    public static final int LAYOUT = R.layout.viewholder_activity;

    public static Context context;

    @BindView(R.id.vh3_tvSnippet)
    TextView tvSnippet;
    @BindView(R.id.vh3_tvDate)
    TextView tvDate;
    @BindView(R.id.vh3_tvUrl)
    TextView tvUrl;

    public SearchViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Article element) {
        tvSnippet.setText(element.getmTitle());
        tvDate.setText(element.getmDate());
        tvUrl.setText(element.getmUrl());
    }
}
