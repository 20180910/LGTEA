package com.sk.lgtea.module.home.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/8.
 */

public class ReplyEvent implements Serializable {
    public  String type;
    public ReplyEvent(String type){
        this.type=type;

    }
}
