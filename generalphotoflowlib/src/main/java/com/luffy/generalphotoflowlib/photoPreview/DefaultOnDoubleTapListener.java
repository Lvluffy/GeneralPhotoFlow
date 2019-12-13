package com.luffy.generalphotoflowlib.photoPreview;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name
 * @desc
 */
public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {

    private PhotoViewAttacher photoViewAttacher;

    public DefaultOnDoubleTapListener(PhotoViewAttacher photoViewAttacher) {
        setPhotoViewAttacher(photoViewAttacher);
    }

    public void setPhotoViewAttacher(PhotoViewAttacher newPhotoViewAttacher) {
        this.photoViewAttacher = newPhotoViewAttacher;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (this.photoViewAttacher == null)
            return false;
        ImageView imageView = photoViewAttacher.getImageView();
        if (null != photoViewAttacher.getOnPhotoTapListener()) {
            final RectF displayRect = photoViewAttacher.getDisplayRect();
            if (null != displayRect) {
                final float x = e.getX(), y = e.getY();
                if (displayRect.contains(x, y)) {
                    float xResult = (x - displayRect.left) / displayRect.width();
                    float yResult = (y - displayRect.top) / displayRect.height();
                    photoViewAttacher.getOnPhotoTapListener().onPhotoTap(imageView, xResult, yResult);
                    return true;
                }
            }
        }
        if (null != photoViewAttacher.getOnViewTapListener()) {
            photoViewAttacher.getOnViewTapListener().onViewTap(imageView, e.getX(), e.getY());
        }
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent ev) {
        if (photoViewAttacher == null)
            return false;
        try {
            float scale = photoViewAttacher.getScale();
            float x = ev.getX();
            float y = ev.getY();
            if (scale < photoViewAttacher.getMediumScale()) {
                photoViewAttacher.setScale(photoViewAttacher.getMediumScale(), x, y, true);
            } else if (scale >= photoViewAttacher.getMediumScale() && scale < photoViewAttacher.getMaximumScale()) {
                photoViewAttacher.setScale(photoViewAttacher.getMaximumScale(), x, y, true);
            } else {
                photoViewAttacher.setScale(photoViewAttacher.getMinimumScale(), x, y, true);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

}
