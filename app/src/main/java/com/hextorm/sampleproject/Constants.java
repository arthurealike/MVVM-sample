package com.hextorm.sampleproject;

import android.util.Log;

public class Constants {

    public static final String BASE_URL = "https://api.nytimes.com";
    public static final String END_POINT = "/svc/mostpopular/v2/viewed/7.json";
    public static final String API_KEY = "vLCI12IzU5dA4nOGN0cKSZQddyPrAi5L";

    public static boolean isAlternative=false;
    public static final void getUrl() { Log.i("custom-url-information", BASE_URL + END_POINT + API_KEY); }
}
