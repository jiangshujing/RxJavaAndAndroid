package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;


/**
 * Created by jsj on 16/6/6.
 * 消息数量过滤操作符
 * take ：取前n个数据
 * takeLast：取后n个数据
 * first 只发送第一个数据
 * last 只发送最后一个数据
 * skip() 跳过前n个数据发送后面的数据
 * skipLast() 跳过最后n个数据，发送前面的数据
 */
public class OperatorMsgNumberFilterDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable mObservable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        //take 发送前三个数据
        Observable mObservableTake = mObservable.take(3);

        mObservableTake.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("take == ", o.toString());
            }
        });

        //takeLast 发送后三个数据
        Observable mObservableTakeLast = mObservable.takeLast(3);
        mObservableTakeLast.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("takeLast",o.toString());
            }
        });

        //first 只发送第一个数据
        Single mObservableFirst = mObservable.first(1);
        mObservableFirst.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("first === ",o.toString());
            }
        });

        //last 只发送最后一个数据
        Single mObservableLast = mObservable.last(7);
        mObservableLast.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("last === ",o.toString());
            }
        });

        //skip 跳过前2个数据发送后面的数据
        Observable mObservableSkip = mObservable.skip(2);
        mObservableSkip.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("skip === ",o.toString());
            }
        });

        //skipLast 跳过最后2个数据发送后面的数据
        Observable mObservableSkipLast = mObservable.skipLast(2);
        mObservableSkipLast.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("skipLast === ",o.toString());
            }
        });

    }

}
