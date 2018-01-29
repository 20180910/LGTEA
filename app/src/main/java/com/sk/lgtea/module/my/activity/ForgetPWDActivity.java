package com.sk.lgtea.module.my.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.customview.MyTextView;
import com.github.rxjava.rxbus.RxUtils;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.Constant;
import com.sk.lgtea.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Administrator on 2017/12/5.
 */

public class ForgetPWDActivity extends BaseActivity {
    @BindView(R.id.et_forget_pwd_get_youxiang)
    EditText et_forget_pwd_get_youxiang;
    @BindView(R.id.et_forget_pwd_get_yanzhengma)
    EditText et_forget_pwd_get_yanzhengma;
    @BindView(R.id.tv_forget_pwd_get_cord)
    TextView tv_forget_pwd_get_cord;
    @BindView(R.id.tv_forget_pwd_next)
    MyTextView tv_forget_pwd_next;
    String email,smsCode,getCode;

    @Override
    protected int getContentView() {
        setAppTitle("忘记密码");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_forget_pwd;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_forget_pwd_get_cord, R.id.tv_forget_pwd_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pwd_get_cord:

                email=getSStr(et_forget_pwd_get_youxiang);
                if (TextUtils.isEmpty(email)) {
                    showMsg("请输入邮箱！");
                    return;
                }
                if(!GetSign.isEmail(email)){
                    showMsg("请输入正确邮箱!");
                    return;
                }
                getMsgCode();

                break;
            case R.id.tv_forget_pwd_next:
                email=getSStr(et_forget_pwd_get_youxiang);

                getCode=getSStr(et_forget_pwd_get_yanzhengma);

                if (TextUtils.isEmpty(email)) {
                    showMsg("请输入邮箱！");
                    return;
                }
                if(!GetSign.isEmail(email)){
                    showMsg("请输入正确邮箱!");
                    return;
                }


                if (TextUtils.isEmpty(getCode)) {
                    showMsg("请输入邮箱验证码");
                    return;
                }
                if (!getCode.equals(smsCode)) {
                    showMsg("请输入正确的邮箱验证码");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.mailbox,email);
                STActivity(intent,BackPwdActivity.class);
                finish();
                break;
        }
    }
    private void getMsgCode() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("email",email);
        map.put("rnd",getRnd());
        String sign = GetSign.getSign(map);
        map.put("sign", sign);
        showLoading();
        ApiRequest.getSMSCode(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                smsCode = obj.getEmailCode();
                countDown();
                showMsg("请查看邮箱邀请码");
            }
        });

    }
    private void countDown() {
        tv_forget_pwd_get_cord.setEnabled(false);
        final long count = 30;
        Subscription subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .take(31)//计时次数
                .map(integer -> count - integer)
                .compose(RxUtils.appSchedulers())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        tv_forget_pwd_get_cord.setEnabled(true);
                        tv_forget_pwd_get_cord.setText("获取验证码");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        tv_forget_pwd_get_cord.setText("剩下" + aLong + "s");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        addSubscription(subscribe);
    }
}
