package com.fanfan.mygitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.fanfan.mygitdroid.activity.HotRepoFragment;
import com.fanfan.mygitdroid.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页面 第一次启动时进入的页面
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout; // 抽屉(包含内容+侧滑菜单)
    @Bind(R.id.navigationView)
    NavigationView mNavigationView; // 侧滑菜单视图
    private ActivityUtils mActivityUtils;
    private MenuItem menuItem;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    // 热门仓库页面Fragment
    private HotRepoFragment hotRepoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        mActivityUtils=new ActivityUtils(this);
        // ActionBar处理
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置navigationView的监听器
        mNavigationView.setNavigationItemSelectedListener(this);
        // 设置Toolbar左上角切换侧滑菜单的按钮
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // 默认第一个menu项为选中(最热门)
        menuItem = mNavigationView.getMenu().findItem(R.id.github_hot_repo);
        menuItem.setChecked(true);
        // 默认显示的是hotRepoFragment热门仓库
        hotRepoFragment = new HotRepoFragment();
        replaceFragment(hotRepoFragment);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.github_hot_repo:
                mActivityUtils.showToast(R.string.hot_repo);
                break;
            case R.id.github_hot_coder:
                mActivityUtils.showToast(R.string.hot_coder);
                break;
            case R.id.github_trend:
                mActivityUtils.showToast(R.string.trend);
                break;
            case R.id.arsenal_my_repo:
                mActivityUtils.showToast(R.string.my_repo);
                break;
            case R.id.arsenal_recommend:
                mActivityUtils.showToast(R.string.recommend);
                break;
            case R.id.tips_daily:
                mActivityUtils.showToast(R.string.tips_daily);
                break;
            case R.id.tips_share:
                mActivityUtils.showToast(R.string.share);
                break;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 如NavigationView是开的 -> 关闭
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        // 如NavigationView是关的 -> 退出当前Activity
        else{
            super.onBackPressed();
        }
    }
    // 替换不同的内容Fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
