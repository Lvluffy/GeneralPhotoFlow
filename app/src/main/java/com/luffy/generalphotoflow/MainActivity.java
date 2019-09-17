package com.luffy.generalphotoflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luffy.generalphotoflow.manager.PhotoBrowseManager;
import com.luffy.generalphotoflow.manager.PhotoPreviewManager;
import com.luffy.generalphotoflowlib.photoBrowse.controller.PhotoBrowseConfig;
import com.luffy.generalphotoflowlib.photoPreview.controller.PhotoPreviewConfig;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_photo_preview, R.id.btn_photo_browse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_photo_preview:
                PhotoPreviewConfig config = new PhotoPreviewConfig.Builder()
                        .setListData(PhotoPreviewManager.getPhotoUrls())    //图片数据List<String> list
                        .setPosition(0)                                     //图片下标（从第position张图片开始浏览）
                        .setDownloadPath("imagepreview")                    //图片下载文件夹地址
                        .setIsShowNumber(true)                              //是否显示数字下标
                        .needDownload(true)                                 //是否支持图片下载
                        .setPlaceHolder(R.mipmap.ic_launcher)               //占位符
                        .build();
                config.gotoActivity(this, config);
                break;
            case R.id.btn_photo_browse:
                new PhotoBrowseConfig.Builder(this)
                        .setBigImageUrls(PhotoBrowseManager.getPhotoUrls())
                        .setSavaImage(true)
                        .setPosition(0)
                        .setSaveImageLocalPath("这里是你想保存的图片地址")
                        .build();
                break;
        }
    }
}
