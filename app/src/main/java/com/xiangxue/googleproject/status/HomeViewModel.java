package com.xiangxue.googleproject.status;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xiangxue.common.utils.Utils;
import com.xiangxue.googleproject.R;

// 首页的 ViewModel  一个职责  管理 home 状态集变化
public class HomeViewModel extends ViewModel {

    //HomeViewModel  ----  HomeFragment ---> DataBinding ---> Layout
    private MutableLiveData<String> mText;
    private MutableLiveData<String> imageURL1; // 图片1 服务器地址  server
    private MutableLiveData<String> imageURL2; // 图片2 服务器地址 server
    private MutableLiveData<String> linkURL1;  // 图片1  server 跳转链接
    private MutableLiveData<String> linkURL2;  // 图片2  server 跳转链接
    private MutableLiveData<Drawable> placeHolder; // 真正的图片

    // 给上面的初始化工作
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("首页中心");

        imageURL1 = new MutableLiveData<>();
        imageURL1.setValue(null);

        imageURL2 = new MutableLiveData<>();
        imageURL2.setValue(null);

        linkURL1 = new MutableLiveData<>();
        linkURL2 = new MutableLiveData<>();

        placeHolder = new MutableLiveData<>();
        // 初始化默认图片 （我的逻辑，在哪里 xml  DataBinding 高级用法）
        placeHolder.setValue(Utils.getApp().getResources().getDrawable(R.drawable.pictures_no));
    }


    // 对外暴露  数据集  为了数据驱动
    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String> getImageURL1() {
        return imageURL1;
    }

    public MutableLiveData<String> getImageURL2() {
        return imageURL2;
    }

    public MutableLiveData<String> getLinkURL1() {
        return linkURL1;
    }

    public MutableLiveData<String> getLinkURL2() {
        return linkURL2;
    }

    public MutableLiveData<Drawable> getPlaceHolder() {
        return placeHolder;
    }
}