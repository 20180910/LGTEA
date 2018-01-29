package com.sk.lgtea.module.taolun.network;


import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.ResponseObj;
import com.sk.lgtea.module.taolun.network.request.BusinessEvaluationBody;
import com.sk.lgtea.module.taolun.network.request.FatieBody;
import com.sk.lgtea.module.taolun.network.response.DianzanObj;
import com.sk.lgtea.module.taolun.network.response.MoreReplyObj;
import com.sk.lgtea.module.taolun.network.response.MyOrderObj;
import com.sk.lgtea.module.taolun.network.response.OrderCancelReasonObj;
import com.sk.lgtea.module.taolun.network.response.OrderDetailsObj;
import com.sk.lgtea.module.taolun.network.response.OrderObj;
import com.sk.lgtea.module.taolun.network.response.TaolunDetailsObj;
import com.sk.lgtea.module.taolun.network.response.TaolunquObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    //讨论区帖子列表
    @GET("api/SHLGInformation/GetDiscussionForum")
    Call<ResponseObj<TaolunquObj>> getDiscussionForum(@QueryMap Map<String, String> map);

    //发帖(讨论区)
    @POST("api/SHLGTeacher/PostMessage")
    Call<ResponseObj<BaseObj>> postMessage(@QueryMap Map<String, String> map, @Body FatieBody body);

    //讨论区帖子详情
    @GET("api/SHLGInformation/GetDiscussionForumDetail")
    Call<ResponseObj<TaolunDetailsObj>> getDiscussionForumDetail(@QueryMap Map<String, String> map);

    //点赞/取消点赞--(课件/讨论区帖子)
    @GET("api/SHLGInformation/GetthumbupForum")
    Call<ResponseObj<DianzanObj>> getthumbupForum(@QueryMap Map<String, String> map);

    //帖子--发表评论/发表二级评论
    @GET("api/SHLGInformation/GetAddCommentDiscussionForum")
    Call<ResponseObj<BaseObj>> getAddCommentDiscussionForum(@QueryMap Map<String, String> map);

    //回复评论
    @GET("api/SHLGInformation/GetAddReply")
    Call<ResponseObj<BaseObj>> getAddReply(@QueryMap Map<String, String> map);

    //查看更多回复(弹框)
    @GET("api/SHLGInformation/GetCommentDetail")
    Call<ResponseObj<MoreReplyObj>> getCommentDetail(@QueryMap Map<String, String> map);



    /****************************************************************************************************************/
    @GET("api/HomePage/GetProductGroupOrderShow")
    Call<ResponseObj<BaseObj>> tuanGouSureOrder(@QueryMap Map<String, String> map);
    //订单
    @GET("api/Order/GetOrder")
    Call<ResponseObj<OrderObj>> getOrder(@QueryMap Map<String, String> map);
    //我的订单
    @GET("api/Order/GetMyOrder")
    Call<ResponseObj<MyOrderObj>> getMyOrder(@QueryMap Map<String, String> map);

    //订单详情
    @GET("api/Order/GetOrderDetail")
    Call<ResponseObj<OrderDetailsObj>> getOrderDetail(@QueryMap Map<String, String> map);

    //取消订单原因
    @GET(" api/Order/GetCancelReason")
    Call<ResponseObj <OrderCancelReasonObj>> getCancelReason(@QueryMap Map<String, String> map);

    //提交-取消订单
    @GET("api/Order/GetCancelOrder")
    Call<ResponseObj<BaseObj>> getCancelOrder(@QueryMap Map<String, String> map);

    //发表商家评价
    @POST("api/Order/PostPublishComment")
    Call<ResponseObj<BaseObj>>postPublishComment(@QueryMap Map<String, String> map, @Body BusinessEvaluationBody body);

    //取消订单(未付款的)
    @GET("api/Order/GetCancelsOrder")
    Call<ResponseObj<BaseObj>>getCancelsOrder(@QueryMap Map<String, String> map);

}
