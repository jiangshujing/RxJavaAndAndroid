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
 * Buffer操作符
 * Buffer( int n )      把n个数据打成一个list包，然后再次发送。
   Buffer( int n , int skip)   把n个数据打成一个list包，然后跳过第skip个数据。
 */
public class OperatorBufferDemo extends AppCompatActivity {

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
