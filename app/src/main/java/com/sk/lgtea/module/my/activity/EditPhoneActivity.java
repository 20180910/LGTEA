package com.sk.lgtea.module.my.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.Constant;
import com.sk.lgtea.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/7.
 */

public class EditPhoneActivity extends BaseActivity {
    @BindView(R.id.et_edit)
    MyEditText et_edit;
    @BindView(R.id.tv_edit_baocun)
    MyTextView tv_edit_baocun;
    String type,value;

    @Override
    protected int getContentView() {
        setAppTitle("修改手机号");
        setBackIcon(R.drawable.back_white);


        return R.layout.act_edit_phone;
    }

    @Override
    protected void initView() {

//        pwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        getValue();

    }

    private void getValue() {

        type=getIntent().getStringExtra(Constant.IParam.type);
        value=getIntent().getStringExtra(Constant.IParam.value);


        /**
         *  Qianming.putExtra(Constant.IParam.type, Constant.IParam.qianming);
         Qianming.putExtra(Constant.IParam.value, individuality_signature);
         STActivityForResult(Qianming, EditPhoneActivity.class, 003);
         */

        if (type.equals(Constant.IParam.phone)) {
            et_edit.setInputType(InputType.TYPE_CLASS_NUMBER);

            setAppTitle("修改手机号");
            if (TextUtils.isEmpty(value)) {
                et_edit.setHint("请输入手机号");
            }else {
                et_edit.setText(value);
            }

        }else if(type.equals(Constant.IParam.mailbox)){
            //textEmailAddress
            et_edit.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            setAppTitle("修改邮箱");
            if (TextUtils.isEmpty(value)) {
                et_edit.setHint("请输入邮箱");
            }else {
                et_edit.setText(value);
            }


        }else {
            et_edit.setInputType(InputType.TYPE_CLASS_TEXT);
            setAppTitle("修改个性签名");
            if (TextUtils.isEmpty(value)) {
                et_edit.setHint("请输入您的个性签名 0至20字");
            }else {
                et_edit.setText(value);
            }

        }


    }
    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @OnClick(R.id.tv_edit_baocun)
    public void onClick() {
        value=getSStr(et_edit);

        if (type.equals(Constant.IParam.phone)) {
            if (TextUtils.isEmpty(value)) {
                showMsg("内容不能为空");
                return;
            }
            getEditPhone();
        }else if (type.equals(Constant.IParam.mailbox)){
            if (TextUtils.isEmpty(value)) {
                showMsg("内容不能为空");
                return;
            }
            getEditEmail();
        }else {
            if (value.length()>20) {
                showMsg("个性签名长度为0至20字");
                return;
            }
            getSetIndividualitySignature();




        }

    }

    private void getSetIndividualitySignature() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("jianjie",value+"");
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getSetIndividualitySignature(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefString(mContext, Config.individuality_signature,value);
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.value,value);
                setResult(RESULT_OK,intent);
                showMsg(obj.getMsg());
                finish();

            }
        });

    }

    private void getEditEmail() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("userid",getUserId());
        map.put("email",value);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getEditEmail(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefString(mContext, Config.email,value);
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.value,value);
                setResult(RESULT_OK,intent);
                showMsg(obj.getMsg());
                finish();

            }
        });

    }

    private void getEditPhone() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("userid",getUserId());
        map.put("phone",value);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getEditPhone(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefString(mContext, Config.mobile,value);

                Intent intent=new Intent();
                    intent.putExtra(Constant.IParam.value,value);
                    setResult(RESULT_OK,intent);
                showMsg(obj.getMsg());
                finish();


            }
        });

    }



}
