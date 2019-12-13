package com.luffy.generalphotoflowlib.photoPreview.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;

import com.luffy.generalphotoflowlib.photoPreview.ui.ImagePreviewActivity;
import com.luffy.generalphotoflowlib.photoPreview.ui.ImagePreviewFragment;
import com.luffy.generalphotoflowlib.photoPreview.utils.ImageDownloadUtil;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 图片配置
 * @desc
 */
public class PhotoPreviewConfig {
    public static boolean mIsShowNumber = true;             //是否显示数字下标
    public static boolean needDownload;                     //是否支持图片下载
    public static ArrayList<String> list;                   //图片链接集合
    public static int position;                             //下标
    public static String downloadPath = "pictureviewer";    //下载目录
    public static int placeHolder;                          //占位符图片

    public PhotoPreviewConfig(Builder builder) {
        this.mIsShowNumber = builder.mIsShowNumber;
        this.needDownload = builder.needDownload;
        this.downloadPath = builder.downloadPath;
        this.placeHolder = builder.placeHolder;
        this.position = builder.position;
        this.list = builder.list;
    }

    public static class Builder implements Serializable {
        private boolean mIsShowNumber = true;           //是否显示数字下标
        private boolean needDownload;                   //是否支持图片下载
        private ArrayList<String> list;                 //图片链接集合
        private int position;                           //下标
        private String downloadPath = "imagepreview";   //下载目录
        private int placeHolder;                        //占位符图片

        public Builder() {
            super();
        }

        public Builder setIsShowNumber(boolean mIsShowNumber) {
            this.mIsShowNumber = mIsShowNumber;
            return this;
        }

        public Builder needDownload(boolean needDownload) {
            this.needDownload = needDownload;
            return this;
        }

        public Builder setListData(ArrayList<String> list) {
            this.list = list;
            return this;
        }

        public Builder setPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder setDownloadPath(String downloadPath) {
            this.downloadPath = downloadPath;
            return this;
        }

        public Builder setPlaceHolder(@DrawableRes int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public PhotoPreviewConfig build() {
            return new PhotoPreviewConfig(this);
        }
    }

    public void gotoActivity(Context context, PhotoPreviewConfig config) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        intent.putExtra(ImagePreviewActivity.EXTRA_IMAGE_URLS, config.list);
        intent.putExtra(ImagePreviewActivity.EXTRA_IMAGE_INDEX, config.position);
        intent.putExtra(ImagePreviewActivity.IS_SHOW_NUMBER, config.mIsShowNumber);
        ImagePreviewFragment.placeHolder = config.placeHolder;
        ImagePreviewFragment.mNeedDownload = config.needDownload;
        ImageDownloadUtil.path = config.downloadPath;
        context.startActivity(intent);
    }

}
