package com.sk.lgtea.module.home.network.response;

/**
 * Created by Administrator on 2017/12/20.
 */

public class NewListObj {

    /**
     * id : 5066
     * title : 作业提交提醒
     * content : 毛概作业截止到12月28号提交哦
     * add_time : 2017/12/20
     * is_check : 1
     * image : http://121.40.186.118:1554/upload/xttz.png
     */

    private String id;
    private String title;
    private String content;
    private String add_time;
    private String is_check;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getIs_check() {
        return is_check;
    }

    public void setIs_check(String is_check) {
        this.is_check = is_check;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
