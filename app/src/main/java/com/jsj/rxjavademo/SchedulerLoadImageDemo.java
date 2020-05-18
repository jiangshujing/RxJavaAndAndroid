package com.jsj.rxjavademo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jsj on 16/6/7.
 * Scheduler 线程控制器的使用,加载图片
 */
public class SchedulerLoadImageDemo extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_load_image);
        imageView = (ImageView) findViewById(R.id.imageView);


        //被观察者，发送的数据是Drawable
        Observable.create(new ObservableOnSubscribe<Drawable>() {

            @Override
            public void subscribe(ObservableEmitter<Drawable> e) throws Exception {
                Drawable drawable = SchedulerLoadImageDemo.this.getResources().getDrawable(R.drawable.ad_smail_3);
                e.onNext(drawable);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())//指定subscribe()发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//指定subscribe的回掉发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Drawable value) {
                        imageView.setImageDrawable(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SchedulerLoadImageDemo.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
