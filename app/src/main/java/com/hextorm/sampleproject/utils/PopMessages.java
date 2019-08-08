package com.hextorm.sampleproject.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.hextorm.sampleproject.R;

public class PopMessages {

    public static void makeConnectivityCheckSnack(ViewGroup layout, Context context, boolean option) {
        String message="";
        Snackbar snackbar=snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_SHORT);;
        View sbView = snackbar.getView();
        sbView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        if(option) {
            message="You have solid internet connection";
            sbView.setBackgroundResource(R.color.colorGreen);

        }
        else {
            message="You are not connected to the internet";
            sbView.setBackgroundResource(R.color.colorRed);
        }

        snackbar.setText(message);
        snackbar.show();
    }
}
