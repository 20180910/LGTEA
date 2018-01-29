package com.sk.lgtea.module.home.network;


import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.ResponseObj;
import com.sk.lgtea.module.home.network.response.BenzhouzuoyeObj;
import com.sk.lgtea.module.home.network.response.ErweimaObj;
import com.sk.lgtea.module.home.network.response.HistoryCourseWareListObj;
import com.sk.lgtea.module.home.network.response.HomeRoastingChartObj;
import com.sk.lgtea.module.home.network.response.InformationListObj;
import com.sk.lgtea.module.home.network.response.InformationMoreObj;
import com.sk.lgtea.module.home.network.response.KaoqinObj;
import com.sk.lgtea.module.home.network.response.KaoqinjilvObj;
import com.sk.lgtea.module.home.network.response.KechengbiaoObj;
import com.sk.lgtea.module.home.network.response.KechengliebiaoObj;
import com.sk.lgtea.module.home.network.response.KejiantongjiObj;
import com.sk.lgtea.module.home.network.response.KejiantongjiXiangqingObj;
import com.sk.lgtea.module.home.network.response.NewListObj;
import com.sk.lgtea.module.home.network.response.NewsDetailObj;
import com.sk.lgtea.module.home.network.response.StudyDetailObj;
import com.sk.lgtea.module.home.network.response.TypeAssemBlageObj;
import com.sk.lgtea.module.home.network.response.UnreadNewsObj;
import com.sk.lgtea.module.home.network.response.WeitijiaoObj;
import com.sk.lgtea.module.home.network.response.YitijiaoObj;
import com.sk.lgtea.module.home.network.response.ZuoyetongjiObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {


   //课程表
    @GET ("api/SHLGInformation/GetCurriculumSchedule")
    Call<ResponseObj<List<KechengbiaoObj>>> getCurriculumSchedule(@QueryMap Map<String, String> map);

 //首页轮播图信息
 @GET ("api/SHLGInformation/GetRoastingChart")
 Call<ResponseObj<HomeRoastingChartObj>> getRoastingChart(@QueryMap Map<String, String> map);

 //首页类别集合信息-学生(学习、课程表、考勤、作业)
 @GET ("api/SHLGInformation/GetTypeAssemBlageStudent")
 Call<ResponseObj<List<TypeAssemBlageObj>>> getTypeAssemBlage(@QueryMap Map<String, String> map);

 //首页学院咨询列表数据
 @GET ("api/SHLGInformation/GetInformationList")
 Call<ResponseObj<List<InformationListObj>>> getInformationList(@QueryMap Map<String, String> map);

 //课件统计
 @GET ("api/SHLGTeacher/GetCourseWareStatisticList")
 Call<ResponseObj<List<KejiantongjiObj>>> getCourseWareStatisticList(@QueryMap Map<String, String> map);
 //课件统计详情
 @GET ("api/SHLGTeacher/GetCourseWareStatisticDetail")
 Call<ResponseObj<List<KejiantongjiXiangqingObj>>> getCourseWareStatisticDetail(@QueryMap Map<String, String> map);

 //作业统计-课程列表
 @GET ("api/SHLGTeacher/GetCourseList")
 Call<ResponseObj<List<KechengliebiaoObj>>> getCourseList(@QueryMap Map<String, String> map);

 //作业统计
 @GET ("api/SHLGTeacher/GetHomeworkStatistic")
 Call<ResponseObj<ZuoyetongjiObj>> getHomeworkStatistic(@QueryMap Map<String, String> map);
 //作业提交记录列表-已提交
 @GET ("api/SHLGTeacher/GetOperationStatisticBeenSubmitteDetail")
 Call<ResponseObj<List<YitijiaoObj>>> getYitijiao(@QueryMap Map<String, String> map);

 //作业提交记录列表-未提交
 @GET ("api/SHLGTeacher/GetOperationStatisticNotBeenSubmitteDetail")
 Call<ResponseObj<List<WeitijiaoObj>>> getWeitijiao(@QueryMap Map<String, String> map);

 //提醒/批量提醒
 @GET ("api/SHLGTeacher/GetReminding")
 Call<ResponseObj<BaseObj>> getReminding(@QueryMap Map<String, String> map);

 //本周作业详情
 @GET ("api/SHLGTeacher/GetWeekOperationDetail")
 Call<ResponseObj<List<BenzhouzuoyeObj>>> getWeekOperationDetail(@QueryMap Map<String, String> map);

 //获取首页右上角和作业未读消息条数
 @GET ("api/SHLGInformation/GetUnreadNews")
 Call<ResponseObj<UnreadNewsObj>> getUnreadNews(@QueryMap Map<String, String> map);

 //我的消息列表
 @GET ("api/SHLGUser/GetNewList")
 Call<ResponseObj<List<NewListObj>>> getNewList(@QueryMap Map<String, String> map);

 //我的消息详情
 @GET ("api/SHLGUser/GetNewsDetail")
 Call<ResponseObj<NewsDetailObj>> getNewsDetail(@QueryMap Map<String, String> map);

 //我的课件
 @GET ("api/SHLGTeacher/GetHistorysCourseWareList")
 Call<ResponseObj<HistoryCourseWareListObj>> getHistorysCourseWareList(@QueryMap Map<String, String> map);

 // 课件详情
 @GET("api/SHLGInformation/GetCourseWareDetail")
 Call<ResponseObj<StudyDetailObj>> getCourseWareDetail(@QueryMap Map<String, String> map);

 // 课件--发表评论/发表二级评论
 @GET("api/SHLGInformation/GetAddCommentCourseWare")
 Call<ResponseObj<BaseObj>> getAddCommentCourseWare(@QueryMap Map<String, String> map);

 // 增加下载记录(下载完成后调取)
 @GET("api/SHLGInformation/GetDownloadRecord")
 Call<ResponseObj<BaseObj>> getDownloadRecord(@QueryMap Map<String, String> map);

 // 收藏/取消收藏
 @GET("api/SHLGInformation/GetCollectMerchant")
 Call<ResponseObj<BaseObj>> getCollectMerchant(@QueryMap Map<String, String> map);


 // 考勤统计
 @GET("api/SHLGTeacher/GetAttendanceStatistic")
 Call<ResponseObj<KaoqinObj>> getAttendanceStatistic(@QueryMap Map<String, String> map);

 // 考勤记录
 @GET("api/SHLGTeacher/GetAttendanceStatisticDetail")
 Call<ResponseObj<KaoqinjilvObj>> getAttendanceStatisticDetail(@QueryMap Map<String, String> map);

 // 展示二维码
 @GET("api/SHLGTeacher/GetQrCode")
 Call<ResponseObj<ErweimaObj>> getQrCode(@QueryMap Map<String, String> map);


 //学院咨询详情
 @GET ("api/SHLGInformation/GetInformationMore")
 Call<ResponseObj<InformationMoreObj>> getInformationMore(@QueryMap Map<String, String> map);





 /**************************************分割线******************************************/






    @GET("api/HomePage/GetProductGroupOrderShow")
    Call<ResponseObj<BaseObj>> tuanGouSureOrder(@QueryMap Map<String, String> map);

//    //首页轮播图信息
//    @POST("api/Information/PostRoastingChart")
//    Call<ResponseObj<HomeRoastingChartObj>> postRoastingChart(@QueryMap Map<String, String> map, @Body HomeRoastingChartBody body);
//    //首页类别集合信息
//    @GET("api/Information/GetTypeAssemblage")
//    Call<ResponseObj<HomeTypeAssemblageObj>> getTypeAssemblage(@QueryMap Map<String, String> map);
//
//    //首页中部图片信息
//    @GET("api/Information/GetHomePageImage")
//    Call<ResponseObj<HomePageImageObj>> getHomePageImage(@QueryMap Map<String, String> map);
//
//    //首页每日精选
//    @GET("api/Information/GetDailybest")
//    Call<ResponseObj<HomeDailybestObj>> getDailybest(@QueryMap Map<String, String> map);
//
//    //根据城市名获取ID
//    @GET("api/Lib/GetCityID")
//    Call<ResponseObj<CityIdObj>> getCityId(@QueryMap Map<String, String> map);
//
//    //获取全部区/县商业圈
//    @GET("api/Lib/GetAreaBusinessCircle")
//    Call<ResponseObj<List<AreaBusinessCircleObj>>> getAreaBusinessCircle(@QueryMap Map<String, String> map);
//    //商家列表(分类)
//    @POST("api/MerchantCenter/PostMerchantList")
//    Call<ResponseObj<HomeTypeMerchantListObj>> postMerchantList(@QueryMap Map<String, String> map, @Body HomeTypeMerchantListBody body);
//
//    //首页公告信息
//    @GET("api/Information/GetAnnouncement")
//    Call<ResponseObj<List<HomeAnnouncementObj>>> getAnnouncement(@QueryMap Map<String, String> map);
//
//
//    //获取首页右上角未读消息状态(红点)
//    @GET("api/Information/GetUnreadNews")
//    Call<ResponseObj<HomeUnreadNews>> getUnreadNews(@QueryMap Map<String, String> map);
//
//    //热门搜索词、历史搜索词
//    @GET("api/MerchantCenter/GetHottestSearch")
//    Call<ResponseObj<SearchObj>> getHottestSearch(@QueryMap Map<String, String> map);
//
//    //删除历史搜索词
//    @GET("api/MerchantCenter/GetDelRecentlySearch")
//    Call<ResponseObj<BaseObj>> getDelRecentlySearch(@QueryMap Map<String, String> map);
//
//    //搜索商家
//    @POST("api/MerchantCenter/PostSearchMerchant")
//    Call<ResponseObj<SearchResultObj>>postSearchMerchant(@QueryMap Map<String, String> map, @Body SearchResultBody body);
//
//    //猜你喜欢
//    @GET("api/Information/GetGuessYouLike")
//    Call<ResponseObj<HomeLikeObj>>getGuessYouLike(@QueryMap Map<String, String> map);



}
