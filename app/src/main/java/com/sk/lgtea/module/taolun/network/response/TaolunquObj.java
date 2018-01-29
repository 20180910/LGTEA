package com.sk.lgtea.module.taolun.network.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class TaolunquObj implements Serializable {

    private List<DiscussionForumListBean> discussion_forum_list;

    public List<DiscussionForumListBean> getDiscussion_forum_list() {
        return discussion_forum_list;
    }

    public void setDiscussion_forum_list(List<DiscussionForumListBean> discussion_forum_list) {
        this.discussion_forum_list = discussion_forum_list;
    }

    public static class DiscussionForumListBean {
        /**
         * discussion_forum_id : 3
         * head_portrait : 
         * name : 魏老师
         * title : 如何才能学习好印刷出版呢?
         * content : 很多同学对于出版印刷都是比较头疼，很多同学对于出版印刷都是比较头疼,其实掌握好也是很简单的，要制一个印刷品，首先要看你印什么，书、包装袋、还是往别的物品上转印，
         * add_time : 1513082712
         * thumbup_count : 0
         * is_thumbup : 0
         * comment_count : 0
         * image_list : ["http://121.40.186.118:1554/upload/taolunqu.png","http://121.40.186.118:1554/upload/taolunqu.png","http://121.40.186.118:1554/upload/taolunqu.png"]
         */

        private String discussion_forum_id;
        private String head_portrait;
        private String name;
        private String title;
        private String content;
        private long add_time;
        private String thumbup_count;
        private String is_thumbup;
        private String comment_count;
        private List<String> image_list;

        public String getDiscussion_forum_id() {
            return discussion_forum_id;
        }

        public void setDiscussion_forum_id(String discussion_forum_id) {
            this.discussion_forum_id = discussion_forum_id;
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

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public String getThumbup_count() {
            return thumbup_count;
        }

        public void setThumbup_count(String thumbup_count) {
            this.thumbup_count = thumbup_count;
        }

        public String getIs_thumbup() {
            return is_thumbup;
        }

        public void setIs_thumbup(String is_thumbup) {
            this.is_thumbup = is_thumbup;
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
    }
}
