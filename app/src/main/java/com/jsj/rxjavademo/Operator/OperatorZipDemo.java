package com.jsj.rxjavademo.Operator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jsj.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jsj on 16/6/7.
 */
public class OperatorZipDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ArrayList<String> list1 = new ArrayList<String>();
//        ArrayList<String> list2 = new ArrayList<String>();
//
//        list1.add("1");
//        list1.add("2");
//        list1.add("3");
//
//        list2.add("a");
//        list2.add("b");
//        list2.add("c");
//
//        Observable mObservable1 = Observable.fromIterable(list1);
//        Observable mObservable2 = Observable.fromIterable(list2);
//
//        //合并Observable数据 ,并重新发送组合后的数据
//        Observable mObservable3 = Observable.zip(mObservable1, mObservable2, new BiFunction<String,String,String>() {
//
//            @Override
//            public String apply(String s, String s2) throws Exception {
//                return s+s2;
//            }
//        });
//
//        mObservable3.subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                Log.d("zip === ",o.toString());
//            }
//        });


        Observable.zip(getData1(), getData2(), new BiFunction<List<String>, List<String>, String>() {
            @Override
            public String apply(List<String> strings, List<String> strings2) throws Exception {

                return strings.get(0) + strings.get(0);//发送组合后的新数据
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("接受新数据：：：", s);
                    }
                });
    }

    /**
     * 请求数据1
     *
     * @return
     */
    private Observable<List<String>> getData1() {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                ArrayList<String> contacters = new ArrayList<>();
                contacters.add("location:张三");
                contacters.add("location:李四");
                contacters.add("location:王五");
                e.onNext(contacters);
                e.onComplete();
            }
        });
    }

    /**
     * 获取数据2
     *
     * @return
     */
    private Observable<List<String>> getData2() {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                ArrayList<String> contacters = new ArrayList<>();
                contacters.add("net:Zeus");
                contacters.add("net:Athena");
                contacters.add("net:Prometheus");
                e.onNext(contacters);
                e.onComplete();
            }
        });
    }
}
