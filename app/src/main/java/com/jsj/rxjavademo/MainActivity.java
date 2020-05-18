package com.jsj.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //订阅 subscribe方法有一个重载版本，接受三个Action1类型的参数，分别对应OnNext，OnComplete， OnError函数。
        //这里我们并不关心onError和onComplete，所以只需要第一个参数就可以
//        myObservable.subscribe(onNextAction);

        //再简化
        disposable = Observable.just("Hello, world!")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return null;
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "function";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("call", s);
                    }
                });

        Observable.just("Hello, world!")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return s.hashCode();
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("call", integer.toString());
                    }
                });

    }


    private void observable1() {
        //1.创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //通知观察者
                e.onNext("通知观察者");
                e.onComplete();
            }
        });

        //2.创建观察者 是个接口
        Observer observer = new Observer<String>() {
            //RxJava 2.0 中新增的，传递参数为Disposable ，Disposable 相当于RxJava1.x中的Subscription,用于解除订阅。
            @Override
            public void onSubscribe(Disposable d) {
                //可用于取消订阅
                d.dispose();
                //还可以判断是否处于取消状态
                //boolean b=d.isDisposed();
            }

            //观察者接收到通知,进行相关操作
            @Override
            public void onNext(String o) {
                Log.d("", "接收到观察者发出的数据");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        // 3. 订阅，将被观察者和观察者关联上

        observable.subscribe(observer);
    }

//    private void observable3() {
//        Observable.fromArray()
//    }



    /**
     * Flowable/Subscriber
     */
    private void observable2() {
        //1.创建被观察者
//        Flowable flowable = Flowable.range(0, 10);
        //使用create 创建Flowable 第二个参数是指定背压策略
        Flowable flowable = Flowable.create(new FlowableOnSubscribe() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);


        //2.创建观察者
        Subscriber subscribe = new Subscriber<Integer>() {
            Subscription sub;

            //当订阅后，会首先调用这个方法，其实就相当于onStart()，
            //传入的Subscription s参数可以用于请求数据或者取消订阅
            @Override
            public void onSubscribe(Subscription s) {
                Log.w("TAG", "onsubscribe start");
                sub = s;
                sub.request(1);
                Log.w("TAG", "onsubscribe end");
            }

            @Override
            public void onNext(Integer o) {
                Log.w("TAG", "onNext--->" + o);
                sub.request(1);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.w("TAG", "onComplete");
            }
        };

        //3.订阅
        flowable.subscribe(subscribe);
    }




    //简化代码
    Observable<String> myObservable = Observable.just("Hello, world!");

    //简化Subscriber,在Subscriber中我们关注的只有onNext 一个方法，这时候就可以使用Consumer 类
    Consumer<String> onNextAction = new Consumer<String>() {

        @Override
        public void accept(String s) throws Exception {
            Log.e("call", s);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
