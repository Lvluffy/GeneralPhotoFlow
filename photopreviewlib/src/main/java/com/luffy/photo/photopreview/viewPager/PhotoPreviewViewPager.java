package com.luffy.photo.photopreview.viewPager;

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
public class PhotoPreviewViewPager extends ViewPager {

    public PhotoPreviewViewPager(Context context) {
        super(context);
    }

    public PhotoPreviewViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            //不理会
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            //不理会
            return false;
        }
    }

}
