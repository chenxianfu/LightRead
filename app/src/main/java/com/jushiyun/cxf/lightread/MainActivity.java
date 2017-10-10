package com.jushiyun.cxf.lightread;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jushiyun.cxf.lightread.adapter.BookShelfAdapter;
import com.jushiyun.cxf.lightread.base.BaseActivity;
import com.jushiyun.cxf.lightread.bean.BookShelfBean;
import com.jushiyun.cxf.lightread.widget.CustomRecyclerView;
import com.jushiyun.cxf.lightread.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxf on 2017/8/23.
 *
 * @title
 * @describe 主页面
 * @email chenxianfu_it@163.com
 */
public class MainActivity extends BaseActivity implements CustomRecyclerView.OnDataChangeListener {

    private TextView tv_logo; //logo
    private ImageButton ib_money; //捐赠
    private ImageButton ib_download; //下载
    private ImageButton ib_library; //书城
    private ImageButton ib_add; //本地

    private long click_back_key_time = 0;

    //刷新控件部分
    public LinearLayoutManager lm;
    public RefreshRecyclerView crv;

    private BookShelfAdapter shelfadapter;
    private ArrayList<BookShelfBean> list_bookShelfBean =new ArrayList<>();


    /**
     * setContentView(R.layout.activity_main_1);  //正常布局
     * setContentView(R.layout.activity_main);  //用constraintlayout写的布局
     *
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
        crv = (RefreshRecyclerView) findViewById(R.id.crv);

        setAdapter();
        setListener();



    }

    private void setAdapter() {

        //test
        for (int i = 0; i <15 ; i++) {
            BookShelfBean book =new BookShelfBean();
            book.setTitle("书"+i);
            list_bookShelfBean.add(book);
        }

        shelfadapter =new BookShelfAdapter(list_bookShelfBean);
        crv.setRefreshRecyclerViewAdapter(shelfadapter, new LinearLayoutManager(this));



        //test


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


    /***
     * 返回键处理事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - click_back_key_time > 2000) {
                Toast.makeText(getApplicationContext(), "再次点击退出", Toast.LENGTH_SHORT).show();
                click_back_key_time = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


//上拉刷新和下拉加载
    @Override
    public void onRefresh() {
        Log.d(TAG,"onRefresh");
    }

    @Override
    public void onLoadMore() {
        Log.d(TAG,"onLoadMore");
    }
}
