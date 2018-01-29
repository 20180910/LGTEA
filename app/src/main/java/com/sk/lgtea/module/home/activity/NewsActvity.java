package com.sk.lgtea.module.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.rx.MySubscriber;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.event.NewsEvent;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.NewListObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/8.
 */

public class NewsActvity extends BaseActivity {
    @BindView(R.id.rv_news)
    RecyclerView rv_news;
    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("消息通知");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_news;
    }

    @Override
    protected void initView() {
        String[] title={"本周三下午会议通知","作业提交提醒"};
        adapter=new LoadMoreAdapter<NewListObj>(mContext,R.layout.item_news,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int i, NewListObj obj) {
                ImageView iv_item_news_icon = holder.getImageView(R.id.iv_item_news_icon);
                TextView tv_item_news_type = holder.getTextView(R.id.tv_item_news_type);
                TextView tv_item_news_title = holder.getTextView(R.id.tv_item_news_title);
                TextView tv_item_news_time = holder.getTextView(R.id.tv_item_news_time);
                TextView tv_item_news_content = holder.getTextView(R.id.tv_item_news_content);

                if (obj.getIs_check().equals("1")) {
                    tv_item_news_type.setVisibility(View.VISIBLE);
                }else {
                    tv_item_news_type.setVisibility(View.INVISIBLE);

                }
                Glide.with(mContext).load(obj.getImage()).error(R.color.c_press).into(iv_item_news_icon);
                tv_item_news_title.setText(obj.getTitle());
                tv_item_news_time.setText(obj.getAdd_time());
                tv_item_news_content.setText(obj.getContent());



                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.news_id,obj.getId());
                        intent.putExtra(Constant.IParam.is_check,obj.getIs_check());
                        STActivity(intent,NewsDetailsActivity.class);
                    }
                });

            }


        };
        adapter.setOnLoadMoreListener(this);
        rv_news.setLayoutManager(new LinearLayoutManager(mContext));
        rv_news.setNestedScrollingEnabled(false);
        rv_news.addItemDecoration(getItemDivider());
        rv_news.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getNewList(map, new MyCallBack<List<NewListObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<NewListObj> obj) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj,true);
                }else{
                    pageNum=2;
                    adapter.setList(obj,true);
                }

            }
        });

    }
    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(NewsEvent.class, new MySubscriber<NewsEvent>() {
            @Override
            public void onMyNext(NewsEvent event) {
                if (event.is_check.equals("1")) {
                    showLoading();
                    getData(1,false);

                }


            }
        });
    }

    @Override
    protected void onViewClick(View v) {

    }
}
