package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * Created by jsj on 16/6/6.
 * doOnNext() 操作符，在每次 OnNext() 方法被调用前执行
 * 使用场景：从网络请求数据，在数据被展示前，缓存到本地
 */
public class OperatorDoOnNetDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable mObservable = Observable.just("1", "2", "3", "4");
        mObservable.doOnNext(new Action1() {
            @Override
            public void call(Object o) {
                Log.d("doOnNext  call",o.toString());
            }
        }).subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.d("onNext",o.toString());
            }
        });
    }
}
