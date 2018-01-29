package com.sk.lgtea.module.home.network.response;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class KejiantongjiObj {

    /**
     * add_time : 2017-12-25
     * courseware_list : [{"courseware_id":10,"title":"啦啦啦啦啦","liulan_list":1}]
     */

    private String add_time;
    private List<CoursewareListBean> courseware_list;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public List<CoursewareListBean> getCourseware_list() {
        return courseware_list;
    }

    public void setCourseware_list(List<CoursewareListBean> courseware_list) {
        this.courseware_list = courseware_list;
    }

    public static class CoursewareListBean {
        /**
         * courseware_id : 10
         * title : 啦啦啦啦啦
         * liulan_list : 1
         */

        private String courseware_id;
        private String title;
        private String liulan_list;

        public String getCourseware_id() {
            return courseware_id;
        }

        public void setCourseware_id(String courseware_id) {
            this.courseware_id = courseware_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLiulan_list() {
            return liulan_list;
        }

        public void setLiulan_list(String liulan_list) {
            this.liulan_list = liulan_list;
        }
    }
}
