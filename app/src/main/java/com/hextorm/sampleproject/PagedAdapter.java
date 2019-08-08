package com.hextorm.sampleproject;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.ViewGroup;

import com.hextorm.sampleproject.model.ArticleApiResponseWrapper;

/**
 * I would use it rather than initial one but my Api is not compatible with pagedAdapter
 */

public class PagedAdapter extends PagedListAdapter<ArticleApiResponseWrapper,BaseViewHolder> {

    protected PagedAdapter(@NonNull DiffUtil.ItemCallback<ArticleApiResponseWrapper> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }
}
