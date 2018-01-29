package com.sk.lgtea.module.my.network;

import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.ResponseObj;
import com.sk.lgtea.module.my.network.request.AddAccountBody;
import com.sk.lgtea.module.my.network.request.DelMyCollectionBody;
import com.sk.lgtea.module.my.network.request.EditUserInfoBody;
import com.sk.lgtea.module.my.network.request.RegisterBody;
import com.sk.lgtea.module.my.network.response.AboutPlatformObj;
import com.sk.lgtea.module.my.network.response.AccountDefaultObj;
import com.sk.lgtea.module.my.network.response.AccountObj;
import com.sk.lgtea.module.my.network.response.BanbengengxinObj;
import com.sk.lgtea.module.my.network.response.ChongzhiCreateOrderObj;
import com.sk.lgtea.module.my.network.response.ChongzhiSuccessObj;
import com.sk.lgtea.module.my.network.response.CollectObj;
import com.sk.lgtea.module.my.network.response.ComperationObj;
import com.sk.lgtea.module.my.network.response.DaiEvaluateObj;
import com.sk.lgtea.module.my.network.response.FenxiaoObj;
import com.sk.lgtea.module.my.network.response.LoginObj;
import com.sk.lgtea.module.my.network.response.MessageListObj;
import com.sk.lgtea.module.my.network.response.MyEvaluateObj;
import com.sk.lgtea.module.my.network.response.MyShouyiObj;
import com.sk.lgtea.module.my.network.response.ShouyiObj;
import com.sk.lgtea.module.my.network.response.UserInfoObj;
import com.sk.lgtea.module.my.network.response.WithdrawalsObj;
import com.sk.lgtea.module.my.network.response.YueMingxiObj;

import java.util.List;
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

    //登录
    @GET("api/SHLGUser/GetUserLogins")
    Call<ResponseObj<LoginObj>> userLogin(@QueryMap Map<String, String> map);

    //是否接受消息推送设置
    @GET("api/SHLGUser/GetMessagSink")
    Call<ResponseObj<BaseObj>> getMessageSink(@QueryMap Map<String, String> map);
    //修改邮箱
    @GET("api/SHLGUser/GetEditEmail")
    Call<ResponseObj<BaseObj>> getEditEmail(@QueryMap Map<String, String> map);

    //修改手机号
    @GET("api/SHLGUser/GetEditPhone")
    Call<ResponseObj<BaseObj>> getEditPhone(@QueryMap Map<String, String> map);

    //获取用户资料
    @GET("api/SHLGUser/GetUserInfo")
    Call<ResponseObj<UserInfoObj>> getUserInfo(@QueryMap Map<String, String> map);

    //单独修改用户头像
    @GET("api/SHLGUser/GetSetUserAvatar")
    Call<ResponseObj<BaseObj>> updateUserImg(@QueryMap Map<String, String> map);

    //修改密码
    @GET("api/SHLGUser/GetSetNewPassword")
    Call<ResponseObj<BaseObj>> setNewPassword(@QueryMap Map<String, String> map);

    //关于平台信息
    @GET("api/SHLGInformation/GetPlatform_Instruction")
    Call<ResponseObj<AboutPlatformObj>> getAboutPlatform(@QueryMap Map<String, String> map);


    //提交-意见反馈
    @GET("api/SHLGUser/GetSubmitFeedback")
    Call<ResponseObj<BaseObj>> getSubmitFeedback(@QueryMap Map<String, String> map);

    //重置密码(忘记密码)
    @GET("api/SHLGUser/GetSetPassword")
    Call<ResponseObj<LoginObj>> forgetPWD(@QueryMap Map<String, String> map);

    //我的收藏
    @GET("api/SHLGInformation/GetMyCollection")
    Call<ResponseObj<CollectObj>> getMyCollection(@QueryMap Map<String, String> map);

    //删除我的收藏
    @GET("api/SHLGInformation/GetDelMyCollect")
    Call<ResponseObj<BaseObj>> getDelMyCollect(@QueryMap Map<String, String> map);

    //修改老师简介
    @GET("api/SHLGUser/GetSetIndividualitySignature")
    Call<ResponseObj<BaseObj>> getSetIndividualitySignature(@QueryMap Map<String, String> map);


    /**************************************************************************/
    @GET("api/HomePage/GetProductGroupOrderShow")
    Call<ResponseObj<BaseObj>> tuanGouSureOrder(@QueryMap Map<String, String> map);


    //注册
    @POST("api/UserBase/PostUserRegister")
    Call<ResponseObj<BaseObj>> register(@QueryMap Map<String, String> map, @Body RegisterBody body);





    //修改信息（昵称，姓名，生日，性别，头像）
    @POST("api/UserBase/PostEditUserInfo")
    Call<ResponseObj<BaseObj>> editUserInfo(@QueryMap Map<String, String> map, @Body EditUserInfoBody body);


    //我的消息列表
    @GET("api/UserBase/GetNewsList")
    Call<ResponseObj<List<MessageListObj>>> getNewsList(@QueryMap Map<String, String> map);

//    //我的消息详情
//    @GET("api/UserBase/GetNewsDetail")
//    Call<ResponseObj<MessageDetailObj>> getNewsDetail(@QueryMap Map<String, String> map);

    //我的分销码
    @GET("api/UserBase/GetMyDistributionYard")
    Call<ResponseObj<FenxiaoObj>> getMyFenxiao(@QueryMap Map<String, String> map);





    //删除我的收藏
    @POST("api/UserBase/PostDelMyCollection")
    Call<ResponseObj<BaseObj>> delMyCollection(@QueryMap Map<String, String> map, @Body List<DelMyCollectionBody> body);



    //我的收益
    @GET("api/UserBase/GetMyIncome")
    Call<ResponseObj<MyShouyiObj>> getMyShouyi(@QueryMap Map<String, String> map);

    //我要合作页面
    @GET("api/Information/GetCooperation")
    Call<ResponseObj<ComperationObj>> getComperation(@QueryMap Map<String, String> map);

    //提现选择账户列表
    @GET("api/CashWithdrawal/GetAccount")
    Call<ResponseObj<List<AccountObj>>> getAccount(@QueryMap Map<String, String> map);
    //绑定 银行卡
    @POST("api/CashWithdrawal/PostAddAccount")
    Call<ResponseObj<BaseObj>> postAddAccount(@QueryMap Map<String, String> map, @Body AddAccountBody body);

    //获取默认账户
    @GET("api/CashWithdrawal/GetAccountDefault")
    Call<ResponseObj<AccountDefaultObj>> getAccountDefault(@QueryMap Map<String, String> map);

    //设置默认-提现账户
    @GET("api/CashWithdrawal/GetEditDefalut")
    Call<ResponseObj<BaseObj>> getEditDefault(@QueryMap Map<String, String> map);
    //账户余额-提现申请
    @GET("api/CashWithdrawal/GetWithdrawals")
    Call<ResponseObj<WithdrawalsObj>> getWithdrawals(@QueryMap Map<String, String> map);

    //我的评价
    @GET("api/UserBase/GetMyAppraise")
    Call<ResponseObj<MyEvaluateObj>> getMyAppraise(@QueryMap Map<String, String> map);

    //待评价列表
    @GET("api/UserBase/GetDaiPingJia")
    Call<ResponseObj<DaiEvaluateObj>> getDaiPingJia(@QueryMap Map<String, String> map);

    //账户充值-生成订单
    @GET("api/CashWithdrawal/GetCreateOrder")
    Call<ResponseObj<ChongzhiCreateOrderObj>> getCreateOrder(@QueryMap Map<String, String> map);

    //账户余额-明细
    @GET(" api/CashWithdrawal/GetMyBalance")
    Call<ResponseObj<List<YueMingxiObj>>> getMyBalance(@QueryMap Map<String, String> map);

    //删除-我的账户(银行卡)列表
    @GET(" api/CashWithdrawal/GetDelAccount")
    Call<ResponseObj<BaseObj>> getDelAccount(@QueryMap Map<String, String> map);

    //账户充值结束界面数据
    @GET("api/Order/GetPayRecharge")
    Call<ResponseObj<ChongzhiSuccessObj>> getPayRecharge(@QueryMap Map<String, String> map);

    //收益-转入余额
    @GET("api/CashWithdrawal/GetCommissionWithdrawals")
    Call<ResponseObj<ShouyiObj>> getCommissionWithdrawals(@QueryMap Map<String, String> map);




}
