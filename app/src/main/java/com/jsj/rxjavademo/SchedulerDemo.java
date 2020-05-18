package com.jsj.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jsj on 16/6/7.
 * Scheduler 线程控制器的使用
 */
public class SchedulerDemo extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        textview = (TextView) findViewById(R.id.textview);


//        代码，数据"1"、"2"、"3"将在io线程中发出，在android主线程中接收数据
        Observable.just("1", "2", "3")
                .subscribeOn(Schedulers.io()) //用来确定数据发射所在的线程，位置放在哪里都可以，但它是只能调用一次的。
                .observeOn(AndroidSchedulers.mainThread())//指定 Subscriber 的回调发生在主线程
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //所在主线程
                        textview.setText(s);
                    }
                });

        Observable.just("a", "b", "c", "d")
                .subscribeOn(Schedulers.io())//指定数据发射在哪个线程执行
                .observeOn(Schedulers.newThread())//observeOn方法决定他下面的方法执行在哪个线程中
                .map(func1)//一个新线程中执行
                .observeOn(Schedulers.io())//observeOn方法决定他下面的方法执行在哪个线程中
                .map(func2)//io线程执行
                .observeOn(AndroidSchedulers.mainThread())////observeOn方法决定他下面的方法执行在哪个线程中
                .subscribe(consumer);//ui线程执行


    }

    Function<String, String> func1 = new Function<String, String>() {
        @Override
        public String apply(String s) throws Exception {
            return s + "＝＝＝newThread＝＝＝";
        }
    };

    Function<String, String> func2 = new Function<String, String>() {
        @Override
        public String apply(String s) throws Exception {
            return s + "＝＝＝io＝＝＝";
        }
    };

    Consumer<String> consumer = new Consumer<String>() {
        @Override
        public void accept(String o) throws Exception {
            Log.d("call", o.toString());
        }
    };
}
