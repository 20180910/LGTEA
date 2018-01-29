package com.sk.lgtea.module.home.network.response;

import com.sk.lgtea.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class KaoqinjilvObj extends BaseObj {

    /**
     * total_number : 1
     * list1 : [{"head_portrait":"http://121.40.186.118:1554/upload/201712/25/171225115632924738.jpg","name":"咯咯咯咯","add_time":""}]
     */

    private int total_number;
    private List<List1Bean> list1;

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public List<List1Bean> getList1() {
        return list1;
    }

    public void setList1(List<List1Bean> list1) {
        this.list1 = list1;
    }

    public static class List1Bean {
        /**
         * head_portrait : http://121.40.186.118:1554/upload/201712/25/171225115632924738.jpg
         * name : 咯咯咯咯
         * add_time :
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
