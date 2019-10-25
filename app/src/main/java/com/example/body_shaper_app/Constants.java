package com.example.body_shaper_app;

import com.example.body_shaper_app.BuildConfig;

public class Constants {
    public static final String YELP_BASE_URL = "https://api.yelp.com/v3/";
//    reference the Yelp credentials in our gradle.properties file
    public static final String YELP_API_KEY = BuildConfig.YELP_API_KEY;
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
}
