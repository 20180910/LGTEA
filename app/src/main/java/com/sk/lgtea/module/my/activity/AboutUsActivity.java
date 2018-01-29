package com.sk.lgtea.module.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.network.ApiRequest;
import com.sk.lgtea.module.my.network.response.AboutPlatformObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.tv_about_us_share)
    TextView tv_about_us_share;
    @BindView(R.id.tv_about_us_wangzhan)
    TextView tv_about_us_wangzhan;
    @BindView(R.id.tv_about_us_banquan)
    TextView tv_about_us_banquan;
    @BindView(R.id.iv_about_us_icon)
    ImageView iv_about_us_icon;
    String logo,official_website,banquansuoyou;

    @Override
    protected int getContentView() {
        setAppTitle("关于我们");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_about_us;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);


    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        getAboutPlatform();

    }

    private void getAboutPlatform() {
//        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getAboutPlatform(map, new MyCallBack<AboutPlatformObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(AboutPlatformObj obj) {
                logo=obj.getLogo();
                official_website=obj.getOfficial_website();
                banquansuoyou=obj.getBanquansuoyou();
                Glide.with(mContext).load(logo).error(R.color.c_press).into(iv_about_us_icon);
                tv_about_us_banquan.setText(banquansuoyou);

            }
        });
//        pl_load.showContent();


    }


    @OnClick({R.id.tv_about_us_share, R.id.tv_about_us_wangzhan})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_about_us_share:
                showFenXiang();

                break;
            case R.id.tv_about_us_wangzhan:
                Intent intent=new Intent();//创建Intent对象
                intent.setAction(Intent.ACTION_VIEW);//为Intent设置动作
                intent.setData(Uri.parse(official_website));//为Intent设置数据
                startActivity(intent);//将Intent传递给Activity
                break;
        }
    }
}
