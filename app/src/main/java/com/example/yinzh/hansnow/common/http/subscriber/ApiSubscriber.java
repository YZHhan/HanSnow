package com.example.yinzh.hansnow.common.http.subscriber;

import com.example.yinzh.hansnow.common.http.exception.ApiException;

import io.reactivex.observers.DisposableObserver;

/*
 * @Author yinzh
 * @Date   2018/7/1 10:55
 * @Description: API 统一订阅者  (观察者)
 * DisposableObserver:
 *   订阅者父类，对Error做特殊处理。
 *
 *
 */
public abstract class ApiSubscriber<T> extends DisposableObserver<T> {
    ApiSubscriber() {

    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            onError(e);
        } else {
            onError(new ApiException(e, 1000));
        }
    }

    protected abstract void onError(ApiException e);
}
