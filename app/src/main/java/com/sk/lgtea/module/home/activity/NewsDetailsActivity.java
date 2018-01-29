package com.sk.lgtea.module.home.activity;

import android.view.View;
import android.widget.TextView;

import com.github.baseclass.rx.RxBus;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.event.NewsEvent;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.NewsDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/8.
 */

public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_news_details_title)
    TextView tv_news_details_title;
    @BindView(R.id.tv_news_details_time)
    TextView tv_news_details_time;
    @BindView(R.id.tv_news_details_content)
    TextView tv_news_details_content;
    String news_id,is_check;
    @Override
    protected int getContentView() {
        setAppTitle("详细消息");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_news_details;
    }

    @Override
    protected void initView() {

        getValue();

    }

    private void getValue() {
        news_id=getIntent().getStringExtra(Constant.IParam.news_id);
        is_check=getIntent().getStringExtra(Constant.IParam.is_check);

    }

    @Override
    protected void initData() {
        showProgress();
        getNewsDetail();


    }

    private void getNewsDetail() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("news_id",news_id);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getNewsDetail(map, new MyCallBack<NewsDetailObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(NewsDetailObj obj) {
                tv_news_details_title.setText(obj.getTitle());
                tv_news_details_content.setText(obj.getContent());
                tv_news_details_time.setText(obj.getAdd_time());
                if (is_check.equals("1")) {
                    RxBus.getInstance().post(new NewsEvent(is_check));

                }


            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}