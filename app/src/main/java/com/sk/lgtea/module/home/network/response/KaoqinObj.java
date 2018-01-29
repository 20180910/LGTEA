package com.sk.lgtea.module.home.network.response;

import com.sk.lgtea.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class KaoqinObj extends BaseObj {


    /**
     * chuqin : 0
     * queqin : 2
     * list_time : [{"time":"1-2","tijiaolv":0,"weijiao":0,"yijiao":0},{"time":"1-1","tijiaolv":0,"weijiao":0,"yijiao":0},{"time":"12-31","tijiaolv":0,"weijiao":0,"yijiao":0},{"time":"12-30","tijiaolv":0,"weijiao":0,"yijiao":0},{"time":"12-29","tijiaolv":50,"weijiao":0,"yijiao":0},{"time":"12-28","tijiaolv":0,"weijiao":0,"yijiao":0},{"time":"12-27","tijiaolv":0,"weijiao":0,"yijiao":0}]
     */

    private int chuqin;
    private int queqin;
    private List<ListTimeBean> list_time;

    public int getChuqin() {
        return chuqin;
    }

    public void setChuqin(int chuqin) {
        this.chuqin = chuqin;
    }

    public int getQueqin() {
        return queqin;
    }

    public void setQueqin(int queqin) {
        this.queqin = queqin;
    }

    public List<ListTimeBean> getList_time() {
        return list_time;
    }

    public void setList_time(List<ListTimeBean> list_time) {
        this.list_time = list_time;
    }

    public static class ListTimeBean {
        /**
         * time : 1-2
         * tijiaolv : 0
         * weijiao : 0
         * yijiao : 0
         */

        private String time;
        private double tijiaolv;
        private int weijiao;
        private int yijiao;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getTijiaolv() {
            return tijiaolv;
        }

        public void setTijiaolv(double tijiaolv) {
            this.tijiaolv = tijiaolv;
        }

        public int getWeijiao() {
            return weijiao;
        }

        public void setWeijiao(int weijiao) {
            this.weijiao = weijiao;
        }

        public int getYijiao() {
            return yijiao;
        }

        public void setYijiao(int yijiao) {
            this.yijiao = yijiao;
        }
    }
}
