package com.sk.lgtea.module.home.network.response;


import com.sk.lgtea.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public class StudyDetailObj extends BaseObj {


    /**
     * courseware_id : 1
     * video_pdf : http://121.40.186.118:1554/upload/201712/08/201712081041304454.mp4
     * attachment : http://121.40.186.118:1554/upload/201712/08/201712081041304454.mp4
     * title : 小葵花爸爸课堂开课啦
     * keynote_speaker_image :
     * keynote_speaker : 魏老师
     * individuality_signature : 魏老师
     * courseware_introduction : 孩子发烧老不好?多半是装的,打一顿就好了呀
     * is_thumbup : 1
     * is_collect : 1
     * thumbup_count : 1
     * comment_count : 4
     * comment_list : [{"comments_id":3,"content":"支持支持 热烈支持3","photo":"","name":"","comment_time":1512530486,"reply_list":[{"reply_id":3,"name":"","name_to":"","content":"谢谢支持","code":"commen"}]},{"comments_id":2,"content":"支持支持 热烈支持2","photo":"","name":"","comment_time":1512530486,"reply_list":[{"reply_id":2,"name":"","name_to":"","content":"谢谢支持","code":"commen"}]},{"comments_id":1,"content":"支持支持 热烈支持1","photo":"","name":"","comment_time":1512530486,"reply_list":[{"reply_id":9,"name":"咕**","name_to":"","content":"哎呦喂！","code":"commen"},{"reply_id":7,"name":"咕**","name_to":"","content":"那你很棒棒哦！你是不是二","code":"commen"}]}]
     */

    private String courseware_id;
    private String video_pdf;
    private String attachment;
    private String title;
    private String individuality_signature;
    private String keynote_speaker_image;
    private String keynote_speaker;
    private String courseware_introduction;
    private String is_thumbup;
//    private String is_collect;
    private String thumbup_count;
    private String comment_count;

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getIndividuality_signature() {
        return individuality_signature;
    }

    public void setIndividuality_signature(String individuality_signature) {
        this.individuality_signature = individuality_signature;
    }

    private List<CommentListBean> comment_list;

    public String getCourseware_introduction() {
        return courseware_introduction;
    }

    public void setCourseware_introduction(String courseware_introduction) {
        this.courseware_introduction = courseware_introduction;
    }



//    public String getIs_collect() {
//        return is_collect;
//    }
//
//    public void setIs_collect(String is_collect) {
//        this.is_collect = is_collect;
//    }

    public String getCourseware_id() {
        return courseware_id;
    }

    public void setCourseware_id(String courseware_id) {
        this.courseware_id = courseware_id;
    }

    public String getVideo_pdf() {
        return video_pdf;
    }

    public void setVideo_pdf(String video_pdf) {
        this.video_pdf = video_pdf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeynote_speaker_image() {
        return keynote_speaker_image;
    }

    public void setKeynote_speaker_image(String keynote_speaker_image) {
        this.keynote_speaker_image = keynote_speaker_image;
    }

    public String getKeynote_speaker() {
        return keynote_speaker;
    }

    public void setKeynote_speaker(String keynote_speaker) {
        this.keynote_speaker = keynote_speaker;
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

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public List<CommentListBean> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<CommentListBean> comment_list) {
        this.comment_list = comment_list;
    }

    public static class CommentListBean {
        /**
         * comments_id : 3
         * content : 支持支持 热烈支持3
         * photo :
         * name :
         * comment_time : 1512530486
         * reply_list : [{"reply_id":3,"name":"","name_to":"","content":"谢谢支持","code":"commen"}]
         */

        private String comments_id;
        private String content;
        private String photo;
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

        public List<ReplyListBean> getReply_list() {
            return reply_list;
        }

        public void setReply_list(List<ReplyListBean> reply_list) {
            this.reply_list = reply_list;
        }

        public static class ReplyListBean {
            /**
             * reply_id : 3
             * name :
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
