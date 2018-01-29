package com.sk.lgtea.module.home.network.response;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class KechengbiaoObj {

    /**
     * weekday : 1
     * time_hour : [{"course_id":1,"time":1,"content":"高等数学A-301"},{"course_id":0,"time":2,"content":""},{"course_id":2,"time":3,"content":"毛概A-301"},{"course_id":0,"time":4,"content":""}]
     */

    private int weekday;
    private List<TimeHourBean> time_hour;

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public List<TimeHourBean> getTime_hour() {
        return time_hour;
    }

    public void setTime_hour(List<TimeHourBean> time_hour) {
        this.time_hour = time_hour;
    }

    public static class TimeHourBean {
        /**
         * course_id : 1
         * time : 1
         * content : 高等数学A-301
         */

        private int course_id;
        private int time;
        private String content;

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
