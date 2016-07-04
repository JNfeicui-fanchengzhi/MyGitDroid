package com.fanfan.mygitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.fanfan.mygitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/4.
 */
public class pager2 extends FrameLayout {
    @Bind(R.id.ivBubble1)
    ImageView mImageView1;
    @Bind(R.id.ivBubble2)
    ImageView mImageView2;
    @Bind(R.id.ivBubble3)
    ImageView mImageView3;
    public pager2(Context context) {
        super(context);
        init();
    }

    public pager2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public pager2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
        ButterKnife.bind(this);
        mImageView1.setVisibility(View.GONE);
        mImageView2.setVisibility(View.GONE);
        mImageView3.setVisibility(View.GONE);
    }
    public void showAnimation() {
        if (mImageView1.getVisibility()!=View.VISIBLE) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImageView1.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.BounceInLeft).duration(300).playOn(mImageView1);
                }
            }, 50);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImageView2.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.BounceInLeft).duration(300).playOn(mImageView2);
                }
            }, 550);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mImageView3.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.BounceInLeft).duration(300).playOn(mImageView3);
                }
            }, 1050);
        }
    }
}
