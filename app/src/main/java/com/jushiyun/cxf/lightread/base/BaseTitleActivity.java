package com.jushiyun.cxf.lightread.base;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jushiyun.cxf.lightread.R;


/**
 * Created by cxf on 2017/8/23.
 *
 * @title 带有标题的activity
 * @describe
 * @email chenxianfu_it@163.com
 */

public abstract class BaseTitleActivity extends BaseActivity {

    public final int TYPE_NUMBER_FLAG_DECIMAL = 8194;
    /**
     * toolbar
     */
    protected Toolbar toolbar;
    /**
     * 居中显示的标题TextView
     */
    public TextView tv_base_title;

    @Override
    public void initBaseView() {
        activity = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_base_title = (TextView) findViewById(R.id.tv_base_title);
        tv_base_title.setText(setTitle());

        setNavigationVisibale(true);
        setListener();

        initView();
        Log.d(TAG, "TAG = " + TAG);
    }

    /**
     * <p>设置顶部的标题，之所以单独提供抽象方法，是考虑到程序有可能不使用toolbar设置标题（标题需要居中）。</p>
     * <p>toolbar.setTitle(setTitle()); 靠左标题</p>
     * <p>tvTitle.setText(setTitle()); 剧中标题/默认</p>
     *
     * @return title
     */
    public abstract String setTitle();

    public void setTitle(String title) {
        tv_base_title.setText(title);
    }

    /**
     * activity 中实现，主要是实现组件的初始化，此方法会在parent activity的onCreate方法中实现
     */
    public abstract void initView();

    /**
     * <p>设置toolbar navigation icon，默认使用v7的drawable，此方法不建议重写。</p>
     *
     * @return
     */
    public int setNavigationIcon() {
        //
//        return R.drawable.abc_back_icon;
        return R.drawable.icon_return_pre;
    }

    /**
     * 设置toolbar navigation的可见性,如需要此方法，请在initView()里调用
     *
     * @param visibale true:visibale,false:gone
     */
    public void setNavigationVisibale(boolean visibale) {
        if (visibale) {
            toolbar.setNavigationIcon(setNavigationIcon());
        } else {
            toolbar.setNavigationIcon(null);
        }
    }

    private void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationClick(v);
            }
        });
        //
    }

    /**
     * toolbar navigation （up button）的点击事件处理方法
     *
     * @param view
     */
    protected void onNavigationClick(View view) {
        finish();
    }

    /**
     * 获取标题TextView
     * @return
     */
    public TextView getTv_title() {
        return tv_base_title;
    }
}
