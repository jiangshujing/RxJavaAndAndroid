package com.jsj.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myObservable.subscribe(mySubscriber);//订阅

        //订阅 subscribe方法有一个重载版本，接受三个Action1类型的参数，分别对应OnNext，OnComplete， OnError函数。
        //这里我们并不关心onError和onComplete，所以只需要第一个参数就可以
//        myObservable.subscribe(onNextAction);

        //再简化
        Observable.just("Hello, world!")
                .map(new Func1<String, String>() {//转换事件
                    @Override
                    public String call(String s) {
                        return s + " RxJava";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("call", s);
                    }
                });

        Observable.just("Hello, world!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e("call", integer.toString());
                    }
                });


    }


    //创建被观察者
//    Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>(){
//
//        @Override
//        public void call(Subscriber<? super String> subscriber) {
//            subscriber.onNext("Hello, world!");
//            subscriber.onCompleted();
//        }
//    });

    //简化代码
    Observable<String> myObservable = Observable.just("Hello, world!");


    //创建订阅者

//    Subscriber<String> mySubscriber = new Subscriber<String>() {
//        @Override
//        public void onCompleted() {
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onNext(String s) {
//            Log.e("onCompleted", s);
//        }
//    };

    //简化Subscriber,在Subscriber中我们关注的只有onNext 一个方法，这时候就可以使用Action1 类
    Action1<String> onNextAction = new Action1<String>() {

        @Override
        public void call(String s) {
            Log.e("call", s);
        }
    };


}
