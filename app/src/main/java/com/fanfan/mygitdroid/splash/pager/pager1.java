package com.fanfan.mygitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.fanfan.mygitdroid.R;

/**
 * Created by Administrator on 2016/7/4.
 */
public class pager1 extends FrameLayout {
    public pager1(Context context) {
        super(context);
        init();
    }

    public pager1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public pager1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1,this,true);
    }
}
