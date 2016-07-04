package com.fanfan.mygitdroid.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.fanfan.mygitdroid.MainActivity;
import com.fanfan.mygitdroid.R;
import com.fanfan.mygitdroid.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页面 第一次启动时进入的页面
 */
public class SplashActivity extends AppCompatActivity {
    // ButterKnife 库
    // compile 'com.jakewharton:butterknife:7.0.1'

    @Bind(R.id.btnLogin)
    Button btnlogin;
    @Bind(R.id.btnEnter)
    Button btnenter;
    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mActivityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        Toast.makeText(this, "登陆", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnEnter)
    public void enter() {
        mActivityUtils.startActivity(MainActivity.class);
    }
}