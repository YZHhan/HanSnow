package com.example.yinzh.hansnow.common.http.subscriber;

import com.example.yinzh.hansnow.common.http.callback.ACallBack;
import com.example.yinzh.hansnow.common.http.exception.ApiException;

/*
 * @Author yinzh
 * @Date   2018/7/1 11:53
 * @Description: 包含回调的订阅者（观察者），如果订阅这个，上层在不使用订阅者的情况下可或获得回调
 */
public class ApiCallbackSubscriber<T> extends ApiSubscriber<T>{

    ACallBack <T>  callBack;
    T data;

    public ApiCallbackSubscriber(ACallBack <T> callBack) {
        if(callBack == null){
            throw new NullPointerException("This callback is null");
        }
    }

    @Override
    protected void onError(ApiException e) {
        if(e == null){
            callBack.onFail(-1, "This ApiException is null");
        }

        callBack.onFail(e.getCode(), e.getMessage());
    }

    @Override
    public void onNext(T t) {
        this.data = t;
        callBack.onSuccess(t);
    }

    @Override
    public void onComplete() {
    }

    public T getData(){
        return data;
    }
}
