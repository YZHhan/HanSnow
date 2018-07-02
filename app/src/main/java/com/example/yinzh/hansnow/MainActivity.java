package com.example.yinzh.hansnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yinzh.hansnow.common.log.L;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RxJava";
    private static final String TAG1 = "RxJavaThread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 创建一个观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                L.i("RxJava", "onNext" + value);
            }

            @Override
            public void onError(Throwable e) {
                L.i("RxJava", "onError");

            }

            @Override
            public void onComplete() {
                L.i("RxJava", "onComplete");

            }
        };

        //创建一个被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("我是R小Java1");
                e.onNext("我是RxJava2");
                e.onComplete();
            }
        });


//        observable.subscribe(observer);

        List arrayList = new ArrayList();
        arrayList.add("1");
        L.i("RxJava", "arrayList.size :" + arrayList.size());


        //
        Observable.create(new ObservableOnSubscribe<String>() {// 第一步，初始化Observable
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i(TAG, "Observable emit 1" + "\n");
                e.onNext("我是RxJava1");
                Log.i(TAG, "Observable emit 2" + "\n");
                e.onNext("我是RxJava2");
                Log.i(TAG, "Observable emit 3" + "\n");
                e.onNext("我是RxJava3");

                e.onComplete();
                e.onNext("我是RxJava4");


            }
        }).subscribe(new Observer<String>() {// 第三步，订阅

            private int i;
            private Disposable disposable;


            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String value) {
                i++;
                L.i(TAG, value + "1111111");
                if (i == 2) {
                    disposable.dispose();
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n");

            }
        });

        /**
         * 1.	简单的说，subscriben指定的是发射事件的线程，observeOn指定的是订阅者接受事件的线程。
         * 2.	多次指定发射事件的线程有第一次指定的有效，也就是说多次调用subscrobeOn时，只用第一次有用，其余的会被忽略。
         * 3.	多次指定订阅者的线程是可以的，也就是说每调用一次observerOn，下游的线程会切换一次
         */

        // --------------- 切换线程  RxJava ---------------------

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                L.i(TAG1, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext("我是切换线程RxJava");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())// ========切换被观察者线程
                .subscribeOn(Schedulers.io())//  ==========切换被观察者线程
                .observeOn(AndroidSchedulers.mainThread())//  ========切换观察者线程
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG1, "After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
                    }
                }).observeOn(Schedulers.io())//      =============切换观察者线程
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG1, "After observeOn(io)，Current thread is " + Thread.currentThread().getName());

                    }
                });


    }
}
