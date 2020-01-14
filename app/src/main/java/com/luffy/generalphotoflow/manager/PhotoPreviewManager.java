package com.luffy.generalphotoflow.manager;

import java.util.ArrayList;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 图片预览-管理者
 * @desc
 */
public class PhotoPreviewManager {

    public static ArrayList<String> getPhotoUrls() {
        ArrayList<String> list = new ArrayList<>();
        //网络图片
        list.add("https://wx1.sinaimg.cn/mw690/7325792bly1fx9oma87k1j21900u04jf.jpg");
        list.add("https://wx3.sinaimg.cn/mw690/7325792bly1fx9oma3jhpj21900u04h0.jpg");
        list.add("https://wx4.sinaimg.cn/mw690/006l0mbogy1fi68ud4uwwj30u00zrtdj.jpg");
        list.add("https://wx1.sinaimg.cn/mw690/006DQg3tly1fuvkrxsntcg30g409xx6q.gif");
        list.add("https://wx1.sinaimg.cn/mw690/006DQg3tly1fuvks859e4g30dw0atqv8.gif");
        return list;
    }
}
