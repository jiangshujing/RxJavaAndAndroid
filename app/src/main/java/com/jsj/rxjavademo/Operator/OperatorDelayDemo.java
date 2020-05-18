package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * Created by jsj on 16/6/6.
 * delay操作符，延迟数据发送
 */
public class OperatorDelayDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable mObservable = Observable.just("aa","bb","cc");
        //延迟数据发射的时间，仅仅延时一次，也就是发射第一个数据前延时。发射后面的数据不延时
        Observable mObservableDelay = mObservable.delay(2, TimeUnit.SECONDS);//延迟两秒发送
        mObservableDelay.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("delay ＝＝", o.toString());
            }
        });
    }
}
