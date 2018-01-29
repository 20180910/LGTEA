package com.sk.lgtea.network.response;


import com.sk.lgtea.base.BaseObj;

/**
 * Created by Administrator on 2017/11/30.
 */

public class FenXiangObj extends BaseObj {

    /**
     * title : 上海理工大学
     * content : 上海理工大学是一所以工学为主，工学、理学、经济学、管理学、文学、法学、艺术学等多学科协调发展的应用研究型大学，是国家国防科技工业局与上海市人民政府共建高校，上海市重点建设大学。
     * share_link : http://www.usst.edu.cn/
     */

    private String title;
    private String content;
    private String share_link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShare_link() {
        return share_link;
    }

    public void setShare_link(String share_link) {
        this.share_link = share_link;
    }
}
