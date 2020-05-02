package com.luffy.generalphotoflow.base;

import android.app.Application;

import com.luffy.photo.photobrowse.FrescoImageLoader;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FrescoImageLoader.init(this);
    }
}
