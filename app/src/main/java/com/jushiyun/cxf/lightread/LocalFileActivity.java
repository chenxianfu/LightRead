package com.jushiyun.cxf.lightread;

import com.jushiyun.cxf.lightread.base.BaseTitleActivity;


/**
 * Created by cxf on 2017/8/23.
 *
 * @title 本地文件 添加
 * @describe
 * @email chenxianfu_it@163.com
 */

public class LocalFileActivity extends BaseTitleActivity {
    @Override
    public String setTitle() {
        return getString(R.string.local_file);
    }

    @Override
    public void initView() {


    }

    @Override
    public int setLayout() {
        return R.layout.activity_local_file;
    }
}
