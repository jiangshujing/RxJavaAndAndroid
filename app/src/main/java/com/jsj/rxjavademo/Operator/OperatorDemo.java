package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by jsj on 16/6/6.
 */
public class OperatorDemo extends AppCompatActivity {

    List<String> arr1;

    Observable<List<String>> query(String text) {
        return Observable.just(arr1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String arr[] = {"url1", "url2", "url3"};
        arr1 = new ArrayList<>();
        arr1.add("url1");
        arr1.add("url2");
        arr1.add("url3");


//        query("Hello, world!")
//                .subscribe(new Action1<List<String>>() {
//                    @Override
//                    public void call(List<String> strings) {
////                        for(String s:strings){
////                            Log.e("call", s);
////                        }
//
//                        Observable.from(strings)
//                                .subscribe(new Action1<String>() {
//                                    @Override
//                                    public void call(String s) {
//                                        Log.e("call", s);
//                                    }
//                                });
//                    }
//                });

        query("Hello, world!")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return null;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("call", s);
                    }
                });


    }

    private Observable<String> getTitle(String URL){

       return null;
    }


}
