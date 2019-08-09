package com.hextorm.sampleproject.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.ViewGroup;

import com.hextorm.sampleproject.article.ArticleActivity;
import com.hextorm.sampleproject.utils.PopMessages;

public class NetworkStateReceiver extends BroadcastReceiver {

    ViewGroup viewGroup;

    public NetworkStateReceiver() {
    }

    public NetworkStateReceiver(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ArticleActivity.isConnectionAvailable.setValue(isNetworkAvailable(context));
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo!=null&&networkInfo.isConnected());
    }
}
