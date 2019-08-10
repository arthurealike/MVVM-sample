package com.hextorm.sampleproject.articlesearch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.R;
import com.hextorm.sampleproject.RecyclerAdapter;

import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;

    SearchViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, null);
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        recyclerView = view.findViewById(R.id.search_recyclerView);
        progressBar = view.findViewById(R.id.search_progressBar);

        layoutManager = new LinearLayoutManager(getContext());
        setUpRecyclerView(getContext());

        Observer<Boolean> progressBarVisibilityObserver = (@Nullable Boolean visibility) -> {
            if (visibility == null) return;
            progressBar.setVisibility(View.VISIBLE);
            if (!visibility) progressBar.setVisibility(View.INVISIBLE);
        };

        Observer<List<Article>> listObserver = (@Nullable List<Article> articles) -> {
            adapter.setArticleList(articles);
        };

        viewModel.getProgressBarVisibility().observe(this, progressBarVisibilityObserver);
        viewModel.getArticleList().observe(this, listObserver);

        return view;
    }

    public void loadArticleList(String keyword) {
        viewModel.searchArticles(keyword);
    }

    void setUpRecyclerView(Context context) {
        //Create Adapter && bind it
        adapter = new RecyclerAdapter(context, null, true);

        adapter.setHasStableIds(true);

        recyclerView.getItemAnimator().setRemoveDuration(0);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.getItemAnimator().setAddDuration(0);
        recyclerView.getItemAnimator().setMoveDuration(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setAdapter(adapter);
    }
}
