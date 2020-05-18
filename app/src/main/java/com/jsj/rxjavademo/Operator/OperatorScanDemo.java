package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


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
        Observable mObservableScan = mObservable.scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }

        });

        mObservableScan.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("scan == ", o.toString());
            }
        });


    }

}
