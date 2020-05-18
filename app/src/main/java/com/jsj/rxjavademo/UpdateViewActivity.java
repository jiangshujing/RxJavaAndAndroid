package com.jsj.rxjavademo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

//import com.f2prateek.rx.preferences.Preference;
//import com.f2prateek.rx.preferences.RxSharedPreferences;

/**
 * Created by jsj on 16/6/6.
 */
public class UpdateViewActivity extends AppCompatActivity {

    TextView textview;
    TextView tv_ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_view);
        textview = (TextView) findViewById(R.id.textview);

        CheckBox chekcbox = (CheckBox) findViewById(R.id.chekcbox);
        //观察选择的变化
        RxCompoundButton.checkedChanges(chekcbox)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        textview.setText(aBoolean.toString());
                    }
                });


        //同步SharedPreferences
        //注：需要RxSharedPreferences库支持:https://github.com/f2prateek/rx-preferences
        SharedPreferences msharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(msharedPreferences);
//        Preference<Boolean> xxFunction = rxSharedPreferences.getBoolean("checkbox_start", false);

        CheckBox chekcbox2 = (CheckBox) findViewById(R.id.chekcbox2);
//        chekcbox2.setChecked(xxFunction.get());//设置保存的状态
//        RxCompoundButton.checkedChanges(chekcbox2)
//                .subscribe((Observer<? super Boolean>) xxFunction.asObservable());//保存状态

        //观察点击事件
        Button button = (Button) findViewById(R.id.button);
        RxView.clicks(button)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object aVoid) throws Exception {
                        Toast.makeText(UpdateViewActivity.this, "点击", Toast.LENGTH_SHORT).show();
                    }
                });

        //解决连续点击问题
        Button button2 = (Button) findViewById(R.id.button2);
        RxView.clicks(button2)
                .throttleFirst(3, TimeUnit.SECONDS)//在一段时间内只取一个事件 第二个参数是单位
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(UpdateViewActivity.this, "防止连续点击", Toast.LENGTH_SHORT).show();
                    }
                });

        //观察EditText改变事件
        tv_ed = (TextView) findViewById(R.id.tv_ed);
        EditText edittext = (EditText) findViewById(R.id.edittext);
        RxTextView.textChanges(edittext)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        tv_ed.setText(charSequence);
                    }
                });
    }
}
