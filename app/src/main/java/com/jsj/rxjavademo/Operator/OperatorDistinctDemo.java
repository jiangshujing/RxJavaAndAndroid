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

        Observable.fromArray(list)
                .distinct()
                .subscribe(new Consumer<ArrayList<String>>() {
                    @Override
                    public void accept(ArrayList<String> strings) throws Exception {
                        Log.d("distinct == ", strings.toString());
                    }
                });

        Observable.fromArray(list)
                .distinctUntilChanged()//过滤连续重复的数据
                .subscribe(new Consumer<ArrayList<String>>() {
                    @Override
                    public void accept(ArrayList<String> strings) throws Exception {
                        Log.d("distinctUntilChange == ", strings.toString());
                    }
                });

    }
}
