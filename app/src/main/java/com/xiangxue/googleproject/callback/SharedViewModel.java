package com.xiangxue.googleproject.callback;

import androidx.lifecycle.ViewModel;

import com.xiangxue.common.callback.CustomProjectLiveData;

/**
 * 共享区域
 */
public class SharedViewModel extends ViewModel {

    private final CustomProjectLiveData<Boolean> isActive;
    private final CustomProjectLiveData<Boolean> isLogin;

    // TODO 同学们注意，可以随着项目的判断，内容会越来越判断

    {
        isActive = new CustomProjectLiveData<>();
        isActive.setValue(false);

        isLogin = new CustomProjectLiveData<>();
        isLogin.setValue(false);
    }

}
