package com.xiangxue.googleproject.data.repository.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiangxue.common.utils.Utils;
import com.xiangxue.googleproject.R;
import com.xiangxue.googleproject.data.bean.DownloadFile;
import com.xiangxue.googleproject.data.bean.HomeDataResult;
import com.xiangxue.googleproject.data.bean.LibraryInfo;
import com.xiangxue.googleproject.data.bean.ListInfo;
import com.xiangxue.googleproject.data.config.AppConfigs;
import com.xiangxue.googleproject.data.config.ServerConfigs;
import com.xiangxue.googleproject.data.repository.request.NetworkResultData;
import com.xiangxue.googleproject.data.repository.request.RequestAPI;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Response;

// 仓库角色 网络
public class HttpRequestManager implements IRemoteRequest {

    private static final HttpRequestManager S_REQUEST_MANAGER = new HttpRequestManager();
    private MutableLiveData<String> responseCodeLiveData;

    private HttpRequestManager() {
    }

    public static IRemoteRequest getInstance() {
        return S_REQUEST_MANAGER;
    }

    /** TODO ********************** 下面这一系列都是 NetWork相关的 ************************/

    // 暂无使用到
    public MutableLiveData<String> getResponseCodeLiveData() {
        if (responseCodeLiveData == null) {
            responseCodeLiveData = new MutableLiveData<>();
        }
        return responseCodeLiveData;
    }

    @Override
    public void getListInfo(MutableLiveData<ListInfo> listInfos) {

        Gson gson = new Gson();
        Type type = new TypeToken<ListInfo>() {
        }.getType();
        ListInfo testAlbum = gson.fromJson(Utils.getApp().getString(R.string.list_info_json), type);

        // TODO 在这里可以请求网络
        // TODO 在这里可以请求网络
        // TODO 在这里可以请求数据库
        // .....

        listInfos.setValue(testAlbum);
    }

    @Override
    public void getLibraryInfo(MutableLiveData<List<LibraryInfo>> liveData) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<LibraryInfo>>() {
        }.getType();
        List<LibraryInfo> list = gson.fromJson(Utils.getApp().getString(R.string.library_json), type);

        liveData.setValue(list);
    }

    /**
     * 模拟下载任务:
     * 可分别用于 普通的请求，和可跟随页面生命周期叫停的请求，
     * 具体可见 ViewModel 和 UseCase 中的使用。
     *
     * @param liveData 从 Request-ViewModel 或 UseCase 注入 LiveData，用于 控制流程、回传进度、回传文件
     */
    @Override
    public void downloadFile(MutableLiveData<DownloadFile> liveData) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                //模拟下载，假设下载一个文件要 10秒、每 100 毫秒下载 1% 并通知 UI 层

                DownloadFile downloadFile = liveData.getValue();
                if (downloadFile == null) {
                    downloadFile = new DownloadFile();
                }
                if (downloadFile.getProgress() < 100) {
                    downloadFile.setProgress(downloadFile.getProgress() + 1);
                    Log.d("TAG", "下载进度 " + downloadFile.getProgress() + "%");
                } else {
                    timer.cancel();
                    downloadFile.setProgress(0);
                    return;
                }
                if (downloadFile.isForgive()) {
                    timer.cancel();
                    downloadFile.setProgress(0);
                    return;
                }
                liveData.postValue(downloadFile);
                downloadFile(liveData);
            }
        };

        timer.schedule(task, 100);
    }

    @Override
    public void requestHomeData(MutableLiveData<HomeDataResult> homeDataResultLiveData) {
        RequestAPI.instanceRequestAPI()
                .instanceRequestAction(ServerConfigs.SERVER_URL, "1", new NetworkResultData() {
                    @Override
                    public void requestError(String info) {
                        Log.e(AppConfigs.TAG, "requestHomeData requestError info:" + info);
                    }

                    @Override
                    public void requestSuccess(Response result) {
                        try {
                            // 成功  数据在response里面  获取后台给我们的JSON 字符串
                            String resultData = result.body().string();
                            Log.e(AppConfigs.TAG, "成功  数据在response里面  获取后台给我们的JSON 字符串 resultData:" + resultData);

                            // Gson解析成可操作的对象
                            Gson gson = new Gson();
                            HomeDataResult homeDataResult = gson.fromJson(resultData, HomeDataResult.class);
                            homeDataResultLiveData.postValue(homeDataResult);
                        }catch (Exception e) {
                            e.printStackTrace();
                            Log.e(AppConfigs.TAG, "requestSuccess: " + e.getMessage());
                        }
                    }
                });
    }
}
