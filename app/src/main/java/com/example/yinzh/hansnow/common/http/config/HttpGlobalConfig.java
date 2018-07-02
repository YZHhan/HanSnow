package com.example.yinzh.hansnow.common.http.config;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

import io.reactivex.internal.operators.maybe.MaybeObserveOn;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/*
 * @Author yinzh
 * @Date   2018/5/27 13:49
 * @Description: 请求全局配置  对Retrofit的参数进行初始化。
 */
public class HttpGlobalConfig {
    private CallAdapter.Factory callAdapterFactory;//Call 适配器工厂
    private Converter.Factory converterFactory;// Converter 转换工长
    private Call.Factory callFactory;// Call 工厂
    private SSLSocketFactory sslSocketFactory;//SSL 工厂
    private HostnameVerifier hostnameVerifier;//主机域名验证
    private ConnectionPool connectionPool;//连接池
    private Map<String, String> globalParams = new LinkedHashMap<>();//请求参数
    private Map<String, String> globalHeaders = new LinkedHashMap<>();//请求头
    private boolean isHttpCache;// 是否使用Http缓存
    private File httpCacheDirectory;//Http缓存路径
    private Cache httpCache;//http缓存对象
    private boolean isCookie;//是否使用Cookie
    private String baseUrl;//基础域名
    private int retryDelayMillis;//请求失败重试间隔时间
    private int retryCount;//请求失败重试次数

    private static HttpGlobalConfig instance;

    private HttpGlobalConfig() {
    }

    public static HttpGlobalConfig getInstance() {
        if (instance == null) {
            synchronized (HttpGlobalConfig.class) {
                if (instance == null) {
                    instance = new HttpGlobalConfig();
                }
            }
        }
        return instance;
    }

    /**
     * 设置CallAdapter工厂
     */
    public HttpGlobalConfig callAdapterFactory(CallAdapter.Factory factory) {
        this.callAdapterFactory = factory;
        return this;
    }

    /**
     * 设置转换工厂
     */
    public HttpGlobalConfig converterFactory(Converter.Factory factory) {
        this.converterFactory = factory;
        return this;
    }

    /**
     * 设置Call的工厂
     */
    public HttpGlobalConfig callFactory(Call.Factory factory) {
        this.callFactory = checkoutNotNull(factory, "factory == null");
        return this;
    }

    /**
     * 设置SSL工厂
     */
    public HttpGlobalConfig SSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
        return this;
    }

    /**
     * 设置主机验证
     */
    public HttpGlobalConfig hostNameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    /**
     * 设置连接池
     */
    public HttpGlobalConfig connectionPool(ConnectionPool connectionPool) {
        this.connectionPool = checkoutNotNull(connectionPool, "connectionPool == null");
        return this;
    }

    /**
     * 设置请求头部
     */
    public HttpGlobalConfig globalHeaders(Map<String, String> globalHeaders) {
        if (globalHeaders != null) {
            this.globalHeaders = globalHeaders;
        }
        return this;
    }

    /**
     * 设置请求参数
     */
    public HttpGlobalConfig globalParams(Map<String, String> globalParams) {
        if (globalParams != null) {
            this.globalParams = globalParams;
        }
        return this;
    }

    /**
     * 设置是否添加HTTP缓存
     */
    public HttpGlobalConfig setHttpCache(boolean isHttpCache) {
        this.isHttpCache = isHttpCache;
        return this;
    }

    /**
     * 设置HTTP缓存路径
     */
    public HttpGlobalConfig setHttpCacheDirectory(File httpCacheDirectory){
        this.httpCacheDirectory = httpCacheDirectory;
        return this;
    }

    /**
     * 设置HTTP缓存
     */
    public HttpGlobalConfig httpCache(Cache cache ){
        this.httpCache = cache;
        return this;
    }

    /**
     * 设置是否添加Cookie
     */
    public HttpGlobalConfig setCookie(boolean isCookie){
        this.isCookie = isCookie;
        return this;
    }













    private <T> T checkoutNotNull(T t, String message) {
        if (t == null) {
            throw new NullPointerException(message);
        }
        return t;
    }

}
