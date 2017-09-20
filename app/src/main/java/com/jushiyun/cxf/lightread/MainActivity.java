package com.jushiyun.cxf.lightread;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jushiyun.cxf.lightread.base.BaseActivity;

/**
 * Created by cxf on 2017/8/23.
 *
 * @title
 * @describe 主页面
 * @email chenxianfu_it@163.com
 */
public class MainActivity extends BaseActivity {

    private TextView tv_logo; //logo
    private ImageButton ib_money; //捐赠
    private ImageButton ib_download; //下载
    private ImageButton ib_library; //书城
    private ImageButton ib_add; //本地

    /**
     * setContentView(R.layout.activity_main_1);  //正常布局
     * setContentView(R.layout.activity_main);  //用constraintlayout写的布局
     * @return
     */
    @Override
    public int setLayout() {
        return R.layout.activity_main_1;
    }

    @Override
    public void initBaseView() {
        tv_logo = (TextView) findViewById(R.id.tv_logo);
        ib_money = (ImageButton) findViewById(R.id.ib_money);
        ib_download = (ImageButton) findViewById(R.id.ib_download);
        ib_library = (ImageButton) findViewById(R.id.ib_library);
        ib_add = (ImageButton) findViewById(R.id.ib_add);

        setListener();
    }

    private void setListener() {
        ib_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ib_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ib_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
