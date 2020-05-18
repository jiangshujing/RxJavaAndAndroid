package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

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
        Observable mObservableFilter = mObservable.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                //数据大于4的时候才会被发送
                return integer > 4;
            }
        });

        mObservableFilter.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("filter == ", o.toString());
            }
        });
    }
}
