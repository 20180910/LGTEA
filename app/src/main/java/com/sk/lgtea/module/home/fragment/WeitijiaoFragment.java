package com.sk.lgtea.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.customview.MyTextView;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.WeitijiaoObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/12.
 */

public class WeitijiaoFragment extends BaseFragment {
    @BindView(R.id.tv_weitijiao_tixing)
    MyTextView tv_weitijiao_tixing;
    @BindView(R.id.rv_weitijiao)
    RecyclerView rv_weitijiao;
    LoadMoreAdapter adapter;
    String type,user_id_to;

    @Override
    protected int getContentView() {
        return R.layout.frag_weitijiao;
    }

    public static WeitijiaoFragment newInstance(String operationId) {
        Bundle args = new Bundle();
        WeitijiaoFragment fragment = new WeitijiaoFragment();
        args.putString(Constant.operationId,operationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<WeitijiaoObj>(mContext, R.layout.item_weitijiao, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, WeitijiaoObj bean) {
                ImageView iv_item_weitijiao_icon = holder.getImageView(R.id.iv_item_weitijiao_icon);
                TextView tv_item_weitijiao_name = holder.getTextView(R.id.tv_item_weitijiao_name);
                TextView tv_item_weitijiao_tixing = holder.getTextView(R.id.tv_item_weitijiao_tixing);
                TextView tv_item_weitijiao_yitixing = holder.getTextView(R.id.tv_item_weitijiao_yitixing);
                Glide.with(mContext).load(bean.getHead_portrait()).error(R.drawable.my_people).into(iv_item_weitijiao_icon);
                tv_item_weitijiao_name.setText(bean.getName());
                if (bean.getIs_tixing().equals("0")) {
                    tv_item_weitijiao_tixing.setVisibility(View.VISIBLE);
                    tv_item_weitijiao_yitixing.setVisibility(View.GONE);
                }else {
                    tv_item_weitijiao_tixing.setVisibility(View.GONE);
                    tv_item_weitijiao_yitixing.setVisibility(View.VISIBLE);

                }
                tv_item_weitijiao_tixing.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {

                        getReminding("1",bean.getUser_id());



                    }
                });



            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_weitijiao.setNestedScrollingEnabled(false);
        rv_weitijiao.setLayoutManager(new LinearLayoutManager(mContext));
        rv_weitijiao.setAdapter(adapter);

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
        ApiRequest.getWeitijiao(map, new MyCallBack<List<WeitijiaoObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<WeitijiaoObj> list) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(list,true);
                }else{
                    pageNum=2;
                    if (list.size()==0) {
                        tv_weitijiao_tixing.setVisibility(View.GONE);
                    }else {
                        tv_weitijiao_tixing.setVisibility(View.VISIBLE);
                    }
                    adapter.setList(list,true);
                }


            }
        });


    }
    private void getReminding(String type, String user_id_to) {
        showLoading();
        String operationId = getArguments().getString(Constant.operationId);
        Map<String,String>map=new HashMap<String,String>();
        map.put("type",type);
        map.put("operation_id",operationId);
        map.put("user_id",getUserId());
        map.put("user_id_to",user_id_to);
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getReminding(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {

                showMsg(obj.getMsg());
                showLoading();
                getData(1,false);


            }
        });


    }

    @OnClick({R.id.tv_weitijiao_tixing})
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_weitijiao_tixing:
                getReminding("2",0+"");

            break;
        }
    }
}
