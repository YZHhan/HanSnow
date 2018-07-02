package com.example.yinzh.hansnow.common.http.request;

import com.example.yinzh.hansnow.common.http.config.HttpGlobalConfig;
import com.example.yinzh.hansnow.common.http.model.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.http.Header;

/*
 * @Author yinzh
 * @Date   2018/5/27 16:25
 * @Description 请求基类
 */
public abstract class BaseHttpRequest <R extends BaseHttpRequest> {

    protected HttpGlobalConfig httpGlobalConfig;//全局配置
    protected Retrofit retrofit;// Retrofit 对象
    protected List<Interceptor> interceptors = new ArrayList<>();//局部请求拦截器
    protected List<Interceptor> networkInterceptors = new ArrayList<>();//局部请求的网络拦截器
    protected HttpHeaders headers = new HttpHeaders();// 请求头
}
