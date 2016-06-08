package com.jsj.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jsj on 16/6/7.
 * Scheduler 线程控制器的使用
 */
public class SchedulerDemo extends AppCompatActivity{

    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        textview = (TextView)findViewById(R.id.textview);

//        代码，数据"1"、"2"、"3"将在io线程中发出，在android主线程中接收数据
        Observable.just("1","2","3")
                .subscribeOn(Schedulers.io()) //用来确定数据发射所在的线程，位置放在哪里都可以，但它是只能调用一次的。
                .observeOn(AndroidSchedulers.mainThread())//指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //所在主线程
                        textview.setText(s);
                    }
                });

        Observable.just("a","b","c","d")
                .subscribeOn(Schedulers.io())//指定数据发射在哪个线程执行
                .observeOn(Schedulers.newThread())//observeOn方法决定他下面的方法执行在哪个线程中
                .map(func1)//一个新线程中执行
                .observeOn(Schedulers.io())//observeOn方法决定他下面的方法执行在哪个线程中
                .map(func2)//io线程执行
                .observeOn(AndroidSchedulers.mainThread())////observeOn方法决定他下面的方法执行在哪个线程中
                .subscribe(action1);//ui线程执行


    }

    Func1 func1 = new Func1<String,String>() {
        @Override
        public String call(String o) {
            return o+"＝＝＝newThread＝＝＝";
        }
    };

    Func1 func2 = new Func1<String,String>() {
        @Override
        public String call(String o) {
            return o+"＝＝＝io＝＝＝";
        }
    };

    Action1 action1= new Action1() {
        @Override
        public void call(Object o) {
            Log.d("call",o.toString());
        }
    };
}
