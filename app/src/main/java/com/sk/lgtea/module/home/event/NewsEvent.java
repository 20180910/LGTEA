package com.sk.lgtea.module.home.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/8.
 */

public class NewsEvent implements Serializable {
    public  String is_check;
    public NewsEvent(String is_check){
        this.is_check=is_check;

    }
}
