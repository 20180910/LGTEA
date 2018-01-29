package com.sk.lgtea.module.taolun.network.request;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class FatieBody {


    /**
     * title : sample string 1
     * content : sample string 2
     * images : [{"images":"sample string 1"},{"images":"sample string 1"}]
     */

    private String title;
    private String content;
    private List<ImagesBean> images;

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

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * images : sample string 1
         */

        private String images;

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }
}
