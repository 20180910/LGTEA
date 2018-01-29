package com.sk.lgtea.network.response;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/1/12.
 */

public interface AppServiceApi {
    /**
     * 下载最新模板
     *
     * @return
     */

    @GET
    Call<ResponseBody> downloadLatestFeature(@Url String fileUrl);


}
