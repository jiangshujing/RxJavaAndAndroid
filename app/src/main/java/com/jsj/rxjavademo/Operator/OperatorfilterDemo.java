package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by jsj on 16/6/6.
 * filter过滤操作符的使用
 */
public class OperatorfilterDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable mObservable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        //过滤
        Observable mObservableFilter = mObservable.filter(new Func1<Integer,Boolean>() {
            @Override
            public Boolean call(Integer o) {
                //数据大于4的时候才会被发送
                return o > 4;
            }
        });

        mObservableFilter.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.d("filter == ", o.toString());
            }
        });


    }

}
