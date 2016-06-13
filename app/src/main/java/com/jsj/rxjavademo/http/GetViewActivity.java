package com.jsj.rxjavademo.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsj.rxjavademo.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GetViewActivity extends AppCompatActivity implements View.OnClickListener {


    private String mBaseUrl = "http://10.138.114.147:8080/okHttpServer/";
    private String Url = "https://github.com/hongyangAndroid";

    private static final String TAG = "GetActivity";

    private TextView mTv;

    private OkHttpClient mOkHttpClient;
    private LinearLayout ll_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_view);

        mTv = (TextView) findViewById(R.id.id_textview);
        Button bt_getHtml = (Button) findViewById(R.id.bt_getHtml);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        bt_getHtml.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        getHtml()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//ui线程
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ll_progress.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ll_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(String s) {
                        ll_progress.setVisibility(View.GONE);
                        mTv.setText(s);
                    }
                });
    }


    private Observable<String> getHtml() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //创建okHttpClient对象
                mOkHttpClient = new OkHttpClient();
                //创建一个Request
                Request request = new Request.Builder()
                        .url(Url)
                        .build();
                String json = execute2String(request);
                Log.d(TAG, "json =====" + json);
                setData(subscriber, json);
            }
        }).subscribeOn(Schedulers.io());//处理数据在子线程
    }

    /**
     * 处理给观察者发送的数据
     *
     * @param subscriber
     * @param json
     */
    private void setData(Subscriber<? super String> subscriber, String json) {
        if (TextUtils.isEmpty(json)) {
            subscriber.onError(new Throwable("not data"));
            subscriber.onCompleted();
            return;
        }
        subscriber.onNext(json);
        subscriber.onCompleted();
    }


    public String execute2String(Request request) {

        String result = null;
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
