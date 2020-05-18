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
 * doOnNext() 操作符，在每次 OnNext() 方法被调用前执行
 * 使用场景：从网络请求数据，在数据被展示前，缓存到本地
 */
public class OperatorDoOnNetDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> mObservable = Observable.just("1", "2", "3", "4");
        mObservable.doOnNext(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                Log.d("doOnNext  call",o.toString());
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
            public void onNext(String o) {
                Log.d("onNext",o.toString());
            }
        });
    }
}
