package com.xiangxue.googleproject;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * 同学们这个是：整个项目的 Application
 *
 * 为什么要 “implements ViewModelStoreOwner” 就是为了 让整个项目持有一份 被观察者接口，为了完成共享
 */
public class ProjectApplication extends Application implements ViewModelStoreOwner {

    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.Factory mFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        // 实例化 ViewModelStore  存储 VM
        mAppViewModelStore = new ViewModelStore();

        // 同学们，这里可以完成一系列的初始化工作
        // ......
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    /**
     * 共享
     * 暴露出去 对外界提供一份唯一的ViewModelProvider
     * @param activity
     * @return
     */
    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((ProjectApplication) activity.getApplicationContext(),
                ((ProjectApplication) activity.getApplicationContext()).getAppFactory(activity));
    }

    /**
     * 共享
     * 内部享有 就是为了拿到 ViewModelProvider.Factory
     * @param activity
     * @return
     */
    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    /**
     * 内部享有 检查Application
     * @param activity
     * @return
     */
    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    /**
     * 内部享有 检查Activity
     * @param fragment
     * @return
     */
    private Activity checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
        return activity;
    }
}
