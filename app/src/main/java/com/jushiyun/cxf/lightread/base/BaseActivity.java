package com.jushiyun.cxf.lightread.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by cxf on 2017/8/23.
 *
 * @title  Activityd的基础类
 * @describe
 * @email chenxianfu_it@163.com
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected String TAG = BaseActivity.class.getSimpleName();
    public Activity activity;
    protected SharedPreferences preferences;
//    protected CompositeDisposable subscription = new CompositeDisposable();
    public static final int RESULT_FAILED = -3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        activity = this;
//        preferences = PreferenceUtil.getPreference();

        initBaseView();
        Log.i(TAG, "onCreate");
    }

    /**
     * 设置activity的布局
     *
     * @return
     */
    public abstract int setLayout();

    /**
     * activity 中实现，主要是实现组件的初始化，此方法会在parent activity的onCreate方法中实现
     */
    public abstract void initBaseView();

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // 点击空白处，隐藏软键盘
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();

            if (view instanceof EditText) {
                //
            } else {
                closeKeyWords();
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
//        RxRequest.unsubscribeIfNotNull(subscription);
        super.onDestroy();
    }

    protected void closeKeyWords() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive() && activity.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    /**
     * 隐藏当前的Activity
     */
    public void hideActivity() {
        moveTaskToBack(true);
    }


}
