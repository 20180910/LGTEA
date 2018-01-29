package com.sk.lgtea.module.home.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by Administrator on 2017/12/5.
 */

public class KeChengDetailBean implements CustomTabEntity {
    private String title;
    public KeChengDetailBean(String title) {
        this.title=title;
    }
    @Override
    public String getTabTitle() {
        return title;
    }
    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
