package com.sk.lgtea.module.my.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.github.customview.MyImageView;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.activity.AboutUsActivity;
import com.sk.lgtea.module.my.activity.MyCollectionActivity;
import com.sk.lgtea.module.my.activity.MyDataActivity;
import com.sk.lgtea.module.my.activity.MyUploadActivity;
import com.sk.lgtea.module.my.activity.SettingActivity;
import com.sk.lgtea.module.my.activity.YijianfankuiActivity;
import com.sk.lgtea.module.my.network.ApiRequest;
import com.sk.lgtea.module.my.network.response.UserInfoObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_my_icon)
    MyImageView iv_my_icon;
    @BindView(R.id.tv_my_collection)
    TextView tv_my_collection;
    @BindView(R.id.tv_my_yijianfankui)
    TextView tv_my_yijianfankui;
    @BindView(R.id.tv_my_about_us)
    TextView tv_my_about_us;
    @BindView(R.id.tv_my_setting)
    TextView tv_my_setting;
    @BindView(R.id.tv_my_name)
    TextView tv_my_name;
    @BindView(R.id.tv_my_gonghao)
    TextView tv_my_gonghao;
    @BindView(R.id.tv_my_download)
    TextView tv_my_download;
    @BindView(R.id.ll_my_data)
    LinearLayout ll_my_data;
    //76

    @Override
    protected int getContentView() {

        return R.layout.frag_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress();
        getUserInfo();


    }

    private void getUserInfo() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getUserInfo(map, new MyCallBack<UserInfoObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(UserInfoObj obj) {

                    Glide.with(mContext).load(obj.getAvatar()).error(R.drawable.my_people).into(iv_my_icon);
                tv_my_name.setText(obj.getName());
                tv_my_gonghao.setText("工号：" + obj.getUser_name());
                SPUtils.setPrefString(mContext, Config.avatar,obj.getAvatar());
                SPUtils.setPrefString(mContext, Config.individuality_signature,obj.getIndividuality_signature());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(mContext).load(SPUtils.getPrefString(mContext, Config.avatar,"")).error(R.drawable.my_people).into(iv_my_icon);

    }

    @OnClick({R.id.iv_my_icon,
            R.id.tv_my_collection,
            R.id.tv_my_download,
            R.id.tv_my_yijianfankui,
            R.id.tv_my_about_us,
            R.id.tv_my_setting,
            R.id.ll_my_data})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_my_icon:

                break;
            case R.id.tv_my_collection:
                STActivity(MyCollectionActivity.class);
                break;
            case R.id.tv_my_download:
                STActivity(MyUploadActivity.class);
                break;
            case R.id.tv_my_yijianfankui:
                STActivity(YijianfankuiActivity.class);
                break;
            case R.id.tv_my_about_us:
                STActivity(AboutUsActivity.class);
                break;
            case R.id.tv_my_setting:
                STActivity(SettingActivity.class);
                break;
            case R.id.ll_my_data:
                STActivity(MyDataActivity.class);
                break;
        }
    }


}
