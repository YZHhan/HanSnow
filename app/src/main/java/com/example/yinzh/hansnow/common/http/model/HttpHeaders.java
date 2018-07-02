package com.example.yinzh.hansnow.common.http.model;

import android.text.TextUtils;

import com.example.yinzh.hansnow.common.log.L;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/*
 * @Author yinzh
 * @Date   2018/5/27 16:31
 * @Description 请求头封装
 */
public class HttpHeaders implements Serializable{
    private static final String TAG = "HttpHeaders";
    private static final String FORMAT_HTTP_DATA = "EEE, dd MMM y HH:mm:ss 'GMT'";
    private static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");

    public static final String HEAD_KEY_RESPONSE_CODE = "ResponseCode";
    public static final String HEAD_KEY_RESPONSE_MESSAGE = "ResponseMessage";
    public static final String HEAD_KEY_ACCEPT = "Accept";
    public static final String HEAD_KEY_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEAD_VALUE_ACCEPT_ENCODING = "gzip, deflate";
    public static final String HEAD_KEY_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEAD_KEY_CONTENT_TYPE = "Content-Type";
    public static final String HEAD_KEY_CONTENT_LENGTH = "Content-Length";
    public static final String HEAD_KEY_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEAD_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEAD_KEY_CONTENT_RANGE = "Content-Range";
    public static final String HEAD_KEY_CACHE_CONTROL = "Cache-Control";
    public static final String HEAD_KEY_CONNECTION = "Connection";
    public static final String HEAD_VALUE_CONNECTION_KEEP_ALIVE = "keep-alive";
    public static final String HEAD_VALUE_CONNECTION_CLOSE = "close";
    public static final String HEAD_KEY_DATE = "Date";
    public static final String HEAD_KEY_EXPIRES = "Expires";
    public static final String HEAD_KEY_E_TAG = "ETag";
    public static final String HEAD_KEY_PRAGMA = "Pragma";
    public static final String HEAD_KEY_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String HEAD_KEY_IF_NONE_MATCH = "If-None-Match";
    public static final String HEAD_KEY_LAST_MODIFIED = "Last-Modified";
    public static final String HEAD_KEY_LOCATION = "Location";
    public static final String HEAD_KEY_USER_AGENT = "User-Agent";
    public static final String HEAD_KEY_COOKIE = "Cookie";
    public static final String HEAD_KEY_COOKIE2 = "Cookie2";
    public static final String HEAD_KEY_SET_COOKIE = "Set-Cookie";
    public static final String HEAD_KEY_SET_COOKIE2 = "Set-Cookie2";

    public LinkedHashMap<String, String> headersMap;
    public static String acceptLanguage;

    public HttpHeaders() {
        init();
    }

    public HttpHeaders(String key, String value){
        init();
        put(key, value);
    }

    private void init(){
        headersMap = new LinkedHashMap<>();
    }

    public void put(String key, String value){
        if(key != null && value != null){
            headersMap.put(key, value);
        }
    }

    public void put(Map<String, String> headers){
        if(headers != null && headers.size() > 0){
            headersMap.putAll(headers);
        }
    }

    public void put(HttpHeaders httpHeaders){
        if(httpHeaders != null){
            if(httpHeaders.headersMap != null && !httpHeaders.headersMap.isEmpty()){
                headersMap.putAll(httpHeaders.headersMap);
            }
        }
    }

    public String get(String key){
        if(key != null){
            return headersMap.get(key);
        }
        return null;
    }

    public String remove(String key){
        if(key != null){
           return headersMap.remove(key);
        }
        return null;
    }

    public Set<String> getNames() {
        return headersMap.keySet();
    }

    public void clear() {
        headersMap.clear();
    }

    public final String toJSONString(){
        JSONObject jsonObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : headersMap.entrySet()){
                jsonObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e){
            L.e(TAG,e.getMessage());
        }
        return jsonObject.toString();
    }

    public static long getDate(String getTime){
        try {
            return parseGMTToMillis(getTime);
        } catch (ParseException e) {
            return 0;
        }

    }

    public static String getDate(long milliseconds) {
        return formatMillisToGMT(milliseconds);
    }

    public static long getExpiration(String expiresTime) {
        try {
            return parseGMTToMillis(expiresTime);
        } catch (ParseException e) {
            return -1;
        }
    }

    public static long getLastModified(String lastModified) {
        try {
            return parseGMTToMillis(lastModified);
        } catch (ParseException e) {
            return 0;
        }
    }

    public static String getCacheControl(String cacheControl, String pragma) {
        // first http1.1, second http1.0
        if (cacheControl != null) return cacheControl;
        else if (pragma != null) return pragma;
        else return null;
    }

    public static void setAcceptLanguage(String language) {
        acceptLanguage = language;
    }

    /**
     * Accept-Language: zh-CN,zh;q=0.8
     */
    public static String getAcceptLanguage() {
        if (TextUtils.isEmpty(acceptLanguage)) {
            Locale locale = Locale.getDefault();
            String language = locale.getLanguage();
            String country = locale.getCountry();
            StringBuilder acceptLanguageBuilder = new StringBuilder(language);
            if (!TextUtils.isEmpty(country))
                acceptLanguageBuilder.append('-').append(country).append(',').append(language).append(";q=0.8");
            acceptLanguage = acceptLanguageBuilder.toString();
            return acceptLanguage;
        }
        return acceptLanguage;
    }

    private static String formatMillisToGMT(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_HTTP_DATA, Locale.US);
        simpleDateFormat.setTimeZone(GMT_TIME_ZONE);
        return simpleDateFormat.format(date);
    }

    private static long parseGMTToMillis(String gmtTime) throws ParseException {
        if (TextUtils.isEmpty(gmtTime)) return 0;
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_HTTP_DATA, Locale.US);
        formatter.setTimeZone(GMT_TIME_ZONE);
        Date date = formatter.parse(gmtTime);
        return date.getTime();
    }

    @Override
    public String toString() {
        return "HttpHeaders{" + "headersMap=" + headersMap + '}';
    }
}