package com.sk.lgtea.module.home.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/8.
 */

public class StudyKejianEvent implements Serializable {
    public  String type;
    public StudyKejianEvent(String type){
        this.type=type;

    }
}
