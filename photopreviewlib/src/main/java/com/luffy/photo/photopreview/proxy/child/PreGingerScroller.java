package com.luffy.photo.photopreview.proxy.child;

import android.content.Context;
import android.widget.Scroller;

import com.luffy.photo.photopreview.proxy.base.ScrollerProxy;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name
 * @desc
 */
public class PreGingerScroller extends ScrollerProxy {

    private final Scroller mScroller;

    public PreGingerScroller(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    public boolean computeScrollOffset() {
        return mScroller.computeScrollOffset();
    }

    @Override
    public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY,
                      int overX, int overY) {
        mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY);
    }

    @Override
    public void forceFinished(boolean finished) {
        mScroller.forceFinished(finished);
    }

    public boolean isFinished() {
        return mScroller.isFinished();
    }

    @Override
    public int getCurrX() {
        return mScroller.getCurrX();
    }

    @Override
    public int getCurrY() {
        return mScroller.getCurrY();
    }
}