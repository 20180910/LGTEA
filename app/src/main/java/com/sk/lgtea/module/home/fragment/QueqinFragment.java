package com.sk.lgtea.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.rx.RxBus;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.event.QueqinEvent;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.KaoqinjilvObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/12.
 */

public class QueqinFragment extends BaseFragment {
    @BindView(R.id.rv_kaoqinjilv)
    RecyclerView rv_kaoqinjilv;
    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.frag_kaoqinjilv;
    }
    public static QueqinFragment newInstance(String Id, String Time) {
        Bundle args = new Bundle();
        QueqinFragment fragment = new QueqinFragment();
        args.putString(Constant.IParam.course_id,Id);
        args.putString(Constant.IParam.current_time,Time);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<KaoqinjilvObj.List1Bean>(mContext, R.layout.item_kaoqinjilv, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, KaoqinjilvObj.List1Bean bean) {
                TextView tv_item_kaoqinjilv_xuhao = holder.getTextView(R.id.tv_item_kaoqinjilv_xuhao);
                TextView tv_item_kaoqinjilv_name = holder.getTextView(R.id.tv_item_kaoqinjilv_name);
                TextView tv_item_kaoqinjilv_time = holder.getTextView(R.id.tv_item_kaoqinjilv_time);
                ImageView iv_item_kaoqinjilv_icon = holder.getImageView(R.id.iv_item_kaoqinjilv_icon);
                int indext=position+1;
                tv_item_kaoqinjilv_xuhao.setText(indext+".");
                tv_item_kaoqinjilv_name.setText(bean.getName());
                tv_item_kaoqinjilv_time.setText(bean.getAdd_time());
                Glide.with(mContext).load(bean.getHead_portrait()).error(R.drawable.my_people).into(iv_item_kaoqinjilv_icon);

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_kaoqinjilv.setLayoutManager(new LinearLayoutManager(mContext));
        rv_kaoqinjilv.setNestedScrollingEnabled(false);
        rv_kaoqinjilv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }



    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        String course_id = getArguments().getString(Constant.IParam.course_id);
        String current_time = getArguments().getString(Constant.IParam.current_time);
        Map<String,String> map=new HashMap<String,String>();
        map.put("attendance_type",2+"");
        map.put("user_id",getUserId());
        map.put("course_id",course_id);
        map.put("current_time",current_time);
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getAttendanceStatisticDetail(map, new MyCallBack<KaoqinjilvObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(KaoqinjilvObj obj) {
                RxBus.getInstance().post(new QueqinEvent(obj.getTotal_number()));
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj.getList1(),true);
                }else{
                    pageNum=2;
                    adapter.setList(obj.getList1(),true);
                }

            }
        });

    }
    @Override
    protected void onViewClick(View v) {
    }
}
