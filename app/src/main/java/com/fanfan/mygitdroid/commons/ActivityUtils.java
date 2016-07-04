package com.fanfan.mygitdroid.commons;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/7/4.
 * <p>
 *此类包含与Activity相关的一些常用方法，例如startActivity和showToast。
 *我使用这个类做为BaseActivity的替代方案，以避免过深的继承树引入的复杂性。
 *有点类似于一种不太标准的委托模式。
 */
public class ActivityUtils {
    // 使用弱引用，避免不恰当地持有Activity或Fragment的引用。
    // 持有Activity的引用会阻止Activity的内存回收，增大OOM的风险。
    private WeakReference<Activity> mActivityWeakReference;
    private WeakReference<Fragment> mFragmentWeakReference;
    private Toast mToast;

    public ActivityUtils(Activity activity) {
        mActivityWeakReference = new WeakReference<Activity>(activity);
    }
    public ActivityUtils(Fragment fragment){
        mFragmentWeakReference=new WeakReference<Fragment>(fragment);
    }

    /**
     * 通过弱引用获取Activity对象，此方法可能返回null，调用后需要做检查。
     @return reference of {@link Activity}
      * @return
     */
    private Activity getActivity() {
        if (mActivityWeakReference != null) return mActivityWeakReference.get();
        if (mFragmentWeakReference != null) {
            Fragment fragment = mFragmentWeakReference.get();
            return fragment == null? null : fragment.getActivity();
        }
        return null;
    }

    public void showToast(CharSequence msg){
        Activity activity = getActivity();
        if (activity != null){
            if (mToast == null) mToast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
            mToast.setText(msg);
            mToast.show();
        }
    }
    @SuppressWarnings("SameParameterValue") public void showToast(@StringRes int resId){
        Activity activity = getActivity();
        if (activity != null) {
            String msg = activity.getString(resId);
            showToast(msg);
        }
    }

    public void startActivity(Class<? extends Activity> clazz){
        Activity activity = getActivity();
        if (activity == null) return;
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }
}
