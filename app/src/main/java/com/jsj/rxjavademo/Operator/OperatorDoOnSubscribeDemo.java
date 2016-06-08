package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by jsj on 16/6/6.
 * doOnSubscribe
 * 使用场景： 可以在事件发出之前做一些初始化的工作，比如弹出进度条等等
 * <p>
 * 注意：
 * <p>
 * 1、doOnSubscribe() 默认运行在事件产生的线程里面，然而事件产生的线程一般都会运行在 io 线程里。那么这个时候做一些，更新UI的操作，是线程不安全的。
 * <p>
 * 所以如果事件产生的线程是io线程，但是我们又要在doOnSubscribe() 更新UI ， 这时候就需要线程切换。
 * <p>
 * 2、如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
 * <p>
 * 3、 subscribeOn() 事件产生的线程 ； observeOn() : 事件消费的线程
 */
public class OperatorDoOnSubscribeDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber subscriber) {

            }
        })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //更新ui 需要在主线程执行
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())//指定主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mySubscriber);
    }

    //创建订阅者
    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.e("onCompleted", s);
        }
    };
}
