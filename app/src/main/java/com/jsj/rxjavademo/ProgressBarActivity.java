package com.jsj.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jsj on 16/6/13.
 */
public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener {


    private ProgressBar progressbar;
    private Subscriber subscriber;
    private Observable mObservable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        Button bt_view = (Button) findViewById(R.id.bt_view);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        progressbar.setMax(10); // 设置进度条最大值
        bt_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mObservable = Observable.interval(1, TimeUnit.SECONDS);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(11)//取前10个
                .subscribe(createSubscriber());
    }

    /**
     * 创建订阅者
     */
    private Subscriber createSubscriber() {
        subscriber = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Log.d("onCompleted === ", "");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("onError === ", "");
                mObservable = null;
            }

            @Override
            public void onNext(Long aLong) {
                Log.d("onNext === ", aLong + "");
                progressbar.setProgress(aLong.intValue());
            }
        };

        return subscriber;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
