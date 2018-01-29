package com.sk.lgtea.module.home.network.response;

import com.sk.lgtea.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class ZuoyetongjiObj extends BaseObj {

    /**
     * operation_id : 1
     * yijiao : 2
     * weijiao : 0
     * list_time : [{"tijiaolv":100,"time":"12-27"},{"tijiaolv":0,"time":"12-26"},{"tijiaolv":0,"time":"12-25"},{"tijiaolv":0,"time":"12-24"},{"tijiaolv":0,"time":"12-23"},{"tijiaolv":0,"time":"12-22"},{"tijiaolv":0,"time":"12-21"},{"tijiaolv":0,"time":"12-20"}]
     */

    private String operation_id;
    private int yijiao;
    private int weijiao;
    private List<ListTimeBean> list_time;

    public String getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }

    public int getYijiao() {
        return yijiao;
    }

    public void setYijiao(int yijiao) {
        this.yijiao = yijiao;
    }

    public int getWeijiao() {
        return weijiao;
    }

    public void setWeijiao(int weijiao) {
        this.weijiao = weijiao;
    }

    public List<ListTimeBean> getList_time() {
        return list_time;
    }

    public void setList_time(List<ListTimeBean> list_time) {
        this.list_time = list_time;
    }

    public static class ListTimeBean {
        /**
         * tijiaolv : 100
         * time : 12-27
         */

        private double tijiaolv;
        private String time;

        public double getTijiaolv() {
            return tijiaolv;
        }

        public void setTijiaolv(double tijiaolv) {
            this.tijiaolv = tijiaolv;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
