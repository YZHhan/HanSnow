package com.example.yinzh.hansnow.common.http.core;

import android.content.Context;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/*
 * @Author yinzh
 * @Date   2018/6/3 19:04
 * @Description
 */
public class ApiCookie implements CookieJar {

    private CookieStore cookieStore;

    public ApiCookie(Context context) {
        if(cookieStore == null){
//            cookieStore = new CookieStore(context);
        }
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
}
