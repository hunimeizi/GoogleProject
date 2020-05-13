package com.xiangxue.googleproject.data.repository.request;

import java.util.Map;

/**
 * NetWork请求  /  本地请求
 */
public interface IRequest {

    /** TODO ********************** 下面这一系列都是 NetWork相关的 ************************/
    void instanceRequestAction(String url, NetworkResultData resultData);

    void instanceRequestAction(String url, String value, NetworkResultData resultData);

    void instanceRequestAction(String url, String value1, String value2, NetworkResultData resultData);

    void instanceRequestAction(String url, String value1, String value2, String value3, NetworkResultData resultData);

    void instanceRequestAction(String url, NetworkResultData resultData, String... args);

    void instanceRequestAction(String url, NetworkResultData resultData, Map<String, String> parameter);

    /** TODO ********************** 下面这一系列都是 本地相关的 ************************/
    void instanceLocalAction(String path, LocalResultData localResultData);

    // TODO 自己去扩展
}
