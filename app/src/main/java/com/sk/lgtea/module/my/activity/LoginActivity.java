package com.sk.lgtea.module.my.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.customview.MyCheckBox;
import com.github.customview.MyTextView;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.activity.MainActivity;
import com.sk.lgtea.module.my.network.ApiRequest;
import com.sk.lgtea.module.my.network.response.LoginObj;
import com.sk.lgtea.tools.download.util.FileUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_login_role)
    EditText et_login_role;
    @BindView(R.id.iv_login_dowm)
    ImageView iv_login_dowm;
    @BindView(R.id.et_login_gonghao)
    EditText et_login_gonghao;
    @BindView(R.id.et_login_pwd)
    EditText et_login_pwd;
    @BindView(R.id.cb_login_setting)
    MyCheckBox cb_login_setting;
    @BindView(R.id.tv_login_wangjimima)
    TextView tv_login_wangjimima;
    @BindView(R.id.tv_login)
    MyTextView tv_login;
    private String action;
    String gonghao,pwd;

    @Override
    protected int getContentView() {
        setAppTitle("登录");
        hiddenBackIcon(false);
        return R.layout.act_login;
    }

    @Override
    protected void initView() {

        float newVersionCode=getVersionCode(mContext);
        Log.i("===","===最新newVersionCode="+newVersionCode);

        float spVersionCode= SPUtils.getPrefFloat(mContext, Config.spVersionCode,0);
        Log.i("===","===spVersionCode="+newVersionCode);
        if (newVersionCode>spVersionCode) {
            SPUtils.setPrefFloat(mContext, Config.spVersionCode,newVersionCode);
            Log.i("===","===第一次");

            deleteDir(FileUtils.DOWNLOAD_DIR);
        }else {
            Log.i("===","===不是第一次");

        }



        action = getIntent().getAction();
        cb_login_setting.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //如果选中，显示密码
                    et_login_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    et_login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!"0".equals(getUserId())) {
            STActivity(MainActivity.class);
            finish();
        }


    }

    @Override
    protected void initData() {

    }
    private float getVersionCode(Context context) {
        float versionCode=0;
        try {
            versionCode=context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;

    }


    @OnClick({R.id.iv_login_dowm,  R.id.tv_login_wangjimima, R.id.tv_login})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_dowm:
                break;
            case R.id.tv_login_wangjimima:
                STActivity(ForgetPWDActivity.class);
                break;
            case R.id.tv_login:
                gonghao=getSStr(et_login_gonghao);
                pwd=getSStr(et_login_pwd);
                if(TextUtils.isEmpty(gonghao)) {
                    showToastS("请输入工号");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    showToastS("请输入密码");
                    return;
                }
                login();


                break;
        }
    }
    private void login() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",gonghao);
        map.put("password",pwd);
        map.put("type","2");
        map.put("RegistrationID", SPUtils.getPrefString(mContext, Config.jiguangRegistrationId,""));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.userLogin(map, new MyCallBack<LoginObj>(mContext) {
            @Override
            public void onSuccess(LoginObj obj) {
                loginResult(obj);
            }
        });
    }
    private void loginResult(LoginObj obj) {
        SPUtils.setPrefString(mContext, Config.user_id,obj.getUser_id());
        SPUtils.setPrefString(mContext, Config.user_name,obj.getUser_name());
        SPUtils.setPrefString(mContext, Config.name,obj.getName());
        SPUtils.setPrefString(mContext, Config.avatar,obj.getAvatar());
        SPUtils.setPrefString(mContext, Config.sex,obj.getSex());
        SPUtils.setPrefString(mContext, Config.mobile,obj.getMobile());
        SPUtils.setPrefBoolean(mContext, Config.user_switch, obj.getMessage_sink()==1?true:false);
        SPUtils.setPrefString(mContext, Config.class_name,obj.getClass_name());
        SPUtils.setPrefString(mContext, Config.email,obj.getEmail());

//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Config.Bro.operation));

        Intent intent=new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        STActivity(intent,MainActivity.class);

        finish();

    }
    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            if(Config.exitAPP.equals(action)){
                Intent intent=new Intent(Config.exitAPP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent,MainActivity.class);
            }
            super.onBackPressed();
        }
    }

}
