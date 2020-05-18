package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by jsj on 16/6/6.
 * Buffer操作符
 * Buffer( int n )      把n个数据打成一个list包，然后再次发送。
   Buffer( int n , int skip)   把n个数据打成一个list包，然后跳过第skip个数据。
 */
public class OperatorBufferDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> mObservable = Observable.just("1", "2", "3", "4");
        mObservable.doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d("onNext",value.toString());
            }
        });
    }
}
