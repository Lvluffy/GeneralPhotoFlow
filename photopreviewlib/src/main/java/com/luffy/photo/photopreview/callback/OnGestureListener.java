package com.luffy.photo.photopreview.callback;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 手势监听器
 * @desc
 */
public interface OnGestureListener {

    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX, float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);

}