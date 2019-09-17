package com.luffy.generalphotoflowlib.photoPreview.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luffy.generalphotoflowlib.R;
import com.luffy.generalphotoflowlib.photoPreview.viewPager.PhotoPreviewViewPager;

import java.util.List;

/**
 * Created by lvlufei on 2019/9/17
 *
 * @name 图片预览
 * @desc
 */
public class ImagePreviewActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    public static final String IS_SHOW_NUMBER = "is_show_number";

    /*控件*/
    private PhotoPreviewViewPager mPager;
    private RelativeLayout layoutNav;
    private ImageView imgBack;
    private TextView txtTitle;

    /*上个界面传过来的数据*/
    int pagerPosition;
    List<String> urls;
    boolean mIsShowNumber;//是否显示数字下标

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail_pager);

        //初始化控件
        mPager = (PhotoPreviewViewPager) findViewById(R.id.pager);
        layoutNav = findViewById(R.id.layout_nav);
        imgBack = (ImageView) findViewById(R.id.img_back);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //获取上个界面传过来的数据
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        mIsShowNumber = getIntent().getBooleanExtra(IS_SHOW_NUMBER, true);

        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        txtTitle.setText(getString(R.string.viewpager_indicator, 1, mPager.getAdapter().getCount()));
        layoutNav.setVisibility(mIsShowNumber ? View.VISIBLE : View.GONE);
        //更新下标
        mPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, mPager.getAdapter().getCount());
                txtTitle.setText(text);
            }
        });
        mPager.setCurrentItem(pagerPosition);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {
        public List<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, List<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position).toString();
            return ImagePreviewFragment.newInstance(url);
        }
    }
}