package com.sk.lgtea.module.my.network.response;


import com.sk.lgtea.base.BaseObj;

/**
 * Created by Administrator on 2017/11/21.
 */

public class AboutPlatformObj extends BaseObj {

    /**
     * banquansuoyou : copyrit@2017-2018
     * logo : http://121.40.186.118:1554/upload/201606/02/201606020049340415.png
     * official_website : http://www.usst.edu.cn/
     */

    private String banquansuoyou;
    private String logo;
    private String official_website;

    public String getBanquansuoyou() {
        return banquansuoyou;
    }

    public void setBanquansuoyou(String banquansuoyou) {
        this.banquansuoyou = banquansuoyou;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOfficial_website() {
        return official_website;
    }

    public void setOfficial_website(String official_website) {
        this.official_website = official_website;
    }
}
