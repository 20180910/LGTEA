package com.sk.lgtea.module.my.activity;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.baseclass.view.MyDialog;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.network.ApiRequest;
import com.sk.lgtea.tools.CacheUtil;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.ll_setting_xiugai)
    LinearLayout ll_setting_xiugai;
    @BindView(R.id.tv_setting_huancun)
    TextView tv_setting_huancun;
    @BindView(R.id.ll_setting_huancun)
    LinearLayout ll_setting_huancun;
    @BindView(R.id.sb_setting)
    SwitchButton sb_setting;
    @BindView(R.id.tv_setting_sign_out)
    TextView tv_setting_sign_out;
    boolean user_switch;
    String huancunNum="0K";

    @Override
    protected int getContentView() {
        setAppTitle("设置");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_setting;
    }

    @Override
    protected void initView() {
        try {
            huancunNum= CacheUtil.getExternalCacheSize(mContext);
            Log.i("===","===huancunNum="+huancunNum);
            tv_setting_huancun.setText(huancunNum);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("===","===读取缓存异常="+e.getMessage());
        }






        user_switch= SPUtils.getPrefBoolean(mContext, Config.user_switch,false);
        if (user_switch) {
            sb_setting.setChecked(true);
        }else {
            sb_setting.setChecked(false);
        }
        sb_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                    setSwitch();
                }
                return true;
            }
        });

    }
    private void setSwitch() {
        boolean checked = sb_setting.isChecked();
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("message_sink",!checked?"1":"0");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getMessageSink(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                sb_setting.setChecked(!checked);
                SPUtils.setPrefBoolean(mContext, Config.user_switch, !checked);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                sb_setting.setChecked(checked);
            }
        });
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.ll_setting_xiugai, R.id.ll_setting_huancun,R.id.tv_setting_sign_out})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_xiugai:
                STActivity(SettingPwdActivity.class);
                break;
            case R.id.ll_setting_huancun:



                if (huancunNum.equals("0K")) {
                    showMsg("暂无缓存");
                }else {
                    CacheUtil.clearAllCache(mContext);
                    showMsg("清除缓存成功");
                    try {
                        huancunNum=CacheUtil.getExternalCacheSize(mContext);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("===","===读取缓存异常="+e.getMessage());
                        huancunNum="0K";
                    }
                    tv_setting_huancun.setText(huancunNum);

                }

                break;
            case R.id.tv_setting_sign_out:
                mDialog = new MyDialog.Builder(mContext);
                mDialog.setMessage("是否确认退出登录?")
                        .setNegativeButton((dialog, which) -> dialog.dismiss())
                        .setPositiveButton((dialog, which) -> {
                            dialog.dismiss();
                            startExit();
                            exitLogin();

                        });
                mDialog.create().show();

                break;
        }
    }
    private void exitLogin() {
        SPUtils.removeKey(mContext, Config.user_id);
        Intent intent = new Intent(Config.exitAPP);
//        intent.putExtra(Config.Bro.flag, Config.Bro.exit_login);
//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        STActivity(intent,LoginActivity.class);
        finish();
    }
    private void startExit() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign", GetSign.getSign(map));
        com.sk.lgtea.network.ApiRequest.getLogOut(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {


            }
        });

    }

}
