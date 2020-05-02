package com.luffy.photo.photopreview.proxy.child;

import android.annotation.TargetApi;
import android.content.Context;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name
 * @desc
 */
@TargetApi(14)
public class IcsScroller extends GingerScroller {

    public IcsScroller(Context context) {
        super(context);
    }

    @Override
    public boolean computeScrollOffset() {
        return mScroller.computeScrollOffset();
    }

}
