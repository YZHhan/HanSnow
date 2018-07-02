package com.example.yinzh.hansnow.common.http.callback;

/*
 * @Author yinzh
 * @Date   2018/7/1 15:09
 * @Description
 */
public abstract class ACallBack<T> {
    public abstract void onSuccess(T data);

    public abstract void onFail(int errCode, String errMsg);

}
