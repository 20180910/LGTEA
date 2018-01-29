package com.sk.lgtea.module.home.network.response;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class KejiantongjiXiangqingObj {

    /**
     * add_time : 12月27日
     * courseware_record_list : [{"head_portrait":"http://121.40.186.118:1554/upload/201712/25/171225115632924738.jpg","name":"咯咯咯咯","add_time":"17:37"},{"head_portrait":"http://121.40.186.118:1554/upload/201712/23/171223103245180151.jpg","name":"咕咕咕咕","add_time":"16:50"}]
     */

    private String add_time;
    private List<CoursewareRecordListBean> courseware_record_list;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public List<CoursewareRecordListBean> getCourseware_record_list() {
        return courseware_record_list;
    }

    public void setCourseware_record_list(List<CoursewareRecordListBean> courseware_record_list) {
        this.courseware_record_list = courseware_record_list;
    }

    public static class CoursewareRecordListBean {
        /**
         * head_portrait : http://121.40.186.118:1554/upload/201712/25/171225115632924738.jpg
         * name : 咯咯咯咯
         * add_time : 17:37
         */

        private String head_portrait;
        private String name;
        private String add_time;

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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
