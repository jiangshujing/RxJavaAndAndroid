package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * Created by jsj on 16/6/6.
 * startWith 插入数据，在已有的数据之前插入数据，最多可插入9条
 */
public class OperatorStartWithDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable mObservable = Observable.just("aa","bb","cc");
        Observable mObservableStartWith = mObservable.startWith(mObservable);
        mObservableStartWith.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("StartWith 插入普通数据", o.toString());
            }
        });


        //插入Observable对象
        ArrayList<String> list = new ArrayList<String>();
        list.add("ww");
        list.add("tt");

        mObservable.startWith(Observable.fromIterable(list))
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("startWith 插入Observable",o.toString());
                    }
                });
    }
}
