package com.hextorm.sampleproject;

import android.app.Application;
import android.content.Context;

import com.hextorm.sampleproject.data.ArticleRepository;

public class MyApplication extends Application {

    private static Context context;
    private static ArticleRepository repository;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        repository = Injection.provideRepository(context);
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static ArticleRepository getRepository() {
        return repository;
    }
}