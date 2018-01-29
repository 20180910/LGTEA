package com.sk.lgtea.module.home.network.response;

/**
 * Created by Administrator on 2017/12/20.
 */

public class InformationListObj {

    /**
     * information_id : 4
     * title : 撒阿达大
     * image_url : http://121.40.186.118:1554/upload/201712/07/201712071556199489.jpg
     * author : 测试
     * add_time : 2017/12/7
     */

    private String information_id;
    private String title;
    private String image_url;
    private String author;
    private String add_time;

    public String getInformation_id() {
        return information_id;
    }

    public void setInformation_id(String information_id) {
        this.information_id = information_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
