package com.xiangxue.googleproject.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xiangxue.googleproject.data.bean.HomeDataResult;
import com.xiangxue.googleproject.data.repository.network.HttpRequestManager;

// 服务器请求 ViewModel  请求服务器
public class RequestHomeViewModel extends ViewModel {

    /*public void test() {
        IRequest request = new RequestAPI();
        request.instanceRequestAction("", new NetworkResultData() {
            @Override
            public void requestError(String info) {

            }

            @Override
            public void requestSuccess(Response result) {

            }
        });
    }*/

    // 代表首页 数据  homeDataResultMutableLiveData
    private MutableLiveData<HomeDataResult> homeDataResultMutableLiveData;

    public MutableLiveData<HomeDataResult> getHomeDataResultMutableLiveData() {
        if (homeDataResultMutableLiveData == null) {
            homeDataResultMutableLiveData = new MutableLiveData<>();
        }
        return homeDataResultMutableLiveData;
    }

    // 由 VM 触发  仓库
    public void touchOffHomeData() {
        HttpRequestManager.getInstance().requestHomeData(getHomeDataResultMutableLiveData());
    }
}
