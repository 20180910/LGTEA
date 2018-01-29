package com.sk.lgtea.module.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.rx.MySubscriber;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.activity.ChakanjiluActivity;
import com.sk.lgtea.module.home.adapter.KejiantongjiAdapter;
import com.sk.lgtea.module.home.event.PreviewEvent;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.KejiantongjiObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.github.baseclass.rx.RxBusHelper.getRxBusEvent;

/**
 * Created by Administrator on 2017/12/12.
 */

public class KejiantongjiFragment extends BaseFragment {
    @BindView(R.id.rv_kejiantongji)
    RecyclerView rv_kejiantongji;
    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.frag_kejiantongji;
    }
    public static KejiantongjiFragment newInstance() {
        Bundle args = new Bundle();
        KejiantongjiFragment fragment = new KejiantongjiFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<KejiantongjiObj>(mContext, R.layout.item_kejiantongji_date, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, KejiantongjiObj bean) {
                TextView tv_item_kejiantongji_date = holder.getTextView(R.id.tv_item_kejiantongji_date);
                RecyclerView rv_item_kejiantongji = (RecyclerView) holder.getView(R.id.rv_item_kejiantongji);
                tv_item_kejiantongji_date.setText(bean.getAdd_time());
                KejiantongjiAdapter mAdapter = new KejiantongjiAdapter(mContext,R.layout.item_kejiantongji);
                mAdapter.setList(bean.getCourseware_list(),true);
                rv_item_kejiantongji.setLayoutManager(new LinearLayoutManager(mContext));
                rv_item_kejiantongji.setNestedScrollingEnabled(false);
                rv_item_kejiantongji.setAdapter(mAdapter);
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_kejiantongji.setLayoutManager(new LinearLayoutManager(mContext));
        rv_kejiantongji.setNestedScrollingEnabled(false);
        rv_kejiantongji.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(PreviewEvent.class, new MySubscriber<PreviewEvent>() {
            @Override
            public void onMyNext(PreviewEvent event) {
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.courseware_id,event.id);
                STActivity(intent,ChakanjiluActivity.class);
            }
        });
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getCourseWareStatisticList(map, new MyCallBack<List<KejiantongjiObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<KejiantongjiObj> obj) {
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
    protected void onViewClick(View v) {
    }
}
