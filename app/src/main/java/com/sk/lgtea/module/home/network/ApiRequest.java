package com.sk.lgtea.module.home.network;

import com.github.retrofitutil.NoNetworkException;
import com.sk.lgtea.Config;
import com.sk.lgtea.base.BaseApiRequest;
import com.sk.lgtea.base.MyCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    /*public static Observable getRegisterXieYi(String rnd, String sign){
        return getCommonClient(com.sk.yangyu.module.home.network.IRequest.class).getPayNotifyUrl(rnd,sign).compose(RxResult.appSchedulers()).compose(RxResult.handleResult());
    }*/

    //课程表
    public static void getCurriculumSchedule(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getCurriculumSchedule(map).enqueue(callBack);
    }
    //首页轮播图信息
    public static void getRoastingChart(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getRoastingChart(map).enqueue(callBack);
    }

    //首页类别集合信息-老师(课程表、考勤、课件、统计)
    public static void getTypeAssemBlage(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getTypeAssemBlage(map).enqueue(callBack);
    }


    //首页学院咨询列表数据
    public static void getInformationList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getInformationList(map).enqueue(callBack);
    }

    //课件统计
    public static void getCourseWareStatisticList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getCourseWareStatisticList(map).enqueue(callBack);
    }

    //课件统计详情
    public static void getCourseWareStatisticDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getCourseWareStatisticDetail(map).enqueue(callBack);
    }

    //作业统计-课程列表
    public static void getCourseList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getCourseList(map).enqueue(callBack);
    }

    //作业统计
    public static void getHomeworkStatistic(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getHomeworkStatistic(map).enqueue(callBack);
    }
    //作业提交记录列表-已提交
    public static void getYitijiao(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getYitijiao(map).enqueue(callBack);
    }

    //作业提交记录列表-未提交
    public static void getWeitijiao(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getWeitijiao(map).enqueue(callBack);
    }

    //提醒/批量提醒
    public static void getReminding(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getReminding(map).enqueue(callBack);
    }

    //本周作业详情
    public static void getWeekOperationDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getWeekOperationDetail(map).enqueue(callBack);
    }
    //获取首页右上角和作业未读消息条数
    public static void getUnreadNews(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getUnreadNews(map).enqueue(callBack);
    }

    //我的消息列表
    public static void getNewList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getNewList(map).enqueue(callBack);
    }
    //我的消息详情
    public static void getNewsDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getNewsDetail(map).enqueue(callBack);
    }

    //我的课件
    public static void getHistorysCourseWareList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getHistorysCourseWareList(map).enqueue(callBack);
    }

    //课件详情
    public static void getCourseWareDetail(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getCourseWareDetail(map).enqueue(callBack);
    }

    //课件--发表评论/发表二级评论
    public static void getAddCommentCourseWare(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getAddCommentCourseWare(map).enqueue(callBack);
    }

    //增加下载记录(下载完成后调取)
    public static void getDownloadRecord(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getDownloadRecord(map).enqueue(callBack);
    }

    //收藏/取消收藏
    public static void getCollectMerchant(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getCollectMerchant(map).enqueue(callBack);
    }

    //考勤统计
    public static void getAttendanceStatistic(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getAttendanceStatistic(map).enqueue(callBack);
    }

    //考勤记录
    public static void getAttendanceStatisticDetail(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getAttendanceStatisticDetail(map).enqueue(callBack);
    }

    //考勤记录
    public static void getQrCode(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getQrCode(map).enqueue(callBack);
    }
    //学院咨询详情
    public static void getInformationMore(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getInformationMore(map).enqueue(callBack);
    }

/***************************************************************************************************************/

    public static void tuanGouSureOrder(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).tuanGouSureOrder(map).enqueue(callBack);
    }

//    //首页轮播图信息
//    public static void postRoastingChart(Map map, HomeRoastingChartBody body, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).postRoastingChart(map, body).enqueue(callBack);
//    }
//
//    //首页类别集合信息
//    public static void getTypeAssemblage(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getTypeAssemblage(map).enqueue(callBack);
//    }
//
//    //首页中部图片信息
//    public static void getHomePageImage(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getHomePageImage(map).enqueue(callBack);
//    }
//
//    //首页每日精选
//    public static void getDailybest(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getDailybest(map).enqueue(callBack);
//    }
//
//    //根据城市名获取ID
//    public static void getCityId(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getCityId(map).enqueue(callBack);
//    }
//
//    //获取全部区/县商业圈
//    public static void getAreaBusinessCircle(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getAreaBusinessCircle(map).enqueue(callBack);
//    }
//    //商家列表(分类)
//    public static void postMerchantList(Map map, HomeTypeMerchantListBody body, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).postMerchantList(map,body).enqueue(callBack);
//    }
//
//    //首页公告信息
//    public static void getAnnouncement(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getAnnouncement(map).enqueue(callBack);
//    }
//
//    //获取首页右上角未读消息状态(红点)
//    public static void getUnreadNews(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getUnreadNews(map).enqueue(callBack);
//    }
//
//    //热门搜索词、历史搜索词
//    public static void getHottestSearch(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getHottestSearch(map).enqueue(callBack);
//    }
//
//    //删除历史搜索词
//    public static void getDelRecentlySearch(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getDelRecentlySearch(map).enqueue(callBack);
//    }
//    //搜索商家
//    public static void postSearchMerchant(Map map, SearchResultBody body, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).postSearchMerchant(map,body).enqueue(callBack);
//    }
//
//    //猜你喜欢
//    public static void getGuessYouLike(Map map, MyCallBack callBack) {
//        if (notNetWork(callBack.getContext())) {
//            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
//            return;
//        }
//        getGeneralClient(IRequest.class).getGuessYouLike(map).enqueue(callBack);
//    }
}
