package com.sk.lgtea.module.home.network.response;


import com.sk.lgtea.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public class HistoryCourseWareListObj extends BaseObj {

    private List<TodayCourseWareBean> TodayCourseWare;
    private List<HistoryCourseWareBean> HistoryCourseWare;

    public List<TodayCourseWareBean> getTodayCourseWare() {
        return TodayCourseWare;
    }

    public void setTodayCourseWare(List<TodayCourseWareBean> TodayCourseWare) {
        this.TodayCourseWare = TodayCourseWare;
    }

    public List<HistoryCourseWareBean> getHistoryCourseWare() {
        return HistoryCourseWare;
    }

    public void setHistoryCourseWare(List<HistoryCourseWareBean> HistoryCourseWare) {
        this.HistoryCourseWare = HistoryCourseWare;
    }

    public static class TodayCourseWareBean {
        /**
         * courseware_id : 5
         * image_url : http://121.40.186.118:1554/upload/kejian4.png
         *  "video_pdf": "http://121.40.186.118:1554/upload/201712/08/201712081144443806.mp4"
         *  "attachmen": "http://121.40.186.118:1554/upload/201712/08/201712081144443806.mp4"
         * title : 小葵花爸爸课堂开课啦
         * keynote_speaker : 魏老师
         * add_time : 2017-12-21
         * sales : 4
         * thumbup_count : 0
         * courseware_record_count : 0
         */

        private String courseware_id;
        private String image_url;
        private String title;
        private String video_pdf;
        private String attachmen;
        private String keynote_speaker;
        private String add_time;
        private String sales;
        private String thumbup_count;
        private String courseware_record_count;

        public String getAttachmen() {
            return attachmen;
        }

        public void setAttachmen(String attachmen) {
            this.attachmen = attachmen;
        }

        public String getVideo_pdf() {
            return video_pdf;
        }

        public void setVideo_pdf(String video_pdf) {
            this.video_pdf = video_pdf;
        }

        public String getCourseware_id() {
            return courseware_id;
        }

        public void setCourseware_id(String courseware_id) {
            this.courseware_id = courseware_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKeynote_speaker() {
            return keynote_speaker;
        }

        public void setKeynote_speaker(String keynote_speaker) {
            this.keynote_speaker = keynote_speaker;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getThumbup_count() {
            return thumbup_count;
        }

        public void setThumbup_count(String thumbup_count) {
            this.thumbup_count = thumbup_count;
        }

        public String getCourseware_record_count() {
            return courseware_record_count;
        }

        public void setCourseware_record_count(String courseware_record_count) {
            this.courseware_record_count = courseware_record_count;
        }
    }

    public static class HistoryCourseWareBean {
        /**
         * courseware_id : 2
         * image_url : http://121.40.186.118:1554/upload/kejian2.png
         * attachmen : http://121.40.186.118:1554/upload/kejian2.png
         *
         * title : 小葵花爸爸课堂开课啦
         * keynote_speaker : 
         * add_time : 2017-12-18
         * sales : 7
         * thumbup_count : 0
         * courseware_record_count : 0
         */

        private String courseware_id;
        private String image_url;
        private String title;
        private String video_pdf;
        private String attachmen;
        private String keynote_speaker;
        private String add_time;
        private String sales;
        private String thumbup_count;
        private String courseware_record_count;

        public String getAttachmen() {
            return attachmen;
        }

        public void setAttachmen(String attachmen) {
            this.attachmen = attachmen;
        }

        public String getVideo_pdf() {
            return video_pdf;
        }

        public void setVideo_pdf(String video_pdf) {
            this.video_pdf = video_pdf;
        }

        public String getCourseware_id() {
            return courseware_id;
        }

        public void setCourseware_id(String courseware_id) {
            this.courseware_id = courseware_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKeynote_speaker() {
            return keynote_speaker;
        }

        public void setKeynote_speaker(String keynote_speaker) {
            this.keynote_speaker = keynote_speaker;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getThumbup_count() {
            return thumbup_count;
        }

        public void setThumbup_count(String thumbup_count) {
            this.thumbup_count = thumbup_count;
        }

        public String getCourseware_record_count() {
            return courseware_record_count;
        }

        public void setCourseware_record_count(String courseware_record_count) {
            this.courseware_record_count = courseware_record_count;
        }
    }
}
