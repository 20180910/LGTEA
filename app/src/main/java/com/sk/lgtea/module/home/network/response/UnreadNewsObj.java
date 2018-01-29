package com.sk.lgtea.module.home.network.response;


import com.sk.lgtea.base.BaseObj;

/**
 * Created by Administrator on 2017/12/20.
 */

public class UnreadNewsObj extends BaseObj {

    /**
     * news_num : 0
     * homework_num : 3
     */

    private String news_num;
    private String homework_num;

    public String getNews_num() {
        return news_num;
    }

    public void setNews_num(String news_num) {
        this.news_num = news_num;
    }

    public String getHomework_num() {
        return homework_num;
    }

    public void setHomework_num(String homework_num) {
        this.homework_num = homework_num;
    }
}
