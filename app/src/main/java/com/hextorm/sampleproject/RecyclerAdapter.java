package com.hextorm.sampleproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hextorm.sampleproject.article.ArticleViewHolder;
import com.hextorm.sampleproject.model.ArticleApiResponse;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    Context context;

    @Nullable
    private List<Article> articleList = new ArrayList<>();

    public RecyclerAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("onCreateViewHolder","entered");
        Context context = viewGroup.getContext();
        View itemLayout;
        if(Constants.isAlternative) {
             itemLayout = LayoutInflater.from(context).inflate(R.layout.viewholder_alternative, viewGroup, false);
        }
        else {
             itemLayout = LayoutInflater.from(context).inflate(R.layout.viewholder_activity, viewGroup, false);

        }
        BaseViewHolder viewHolder = new ArticleViewHolder(itemLayout, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, final int position) {
        baseViewHolder.bind(articleList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return articleList.get(position).getmId();
    }

    @Override
    public int getItemCount() {
        if (articleList == null || articleList.size() < 1)
            return 0;

        return articleList.size();
    }

}
