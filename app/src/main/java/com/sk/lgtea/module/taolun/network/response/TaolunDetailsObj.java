package com.sk.lgtea.module.taolun.network.response;


import com.sk.lgtea.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public class TaolunDetailsObj extends BaseObj {

    /**
     * discussion_forum_id : 1
     * title : 如何才能学习好印刷出版呢?
     * content : 很多同学对于出版印刷都是比较头疼，很多同学对于出版印刷都是比较头疼,其实掌握好也是很简单的，要制一个印刷品，首先要看你印什么，书、包装袋、还是往别的物品上转印，这都是不一样的。印纸制品叫胶印，一般常用树脂版，凸印等;软包装(如塑料软包装)一般常用铜版，凹版等;转印一般都是特种印刷，丝网印刷版柔软比较容易变形，可转印。不同印刷，油墨不一样，耗材还不一样。以我们常见的人民币为倒，最能反映出印刷质量的高级。首先纸制选材高级，耐磨耐水，不易损坏，它的油墨，颜色不是一般能配出来的;它的制版包括凸、凹版多了去了，太专业了。它的设计防伪也都是一流的。所以印刷是一个很广泛的话题，它在各个领域都有重大意义。
     * head_portrait : 
     * name : 魏老师
     * is_thumbup : 0
     * thumbup_count : 0
     * add_time : 1513082712
     * image_list : ["http://121.40.186.118:1554/upload/taolunqu.png","http://121.40.186.118:1554/upload/taolunqu.png","http://121.40.186.118:1554/upload/taolunqu.png"]
     * comment_count : 1
     * comment_list : [{"comments_id":4,"content":"支持支持 热烈支持1","head_portrait":"http://121.40.186.118:1554/upload/201712/15/171215093257178754.jpg","name":"咕**","comment_time":1513566001,"reply_list":[{"reply_id":6,"name":"魏老师","name_to":"","content":"谢谢支持","code":"commen"},{"reply_id":5,"name":"魏老师","name_to":"","content":"谢谢支持","code":"commen"}]}]
     */

    private String discussion_forum_id;
    private String title;
    private String content;
    private String head_portrait;
    private String name;
    private String is_thumbup;
    private String thumbup_count;
    private long add_time;
    private String comment_count;
    private List<String> image_list;
    private List<CommentListBean> comment_list;

    public String getDiscussion_forum_id() {
        return discussion_forum_id;
    }

    public void setDiscussion_forum_id(String discussion_forum_id) {
        this.discussion_forum_id = discussion_forum_id;
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

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_thumbup() {
        return is_thumbup;
    }

    public void setIs_thumbup(String is_thumbup) {
        this.is_thumbup = is_thumbup;
    }

    public String getThumbup_count() {
        return thumbup_count;
    }

    public void setThumbup_count(String thumbup_count) {
        this.thumbup_count = thumbup_count;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public List<String> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<String> image_list) {
        this.image_list = image_list;
    }

    public List<CommentListBean> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<CommentListBean> comment_list) {
        this.comment_list = comment_list;
    }

    public static class CommentListBean {
        /**
         * comments_id : 4
         * content : 支持支持 热烈支持1
         * head_portrait : http://121.40.186.118:1554/upload/201712/15/171215093257178754.jpg
         * name : 咕**
         * comment_time : 1513566001
         * reply_list : [{"reply_id":6,"name":"魏老师","name_to":"","content":"谢谢支持","code":"commen"},{"reply_id":5,"name":"魏老师","name_to":"","content":"谢谢支持","code":"commen"}]
         */

        private String comments_id;
        private String content;
        private String head_portrait;
        private String name;
        private long comment_time;
        private List<ReplyListBean> reply_list;

        public String getComments_id() {
            return comments_id;
        }

        public void setComments_id(String comments_id) {
            this.comments_id = comments_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
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
}
