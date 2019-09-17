package com.luffy.generalphotoflowlib.photoBrowse.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name
 * @desc
 */
public class PhotoBrowseViewPager extends ViewPager {

    public PhotoBrowseViewPager(Context context) {
        super(context);
    }

    public PhotoBrowseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // 不理会
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            // 不理会
            return false;
        }
    }
}
