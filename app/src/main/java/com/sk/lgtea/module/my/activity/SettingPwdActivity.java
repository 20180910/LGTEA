package com.sk.lgtea.module.my.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class SettingPwdActivity extends BaseActivity {
    @BindView(R.id.et_old_pwd)
    MyEditText et_old_pwd;
    @BindView(R.id.et_new_pwd)
    MyEditText et_new_pwd;
    @BindView(R.id.et_new_pwd2)
    MyEditText et_new_pwd2;
    @BindView(R.id.tv_setting_pwd_xiugai)
    MyTextView tv_setting_pwd_xiugai;
    String oldPassword,newPassword,newPassword2;


    @Override
    protected int getContentView() {
        setAppTitle("设置密码");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_setting_pwd;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_setting_pwd_xiugai})
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_setting_pwd_xiugai:
                oldPassword = getSStr(et_old_pwd);
                newPassword = getSStr(et_new_pwd);
                newPassword2 = getSStr(et_new_pwd2);
                if (TextUtils.isEmpty(oldPassword)) {
                    showMsg("原始密码不能为空");
                    return;
                }
                if (newPassword.length()<6||newPassword.length()>12) {
                    showMsg("密码长度为6至12位");
                    return;
                }

                if(!newPassword.equals(newPassword2)){
                    showMsg("两次密码不一样");
                    return;
                }
                setNewPassword();



                break;
        }
    }
    private void setNewPassword() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("oldPassword",oldPassword);
        map.put("newPassword",newPassword);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.setNewPassword(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();

            }
        });

    }
}
