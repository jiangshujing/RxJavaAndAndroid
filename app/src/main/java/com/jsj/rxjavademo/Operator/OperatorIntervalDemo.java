package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by jsj on 16/6/6.
 * interval 操作符，轮询操作，循环发送数据
 * 使用interval做周期性操作。当有“每隔xx秒后执行yy操作”类似的需求的时候，想到使用interval
 */
public class OperatorIntervalDemo extends AppCompatActivity {

    private Disposable mSubscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //参数一：延迟时间  参数二：间隔时间  参数三：时间颗粒度 MILLISECONDS 每秒一次
        Observable mObservable = Observable.interval(3000, 1000, TimeUnit.MILLISECONDS);
        mSubscription= mObservable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("interval == ", o.toString());
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSubscription !=null){
            mSubscription.dispose();
        }
    }
}
