package com.xiangxue.googleproject.data.repository.network;

import androidx.lifecycle.MutableLiveData;

import com.xiangxue.googleproject.data.bean.DownloadFile;
import com.xiangxue.googleproject.data.bean.HomeDataResult;
import com.xiangxue.googleproject.data.bean.LibraryInfo;
import com.xiangxue.googleproject.data.bean.ListInfo;

import java.util.List;
import java.util.Map;

/**
 * 远程请求标准接口（在仓库里面） 也就是网络Network请求
 * 只为 HttpRequestManager 服务
 */
public interface IRemoteRequest {

    // 得到ListInfo数据
    void getListInfo(MutableLiveData<ListInfo> liveData);

    // 得到LibraryInfo数据
    void getLibraryInfo(MutableLiveData<List<LibraryInfo>> liveData);

    // 下载文件
    void downloadFile(MutableLiveData<DownloadFile> liveData);

    /**
     * 请求服务器 首页的 数据集
     */
    void requestHomeData(MutableLiveData<HomeDataResult> homeDataResultLiveData);
}
