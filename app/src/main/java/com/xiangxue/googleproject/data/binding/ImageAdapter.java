package com.xiangxue.googleproject.data.binding;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.xiangxue.googleproject.data.config.ServerConfigs;

// DataBinding 高级用法
public class ImageAdapter {

    // placeHolder 占位图
    @BindingAdapter(value = {"imageUrl", "placeHolder"}, requireAll = false)
    public static void loadUrl(ImageView view, String url, Drawable placeHolder) {

        // Log.d(TAG, "loadUrl: url:" + url);

        // 功能  下载服务器图片并显示
        // ....

        if (url != null) {
            Glide.with(view.getContext()).load(url).placeholder(placeHolder).into(view);
        } else {
            Glide.with(view.getContext()).load(ServerConfigs.DEFAULT_IMG_URL2).placeholder(placeHolder).into(view);
        }
    }

}
