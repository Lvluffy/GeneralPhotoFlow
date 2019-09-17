package com.luffy.generalphotoflowlib.photoBrowse.widget.photodraweeview;

import android.view.MotionEvent;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 解决多指缩放和下拉冲突的, 该接口主要用于用于ScalePhotoView，因为PhotoDraweeView它的多指操作，原图缩小的时候,onTouch会被我这里中断
 * @desc
 */
public interface OnTouchEventAndScaleChangeListener {

    void onPhotoTouchEvent(MotionEvent ev);

    void onPhotoScaleChange(float matrixScale);

    void onPhotoScaleEnd(float matrixScale);
}
