package com.luffy.generalphotoflowlib.photoPreview.callback;

import android.view.MotionEvent;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 手势探测器
 * @desc
 */
public interface GestureDetector {

    boolean onTouchEvent(MotionEvent ev);

    boolean isScaling();

    void setOnGestureListener(OnGestureListener listener);

}
