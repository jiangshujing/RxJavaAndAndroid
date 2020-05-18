package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //更新ui 需要在主线程执行
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())//指定主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //创建观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String value) {
            Log.e("onNext", value);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
}
