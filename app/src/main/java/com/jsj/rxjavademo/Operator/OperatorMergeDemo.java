package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * Created by jsj on 16/6/7.
 */
public class OperatorMergeDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();

        list1.add("1");
        list1.add("2");
        list1.add("3");

        list2.add("a");
        list2.add("b");
        list2.add("c");

        Observable mObservable1 = Observable.fromIterable(list1);
        Observable mObservable2 = Observable.fromIterable(list2);

        //合并数据  先发送observable2的全部数据，然后发送 observable1的全部数据
        Observable mObservable3 = Observable.merge(mObservable2, mObservable1);

        mObservable3.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("call3",o.toString());
            }
        });
    }
}
