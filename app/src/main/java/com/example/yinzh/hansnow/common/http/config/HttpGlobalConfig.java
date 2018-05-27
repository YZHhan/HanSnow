package com.example.yinzh.hansnow.common.http.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/*
 * @Author yinzh
 * @Date   2018/5/27 13:49
 * @Description: 请求全局配置
 */
public class HttpGlobalConfig {
    private CallAdapter.Factory callAdapterFactory;//Call 适配器工厂
    private Converter.Factory converterFactory;// Converter 转换工长
    private Call.Factory callFactory;// Call 工厂
    private SSLSocketFactory sslSocketFactory;//SSL 工厂
    private HostnameVerifier hostnameVerifier;//主机域名验证



    private static HttpGlobalConfig instance;

    private HttpGlobalConfig() {
    }

    public static HttpGlobalConfig getInstance(){
        if (instance == null) {
            synchronized (HttpGlobalConfig.class) {
                if (instance == null) {
                    instance = new HttpGlobalConfig();
                }
            }
        }
        return instance;
    }
}
