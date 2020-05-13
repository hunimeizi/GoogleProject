package com.xiangxue.googleproject.data.repository.request;

import com.xiangxue.googleproject.data.config.ServerConfigs;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 请求中心的具体实现
 * 1.NetWork相关
 * 2.本地相关
 */
public class RequestAPI implements IRequest {

    public static IRequest instanceRequestAPI () {
        return new RequestAPI();
    }

    /** TODO ********************** 下面这一系列都是 NetWork相关的 ************************/

    @Override
    public void instanceRequestAction(String url, NetworkResultData resultData) {
        commonOKHttpRequestAction(url, resultData);
    }

    @Override
    public void instanceRequestAction(String url, String value, NetworkResultData resultData) {
        commonOKHttpRequestAction(url, resultData, value);
    }

    @Override
    public void instanceRequestAction(String url, String value1, String value2, NetworkResultData resultData) {
        commonOKHttpRequestAction(url, resultData, value1, value2);
    }

    @Override
    public void instanceRequestAction(String url, String value1, String value2, String value3, NetworkResultData resultData) {
        commonOKHttpRequestAction(url, resultData, value1, value2, value3);
    }

    @Override
    public void instanceRequestAction(String url, NetworkResultData resultData, String... args) {
        commonOKHttpRequestAction(url, resultData, args);
    }

    @Override
    public void instanceRequestAction(String url, NetworkResultData resultData, Map<String, String> parameter) {
        commonOKHttpRequestAction(url, resultData, parameter);
    }

    private final void commonOKHttpRequestAction(String url, NetworkResultData resultData) {
        // OKHTTP
        // 1.创建一个OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        // 2.构建参数的body  MultipartBody.FORM 表单形式
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        // 3.构建一个请求  post 提交里面是参数的builder   url()请求路径
        Request request = new Request.Builder().url(url)
                .post(builder.build()).build();

        // 4.发送一个请求
        okHttpClient.newCall(request).enqueue(resultData);
    }

    private final void commonOKHttpRequestAction(String url, NetworkResultData resultData, String value) {
        // OKHTTP
        // 1.创建一个OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        // 2.构建参数的body  MultipartBody.FORM 表单形式
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        // 2.2封装参数
        builder.addFormDataPart(ServerConfigs.PART, value);

        // 3.构建一个请求  post 提交里面是参数的builder   url()请求路径
        Request request = new Request.Builder().url(url)
                .post(builder.build()).build();

        // 4.发送一个请求
        okHttpClient.newCall(request).enqueue(resultData);
    }

    private final void commonOKHttpRequestAction(String url, NetworkResultData resultData, String... value) {
        // OKHTTP
        // 1.创建一个OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        // 2.构建参数的body  MultipartBody.FORM 表单形式
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (String s : value) {
            // 2.2封装参数
            builder.addFormDataPart(ServerConfigs.PART, s);
            // builder.addFormDataPart("");  添加多个参数
        }

        // 3.构建一个请求  post 提交里面是参数的builder   url()请求路径
        Request request = new Request.Builder().url(url)
                .post(builder.build()).build();

        // 4.发送一个请求
        okHttpClient.newCall(request).enqueue(resultData);
    }

    private final void commonOKHttpRequestAction(String url, NetworkResultData resultData, Map<String, String> parameter) {
        // OKHTTP
        // 1.创建一个OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        // 2.构建参数的body  MultipartBody.FORM 表单形式
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for(Map.Entry<String, String> entry : parameter.entrySet()){
            // 2.2封装参数
            builder.addFormDataPart(entry.getKey(), entry.getValue());
            // builder.addFormDataPart("");  添加多个参数
        }

        // 3.构建一个请求  post 提交里面是参数的builder   url()请求路径
        Request request = new Request.Builder().url(url)
                .post(builder.build()).build();

        // 4.发送一个请求
        okHttpClient.newCall(request).enqueue(resultData);
    }


    /** TODO ********************** 下面这一系列都是 本地相关的 ************************/

    @Override
    public void instanceLocalAction(String path, LocalResultData localResultData) {

    }
}
