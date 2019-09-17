package com.luffy.generalphotoflowlib.photoPreview.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.luffy.generalphotoflowlib.R;
import com.luffy.generalphotoflowlib.photoPreview.PhotoViewAttacher;
import com.luffy.generalphotoflowlib.photoPreview.utils.ImageDownloadUtil;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 图片预览
 * @desc
 */
public class ImagePreviewFragment extends Fragment {

    public static int placeHolder;          //占位符图片
    public static boolean mNeedDownload;    //默认不支持下载

    //控件
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;

    //图片
    private String mImageUrl;
    private Bitmap mBitmap;

    public static ImagePreviewFragment newInstance(String imageUrl) {
        final ImagePreviewFragment imagePreviewFragment = new ImagePreviewFragment();
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        imagePreviewFragment.setArguments(args);
        return imagePreviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_image_detail, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.image);
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mNeedDownload) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("保存图片");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ImageDownloadUtil.saveImage(getActivity(), mImageUrl, mBitmap);
                        }
                    });
                    builder.create().show();
                }
                return false;
            }
        });
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                //点击图片退出
                boolean clickFinish = false;
                if (clickFinish) {
                    getActivity().finish();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(mImageUrl)) {
            RequestOptions mRequestOptions = new RequestOptions();
            mRequestOptions.error(placeHolder).placeholder(placeHolder);
            Glide.with(getActivity())
                    .asBitmap()
                    .load(mImageUrl)
                    .apply(mRequestOptions)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            mBitmap = resource;
                            mImageView.setImageBitmap(mBitmap);
                            mAttacher.update();
                        }
                    });
        } else {
            mImageView.setImageResource(placeHolder);
        }
    }
}
