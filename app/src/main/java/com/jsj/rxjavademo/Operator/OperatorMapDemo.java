package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jsj on 16/6/6.
 */
public class OperatorMapDemo extends AppCompatActivity {

    List<String> arr1;

    Observable<List<String>> query(String text) {
        return Observable.just(arr1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arr1 = new ArrayList<>();
        arr1.add("url1");
        arr1.add("url2");
        arr1.add("url3");

        Observable.just(arr1)
                .subscribeOn(Schedulers.newThread())//指定数据发射在哪个线程执行
                .flatMap(new Function<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> apply(List<String> strings) throws Exception {
                        Log.d("flatMap Thread ::: ", Thread.currentThread().getName());
                        return Observable.fromIterable(strings);//遍历列表发送
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("subscribe Thread ::: ", Thread.currentThread().getName());
                        Log.d("subscribe === ", s);
                    }
                });


//        query("Hello, world!")
//                .flatMap(new Function<List<String>, Observable<List<String>>>() {
//                    @Override
//                    public Observable<List<String>> apply(List<String> strings) throws Exception {
//                        return Observable.fromArray(strings);
//                    }
//                })
//                .flatMap(new Function<List<String>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(List<String> strings) throws Exception {
//                        return null;
//                    }
//                })
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        Log.e("call", o.toString());
//                    }
//                });


    }


}
