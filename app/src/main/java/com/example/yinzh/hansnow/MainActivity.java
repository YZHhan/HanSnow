package com.example.yinzh.hansnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yinzh.hansnow.common.log.L;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

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
                L.i("RxJava","onNext" + value);
            }

            @Override
            public void onError(Throwable e) {
                L.i("RxJava","onError");

            }

            @Override
            public void onComplete() {
                L.i("RxJava","onComplete");

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



         Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {
            L.i("RxJava","onNext");
            }

            @Override
            public void onError(Throwable t) {
                L.i("RxJava","onError");

            }

            @Override
            public void onComplete() {
                L.i("RxJava","onComplete");

            }
        };

        observable.subscribe(observer);


    }
}
