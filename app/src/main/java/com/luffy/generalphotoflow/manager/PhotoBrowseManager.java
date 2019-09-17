package com.luffy.generalphotoflow.manager;

import com.luffy.generalphotoflow.R;
import com.luffy.generalphotoflowlib.photoBrowse.FrescoImageLoader;

import java.util.ArrayList;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 图片浏览-管理者
 * @desc
 */
public class PhotoBrowseManager {

    public static ArrayList<String> getPhotoUrls() {
        ArrayList<String> list = new ArrayList<>();
        list.add(FrescoImageLoader.getResUrl(R.drawable.resx));//长图
        list.add(FrescoImageLoader.getAssetUrl("test.jpeg"));
        //如果加载本地图片，可按照下面的来
//        list.add(FrescoImageLoader.getFileUrl("/storage/emulated/0/Pictures/Screenshots/Screenshot_20170919-203120.png"));
//        list.add(FrescoImageLoader.getFileUrl("/storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1505817817979.jpg"));
        //网络长图,包括横向和纵向的长图
        list.add("https://wx2.sinaimg.cn/mw690/005MctNqgy1fx674gpkbvj30gf4k2wzp.jpg");
        list.add("https://wx1.sinaimg.cn/mw690/0062Xesrgy1fx8uu5p4stj30j66d6b29.jpg");
        list.add("https://wx3.sinaimg.cn/mw690/0062Xesrgy1fx8uu5tkicj30j65fu4qp.jpg");
        //网络图片
        list.add("https://wx1.sinaimg.cn/mw690/7325792bly1fx9oma87k1j21900u04jf.jpg");
        list.add("https://wx3.sinaimg.cn/mw690/7325792bly1fx9oma3jhpj21900u04h0.jpg");
        list.add("https://wx4.sinaimg.cn/mw690/006l0mbogy1fi68ud4uwwj30u00zrtdj.jpg");
        list.add("https://wx1.sinaimg.cn/mw690/006DQg3tly1fuvkrxsntcg30g409xx6q.gif");
        list.add("https://wx1.sinaimg.cn/mw690/006DQg3tly1fuvks859e4g30dw0atqv8.gif");
        return list;
    }
}
