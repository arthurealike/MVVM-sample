package com.hextorm.sampleproject.article;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hextorm.sampleproject.Article;
import com.hextorm.sampleproject.BaseViewHolder;
import com.hextorm.sampleproject.R;
import com.hextorm.sampleproject.model.ArticleApiResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleViewHolder extends BaseViewHolder<Article> {

    @LayoutRes
    public static final int LAYOUT = R.layout.viewholder_activity;

    public static Context context;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvUrl)
    TextView tvUrl;
    @BindView(R.id.tvView)
    TextView tvView;
    @BindView(R.id.articleImage)
    ImageView image;

    public ArticleViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        //itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_down));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Article article) {
        tvTitle.setText(article.getmTitle());
        tvType.setText(article.getmType());
        tvDate.setText(article.getmDate());
        tvUrl.setText(article.getmUrl());
        tvView.setText(String.valueOf(article.getmViews()));

        Glide.with(context)
                .load(article.getmPhotoUrl())
                .into(image);

        image.setOnClickListener((View v) -> {
            createDialog(article.getmUrl());
        });
    }

    private void createDialog(String url) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.ShowAlertDialogTheme));
        dialog.setTitle("Warning");
        dialog.setMessage("You're gonna direct to the official web page, Do you want it?");
        dialog.setNegativeButton("No, Thanks", null);
        dialog.setPositiveButton("Yeah!", (DialogInterface dialogInterface, int which) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        });
        dialog.show();
    }
}

