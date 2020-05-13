package com.xiangxue.googleproject;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xiangxue.googleproject.base.BaseActivity;
import com.xiangxue.googleproject.data.config.AppConfigs;
import com.xiangxue.googleproject.databinding.ActivityDetailLinkBinding;

/**
 * 描述：显示网页链接Activity
 */
public class DetailLinkActivity extends BaseActivity {

    @Override
    public void initWorkspaceAction() {
        ActivityDetailLinkBinding binding = ActivityDetailLinkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideActionBar();

        WebView mWebView = binding.webView;

        // 1.获取上个页面传递过来的url
        final String mUrl = getIntent().getStringExtra(AppConfigs.URL_KEY);

        // 2.设置WebView的一些参数
        WebSettings mWebSettings = mWebView.getSettings();// 获取WebView参数设置
        mWebSettings.setUseWideViewPort(false);  // 将图片调整到适合webview的大小
        mWebSettings.setJavaScriptEnabled(true); // 支持js
        mWebSettings.setLoadsImagesAutomatically(true);  // 支持自动加载图片

        // 3.利用WebView直接加载网页链接
        // 每次启动这个activity 所加载的url网页路径肯定是不一样的 ， Intent传值
        mWebView.loadUrl(mUrl);
    }
}
