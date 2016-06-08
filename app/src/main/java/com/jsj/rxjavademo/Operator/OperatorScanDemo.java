package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by jsj on 16/6/6.
 * 累加结果操作符scan
 */
public class OperatorScanDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable mObservable = Observable.just(1, 2, 3, 4, 5);
        //返回相加结果
        Observable mObservableScan = mObservable.scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer o, Integer o2) {
                return o + o2;
            }
        });

        mObservableScan.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.d("scan == ", o.toString());
            }
        });


    }

}
