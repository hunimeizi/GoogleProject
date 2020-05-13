package com.xiangxue.googleproject.base;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.xiangxue.common.data.manager.CustomNetworkStateManager;
import com.xiangxue.common.utils.AdaptScreenUtils;
import com.xiangxue.common.utils.BarUtils;
import com.xiangxue.common.utils.ScreenUtils;
import com.xiangxue.googleproject.ProjectApplication;
import com.xiangxue.googleproject.callback.SharedViewModel;

// 我所有的孩子，都让人家观察   角色“被观察者”
public abstract class BaseActivity extends AppCompatActivity {

    // 同学们这个是 贯穿整个项目的   初始化 必须共享（单例的方式）
    protected SharedViewModel mSharedViewModel;  // 使用共享

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化工作 公用
        BarUtils.setStatusBarVisibility(this, false); // 隐藏 状态栏
        // .....

        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);

        getLifecycle().addObserver(CustomNetworkStateManager.getInstance());

        initWorkspaceAction();
    }

    /**
     * 让自己的孩子，可以完成此动作
     */
    public abstract void initWorkspaceAction();


    /**
     * 方便调试Debug
     * @return
     */
    public boolean isDebug() {
        return getApplicationContext().getApplicationInfo() != null &&
                (getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    /**
     * 暴露给自己的还是 Toast
     * @param text
     */
    public void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * 暴露给自己的还是 Toast
     * @param text
     */
    public void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 使用 appolication 共享
     * 给此BaseActivity 得到ViewModelProvider
     * application
     * @return  唯一 ViewModelProvider  ViewModel
     */
    private ViewModelProvider getAppViewModelProvider() {
        return ((ProjectApplication) getApplicationContext()).getAppViewModelProvider(this);
    }

    /**
     * 使用 appolication 共享
     * 暴露给自己的孩子 得到ViewModelProvider
     * @param activity
     * 子类的 activity
     * @return 唯一  ViewModelProvider  ViewModel
     */
    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
    }

    /**
     * 暴露给自己的孩子 隐藏ActionBar
     */
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }

    /**
     * 暴露给自己的孩子 显示ActionBar
     */
    public void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.show();
        }
    }

}
