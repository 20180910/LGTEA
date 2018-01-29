package com.sk.lgtea.module.taolun.network.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/18.
 */

public class DianzanObj implements Serializable {

    /**
     * is_thumbup : 0
     * thumbup_count : 0
     */

    private int is_thumbup;
    private String thumbup_count;

    public int getIs_thumbup() {
        return is_thumbup;
    }

    public void setIs_thumbup(int is_thumbup) {
        this.is_thumbup = is_thumbup;
    }

    public String getThumbup_count() {
        return thumbup_count;
    }

    public void setThumbup_count(String thumbup_count) {
        this.thumbup_count = thumbup_count;
    }
}
