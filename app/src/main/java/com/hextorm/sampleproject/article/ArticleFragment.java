package com.hextorm.sampleproject.article;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.Constants;
import com.hextorm.sampleproject.Injection;
import com.hextorm.sampleproject.MyApplication;
import com.hextorm.sampleproject.R;
import com.hextorm.sampleproject.RecyclerAdapter;
import com.hextorm.sampleproject.model.ArticleApiResponse;

import java.util.List;

public class ArticleFragment extends Fragment {

    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArticleViewModel viewModel;

    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,null);
        Log.d("ArticleFragment: ", "onCreateView");
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        layoutManager = new LinearLayoutManager(getContext());

        viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        Observer<List<Article>> recyclerObserver = (@Nullable List<Article> newDataSet) -> {
            adapter.setArticleList(newDataSet);
        };

        Observer<Boolean> progressBarObserver = (Boolean visibility) -> {
            if (visibility) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
                // binding.swiperefresh.setRefreshing(false);
            }
        };

        viewModel.getArticleList().observe(this, recyclerObserver);
        viewModel.getProgressBarVisibility().observe(this, progressBarObserver);

        setUpRecyclerView(getContext());

        return view;
    }

    void setUpRecyclerView(Context context) {
        //Create Adapter && bind it
        adapter = new RecyclerAdapter(context, null);

        adapter.setHasStableIds(true);


        recyclerView.getItemAnimator().setRemoveDuration(0);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.getItemAnimator().setAddDuration(0);
        recyclerView.getItemAnimator().setMoveDuration(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(adapter);
    }

    void changeListType() {

        LayoutAnimationController animationController = null;
        if (Constants.isAlternative) {
            Constants.isAlternative = false;
            animationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_down);
        }
        else {
            Constants.isAlternative = true;
            animationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_up);
        }

        recyclerView.setLayoutAnimation(animationController);

    //    recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.scheduleLayoutAnimation();
    }
}
