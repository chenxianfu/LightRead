package com.jushiyun.cxf.lightread.bean;

import java.io.Serializable;

/**
 * Created by cxf on 2017/9/21.
 *
 * @title
 * @describe 小说的实体类
 * @email chenxianfu_it@163.com
 */

public class BookShelfBean implements Serializable {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
