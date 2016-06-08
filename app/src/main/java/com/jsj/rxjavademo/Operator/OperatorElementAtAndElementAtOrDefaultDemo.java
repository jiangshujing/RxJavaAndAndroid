package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by jsj on 16/6/6.
 * elementAt 、elementAtOrDefault操作符
 * elementAt() 发送数据序列中第n个数据 ，序列号从0开始
    如果该序号大于数据序列中的最大序列号，则会抛出异常，程序崩溃
    所以在用elementAt操作符的时候，要注意判断发送的数据序列号是否越界

    elementAtOrDefault( int n , Object default ) 发送数据序列中第n个数据 ，序列号从0开始。
    如果序列中没有该序列号，则发送默认值
 */
public class OperatorElementAtAndElementAtOrDefaultDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable mObservable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        Observable mObservableElementAt = mObservable.elementAt(2);
        mObservableElementAt.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.d("elementAt === ", o.toString());
            }
        });

//        elementAtOrDefault( int n , Object default ) 发送数据序列中第n个数据 ，序列号从0开始。
//        如果序列中没有该序列号，则发送默认值
        Observable mObservableElementAtOrDefault = mObservable.elementAtOrDefault(8,0);
        mObservableElementAtOrDefault.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.d("elementAtOrDefault === ",o.toString());
            }
        });

    }

}
