package com.luffy.photo.photobrowse.widget.photodraweeview;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name
 * @desc
 */
public interface OnScaleDragGestureListener {

    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX, float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);

    void onScaleEnd();

}
