package com.jsj.rxjavademo.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jsj.rxjavademo.R;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GetActivity extends AppCompatActivity implements View.OnClickListener {


    private String mBaseUrl = "http://10.138.114.147:8080/okHttpServer/";
    private String Url = "https://github.com/hongyangAndroid";

    private static final String TAG = "GetActivity";

    private TextView mTv;

    private OkHttpClient mOkHttpClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        mTv = (TextView) findViewById(R.id.id_textview);
        Button bt_getHtml = (Button) findViewById(R.id.bt_getHtml);
        bt_getHtml.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        getHtml()
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just("flatmap ::: " + s );
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程
                .subscribe(new Observer<String>() {

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GetActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("onNext =====", s);
                        mTv.setText(s);
                    }
                });


    }


    private Observable<String> getHtml() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //创建okHttpClient对象
                mOkHttpClient = new OkHttpClient();
                //创建一个Request
                Request request = new Request.Builder()
                        .url(mBaseUrl)
                        .build();
                String json = execute2String(request);
                Log.d(TAG, "json =====" + json);
                setData(e, json);
            }
        }).subscribeOn(Schedulers.io());//处理数据在子线程
    }

    /**
     * 处理给观察者发送的数据
     *
     * @param s
     * @param json
     */
    private void setData(ObservableEmitter<String> s, String json) {
        if (TextUtils.isEmpty(json)) {
            s.onError(new Throwable("not data"));
            s.onComplete();
            return;
        }
        s.onNext(json);
        s.onComplete();
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
