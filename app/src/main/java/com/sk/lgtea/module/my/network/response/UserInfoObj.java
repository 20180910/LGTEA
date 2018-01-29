package com.sk.lgtea.module.my.network.response;

import com.sk.lgtea.base.BaseObj;

/**
 * Created by Administrator on 2017/11/18.
 */

public class UserInfoObj extends BaseObj {

    /**
     * user_id : 76
     * user_name : 122122
     * name : 魏老师
     * avatar : http://121.40.186.118:1554/upload/201712/21/201712211957545079.jpg
     * sex : 男
     * mobile : 13872228829
     * message_sink : 0
     * email : 925955465@qq.com
     * class_name : 计算机与科学技术
     * user_type : 2
     * individuality_signature : 2
     */

    private String user_id;
    private String user_name;
    private String name;
    private String avatar;
    private String sex;
    private String mobile;
    private int message_sink;
    private String email;
    private String class_name;
    private String user_type;
    private String individuality_signature;

    public String getIndividuality_signature() {
        return individuality_signature;
    }

    public void setIndividuality_signature(String individuality_signature) {
        this.individuality_signature = individuality_signature;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMessage_sink() {
        return message_sink;
    }

    public void setMessage_sink(int message_sink) {
        this.message_sink = message_sink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
