package com.sk.lgtea.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.rx.MySubscriber;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.adapter.YitijiaoAdapter;
import com.sk.lgtea.module.home.event.DownLoadSuccessEvent;
import com.sk.lgtea.module.home.event.WenjianEvent;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.YitijiaoObj;
import com.sk.lgtea.tools.download.entity.AppInfo;
import com.sk.lgtea.tools.download.util.DownloadUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;

import static com.github.baseclass.rx.RxBusHelper.getRxBusEvent;

/**
 * Created by Administrator on 2017/12/12.
 */

public class YitijiaoFragment extends BaseFragment {
    @BindView(R.id.rv_yitijiao)
    RecyclerView rv_yitijiao;
    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.frag_yitijiao;
    }

    public static YitijiaoFragment newInstance(String operationId ) {
        Bundle args = new Bundle();
        args.putString(Constant.operationId,operationId);
        YitijiaoFragment fragment = new YitijiaoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter<YitijiaoObj>(mContext,R.layout.item_yitijiao,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, YitijiaoObj bean) {
                TextView tv_item_yitijiao_date = holder.getTextView(R.id.tv_item_yitijiao_date);
                RecyclerView rv_item_yitijiao = (RecyclerView) holder.getView(R.id.rv_item_yitijiao);
                tv_item_yitijiao_date.setText(bean.getAdd_time());

                YitijiaoAdapter mAdapter=new YitijiaoAdapter(mContext,R.layout.item_yitijiao_list);
                mAdapter.setList(bean.getOperation_submit(),true);

                rv_item_yitijiao.setLayoutManager(new LinearLayoutManager(mContext));
                rv_item_yitijiao.setNestedScrollingEnabled(false);
                rv_item_yitijiao.setAdapter(mAdapter);
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_yitijiao.setLayoutManager(new LinearLayoutManager(mContext));
        rv_yitijiao.setNestedScrollingEnabled(false);
        rv_yitijiao.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        String operationId = getArguments().getString(Constant.operationId);
        Map<String,String> map=new HashMap<String,String>();
        map.put("operation_id",operationId);
        map.put("user_id",getUserId());
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getYitijiao(map, new MyCallBack<List<YitijiaoObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<YitijiaoObj> obj) {
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


        getRxBusEvent(WenjianEvent.class, new MySubscriber<WenjianEvent>() {
            @Override
            public void onMyNext(WenjianEvent event) {
                startDownload(event.id,event.name,event.attachment);




            }
        });


        getRxBusEvent(DownLoadSuccessEvent.class, new MySubscriber<DownLoadSuccessEvent>() {
            @Override
            public void onMyNext(DownLoadSuccessEvent event) {
                if (event.type.equals("success")) {
                    showMsg("下载已完成。请到个人中心->我的下载中查看。");

                }else if (event.type.equals("yes")){
                    showMsg("已下载。请到个人中心->我的下载中查看。");

                }else if (event.type.equals("no")){
                    showMsg("下载中...");

                }



            }
        });

    }
    private void startDownload(String id,String name,String attachment ) {
        if (TextUtils.isEmpty(attachment)) {
            showMsg("暂无下载");
            return;
        }


        AppInfo info = new AppInfo(id, name, name, "",attachment);
        showLoading();
        RXStart(new IOCallBack<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(DownloadUtils.isExistFile(info));
                subscriber.onCompleted();
            }

            @Override
            public void onMyNext(Boolean isExist) {
                dismissLoading();
                DownloadUtils.startDownload(isExist, mContext, info);
            }
        });
    }




    @Override
    protected void onViewClick(View v) {

    }
}
