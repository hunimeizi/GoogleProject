package com.xiangxue.googleproject.data.repository.request;

import android.util.Log;

import com.xiangxue.googleproject.data.config.AppConfigs;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 网络结果集
 */
public abstract class NetworkResultData implements Callback {

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.e(AppConfigs.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>> onFailure: use okhttp network request TO Exception:" + e.getMessage());
        requestError("请求时发送了异常，请开发者来 com.xiangxue.googleproject.data.repository.request.ICallback 寻找错误详情原因!!");
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        requestSuccess(response);
    }

    public abstract void requestError(String info);

    public abstract void requestSuccess(Response result);
}
