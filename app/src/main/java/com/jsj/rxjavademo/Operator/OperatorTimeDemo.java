package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jsj.rxjavademo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by jiangshujing on 2017/11/19.
 * 使用timer做定时操作。当有“x秒后执行y操作”类似的需求的时候，想到使用timer
 * 例如：2秒后输出日志“hello world”，然后结束。
 */

public class OperatorTimeDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_msg = (TextView) findViewById(R.id.tv_msg);

        //2秒后执行
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tv_msg.setText("hello --- world");
                    }
                });
    }
}
