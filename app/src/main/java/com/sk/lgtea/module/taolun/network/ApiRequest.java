package com.sk.lgtea.module.taolun.network;

import com.github.retrofitutil.NoNetworkException;
import com.sk.lgtea.Config;
import com.sk.lgtea.base.BaseApiRequest;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.taolun.network.request.BusinessEvaluationBody;
import com.sk.lgtea.module.taolun.network.request.FatieBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    /*public static Observable getRegisterXieYi(String rnd, String sign){
        return getCommonClient(com.sk.yangyu.module.home.network.IRequest.class).getPayNotifyUrl(rnd,sign).compose(RxResult.appSchedulers()).compose(RxResult.handleResult());
    }*/
    //讨论区帖子列表
    public static void getDiscussionForum(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getDiscussionForum(map).enqueue(callBack);
    }

    //发表商家评价
    public static void postMessage(Map map , FatieBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).postMessage(map,body).enqueue(callBack);
    }


    //讨论区帖子详情
    public static void getDiscussionForumDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getDiscussionForumDetail(map).enqueue(callBack);
    }

    //点赞/取消点赞--(课件/讨论区帖子)
    public static void getthumbupForum(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getthumbupForum(map).enqueue(callBack);
    }

    //帖子--发表评论/发表二级评论
    public static void getAddCommentDiscussionForum(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getAddCommentDiscussionForum(map).enqueue(callBack);
    }

    //回复评论
    public static void getAddReply(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getAddReply(map).enqueue(callBack);
    }
    //查看更多回复(弹框)
    public static void getCommentDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getCommentDetail(map).enqueue(callBack);
    }


    /******************************************************************************************************************/

    public static void tuanGouSureOrder(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).tuanGouSureOrder(map).enqueue(callBack);
    }
    //订单
    public static void getOrder(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).getOrder(map).enqueue(callBack);
    }

    //我的订单
    public static void getMyOrder(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).getMyOrder(map).enqueue(callBack);
    }

    //订单详情
    public static void getOrderDetail(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).getOrderDetail(map).enqueue(callBack);
    }

    //取消订单原因
    public static void getCancelReason(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).getCancelReason(map).enqueue(callBack);
    }

    //提交-取消订单
    public static void getCancelOrder(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).getCancelOrder(map).enqueue(callBack);
    }
    //发表商家评价
    public static void postPublishComment(Map map , BusinessEvaluationBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).postPublishComment(map,body).enqueue(callBack);
    }

    //取消订单(未付款的)
    public static void getCancelsOrder(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).getCancelsOrder(map).enqueue(callBack);
    }
}
