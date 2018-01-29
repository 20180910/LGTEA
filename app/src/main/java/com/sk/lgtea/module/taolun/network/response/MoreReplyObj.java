package com.sk.lgtea.module.taolun.network.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public class MoreReplyObj implements Serializable {

    /**
     * comments_id : 4
     * photo : http://121.40.186.118:1554/upload/201712/15/171215093257178754.jpg
     * name : 咕**
     * comment_time : 1513566001
     * content : 支持支持 热烈支持1
     * reply_list : [{"reply_id":6,"name":"魏老师","name_to":"","content":"谢谢支持","code":"commen"},{"reply_id":5,"name":"魏老师","name_to":"","content":"谢谢支持","code":"commen"},{"reply_id":4,"name":"魏老师","name_to":"","content":"谢谢支持","code":"commen"}]
     */

    private String comments_id;
    private String photo;
    private String name;
    private long comment_time;
    private String content;
    private List<ReplyListBean> reply_list;

    public String getComments_id() {
        return comments_id;
    }

    public void setComments_id(String comments_id) {
        this.comments_id = comments_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getComment_time() {
        return comment_time;
    }

    public void setComment_time(long comment_time) {
        this.comment_time = comment_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ReplyListBean> getReply_list() {
        return reply_list;
    }

    public void setReply_list(List<ReplyListBean> reply_list) {
        this.reply_list = reply_list;
    }

    public static class ReplyListBean {
        /**
         * reply_id : 6
         * name : 魏老师
         * name_to :
         * content : 谢谢支持
         * code : commen
         */

        private String reply_id;
        private String name;
        private String name_to;
        private String content;
        private String code;

        public String getReply_id() {
            return reply_id;
        }

        public void setReply_id(String reply_id) {
            this.reply_id = reply_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName_to() {
            return name_to;
        }

        public void setName_to(String name_to) {
            this.name_to = name_to;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
