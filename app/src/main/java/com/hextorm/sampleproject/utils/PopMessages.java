package com.hextorm.sampleproject.utils;

import android.content.Context;

import android.support.design.widget.Snackbar;

import android.view.View;
import android.view.ViewGroup;



import com.hextorm.sampleproject.R;

public class PopMessages {

    public static void makeConnectivityCheckSnack(ViewGroup layout, Context context, boolean option) {
        String message="";
        Snackbar snackbar=snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();

     //   ViewCompat.setFitsSystemWindows(sbView, false);
      //  ViewCompat.setOnApplyWindowInsetsListener(sbView, null);


        sbView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        if(option) {
            message="You are connected to the internet";
            sbView.setBackgroundResource(R.color.colorGreen);
            snackbar.setDuration(700);
        }
        else {
            message="You are not connected to the internet";
            sbView.setBackgroundResource(R.color.colorRed);
        }

        snackbar.setText(message);
        snackbar.show();
    }
}
