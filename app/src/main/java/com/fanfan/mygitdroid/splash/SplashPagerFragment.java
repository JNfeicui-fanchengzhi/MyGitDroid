package com.fanfan.mygitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fanfan.mygitdroid.R;
import com.fanfan.mygitdroid.splash.pager.pager2;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016/7/4.
 */
public class SplashPagerFragment extends Fragment {
    @Bind(R.id.viewPager) ViewPager mPager;
    @Bind(R.id.indicator) CircleIndicator mIndicator;//指示器(下方的小圆点)

    private SplashPagerAdapter mAdapter;

    @BindColor(R.color.colorGreen) int mcolorgreen;// ViewPager页面对应背景色
    @BindColor(R.color.colorRed) int mcolorred;// ViewPager页面对应背景色
    @BindColor(R.color.colorYellow) int mcoloryellow;// ViewPager页面对应背景色
    @Bind(R.id.content) FrameLayout mFrameLayout;// 当前页面布局,用于显示背景色的渐变

    @Bind(R.id.layoutPhone) FrameLayout mlayoutPhone;
    @Bind(R.id.ivPhoneBlank) ImageView mivPhoneBlank;
    @Bind(R.id.ivPhoneFont) ImageView mivPhoneFont;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mAdapter = new SplashPagerAdapter(getContext());
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(mPageChangeListener);
        mPager.addOnPageChangeListener(phoneChangeListener);
        mIndicator.setViewPager(mPager);
    }
    // 此监听器主要负责viewpager在scroll过程中,当前布局上layoutPhone布局的平移、缩放、渐变的处理
    private  final ViewPager.OnPageChangeListener phoneChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // ViewPager在第一个页面和第二个页面之间
            if (position==0){
                // 在移动过程中，实时scale
                float scale=0.3f+positionOffset*0.7f;
                mlayoutPhone.setScaleX(scale);
                mlayoutPhone.setScaleY(scale);
                // 在移动过程中，fone实时的变化
                mivPhoneFont.setAlpha(positionOffset);
                // 在移动过程中，有一个平移的动画
                int scroll = (int) ((positionOffset - 1) * 400);
                mlayoutPhone.setTranslationX(scroll);
                return;
            }
            // 当ViewPager在第二个页面和第三个页面之间时(总是为1),手机要和ViewPager一起平移
            if (position==1){
                mlayoutPhone.setTranslationX(- positionOffsetPixels);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    // 此监听器主要负责背景颜色的渐变，和最后一个页面视图动画的显示。
    private final ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        // ARGB插值器
        final ArgbEvaluator mEvaluator = new ArgbEvaluator();

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // ViewPager在第一个页面和第二个页面之间
            if (position==0){
                int color = (int) mEvaluator.evaluate(positionOffset,mcolorgreen,mcolorred);
                mFrameLayout.setBackgroundColor(color);
                return;
            }
            // ViewPager在第二个页面和第三个页面之间
            if (position==1){
                int color= (int) mEvaluator.evaluate(positionOffset,mcolorred,mcoloryellow);
                mFrameLayout.setBackgroundColor(color);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (position==2){
                pager2 pager2= (com.fanfan.mygitdroid.splash.pager.pager2) mAdapter.getView(2);
                pager2.showAnimation();
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
