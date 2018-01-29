package com.sk.lgtea.module.home.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/27.
 */

public class PreviewEvent implements Serializable {
    public  String id;
    public  PreviewEvent(String id){
        this.id=id;
    }
}
