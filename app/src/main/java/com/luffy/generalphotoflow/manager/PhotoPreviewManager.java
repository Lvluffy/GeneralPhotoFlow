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
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgdmpxi7erj20qy0qyjtr.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg");
        return list;
    }
}
