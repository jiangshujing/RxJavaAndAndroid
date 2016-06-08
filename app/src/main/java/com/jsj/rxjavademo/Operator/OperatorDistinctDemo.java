package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by jsj on 16/6/6.
 * distinct 操作符   过滤重复的数据
 */
public class OperatorDistinctDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("3");
        list.add("4");
        list.add("2");
        list.add("2");
        list.add("1");

        Observable.from(list)
                .distinct()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("distinct == ", s);
                    }
                });

        Observable.from(list)
                .distinctUntilChanged()//过滤连续重复的数据
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("distinctUntilChange == ", s);
                    }
                });

    }
}
