package com.luffy.generalphotoflowlib.photoPreview.gestureDetector;

import android.content.Context;
import android.os.Build;

import com.luffy.generalphotoflowlib.photoPreview.callback.GestureDetector;
import com.luffy.generalphotoflowlib.photoPreview.callback.OnGestureListener;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 版本-手势探测器
 * @desc
 */
public final class VersionedGestureDetector {

    public static GestureDetector newInstance(Context context, OnGestureListener listener) {
        final int sdkVersion = Build.VERSION.SDK_INT;
        GestureDetector detector;
        if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
            detector = new CupcakeGestureDetector(context);
        } else if (sdkVersion < Build.VERSION_CODES.FROYO) {
            detector = new EclairGestureDetector(context);
        } else {
            detector = new FroyoGestureDetector(context);
        }
        detector.setOnGestureListener(listener);
        return detector;
    }
}