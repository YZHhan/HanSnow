package com.example.yinzh.hansnow.common.http;

import android.content.Context;

import com.example.yinzh.hansnow.common.http.config.HttpGlobalConfig;
import com.example.yinzh.hansnow.common.http.core.ApiCache;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/*
 * @Author yinzh
 * @Date   2018/5/27 13:45
 * @Description 网络请求入口
 */
public class HanHttp {
    private static Context context;
    private static OkHttpClient.Builder okHttpBuilder;
    private static Retrofit.Builder retrofitBuilder;
    private static ApiCache apiCache;
    private static OkHttpClient okHttpClient;

    private static HttpGlobalConfig NET_GLOBAL_CONFIG = HttpGlobalConfig.getInstance();

    public static HttpGlobalConfig CONFIG(){
        return NET_GLOBAL_CONFIG;
    }

    public static void init(Context appContext){
        if(context == null && appContext != null){
            context = appContext.getApplicationContext();
            okHttpBuilder = new OkHttpClient.Builder();
            retrofitBuilder = new Retrofit.Builder();
//            apiCache
        }
    }


    public static Context getContext(){
        if(context == null){
            throw new IllegalStateException("Please call HanHttp.init(this) in Application to initialize");
        }
        return context;
    }

    public static OkHttpClient.Builder getOkHttpBuilder(){
        if (okHttpBuilder == null){
            throw new IllegalStateException("Please call HanHttp.init(this) in Application to initialize");
        }
        return okHttpBuilder;
    }

    public static Retrofit.Builder getRetrofitBuilder(){
        if(retrofitBuilder == null){
            throw new IllegalStateException("Please call HanHttp.init(this) in Application to initialize");
        }
        return retrofitBuilder;
    }

    public static OkHttpClient getOkHttpClient(){
        if(okHttpClient == null){
            throw new IllegalStateException("Please call HanHttp.init(this) in Application to initialize");
        }
        return okHttpClient;
    }



}
