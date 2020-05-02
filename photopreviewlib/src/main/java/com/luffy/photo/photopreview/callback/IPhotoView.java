package com.luffy.photo.photopreview.callback;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

import com.luffy.photo.photopreview.PhotoViewAttacher;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name
 * @desc
 */
public interface IPhotoView {

    float DEFAULT_MAX_SCALE = 3.0f;
    float DEFAULT_MID_SCALE = 1.75f;
    float DEFAULT_MIN_SCALE = 1.0f;
    int DEFAULT_ZOOM_DURATION = 200;

    boolean canZoom();

    RectF getDisplayRect();

    boolean setDisplayMatrix(Matrix finalMatrix);

    Matrix getDisplayMatrix();

    @Deprecated
    float getMinScale();

    float getMinimumScale();

    @Deprecated
    float getMidScale();

    float getMediumScale();

    @Deprecated
    float getMaxScale();

    float getMaximumScale();

    float getScale();

    ImageView.ScaleType getScaleType();

    void setAllowParentInterceptOnEdge(boolean allow);

    @Deprecated
    void setMinScale(float minScale);

    void setMinimumScale(float minimumScale);

    @Deprecated
    void setMidScale(float midScale);

    void setMediumScale(float mediumScale);

    @Deprecated
    void setMaxScale(float maxScale);

    void setMaximumScale(float maximumScale);

    void setOnLongClickListener(View.OnLongClickListener listener);

    void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener listener);

    void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener listener);

    PhotoViewAttacher.OnPhotoTapListener getOnPhotoTapListener();

    void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener listener);

    PhotoViewAttacher.OnViewTapListener getOnViewTapListener();

    void setScale(float scale);

    void setScale(float scale, boolean animate);

    void setScale(float scale, float focalX, float focalY, boolean animate);

    void setScaleType(ImageView.ScaleType scaleType);

    void setZoomable(boolean zoomable);

    void setPhotoViewRotation(float rotationDegree);

    Bitmap getVisibleRectangleBitmap();

    void setZoomTransitionDuration(int milliseconds);

}
