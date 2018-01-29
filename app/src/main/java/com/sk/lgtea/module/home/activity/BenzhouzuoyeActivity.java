package com.sk.lgtea.module.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.BenzhouzuoyeObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/13.
 */

public class BenzhouzuoyeActivity extends BaseActivity {
    @BindView(R.id.rv_benzhouzuoye)
    RecyclerView rv_benzhouzuoye;
    BaseRecyclerAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("本周作业详情");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_benzhouzuoye;
    }

    @Override
    protected void initView() {

        adapter=new BaseRecyclerAdapter<BenzhouzuoyeObj>(mContext,R.layout.item_benzhouzuoye) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, BenzhouzuoyeObj obj) {
                TextView tv_item_time = holder.getTextView(R.id.tv_item_time);
                TextView tv_item_yijiao_num = holder.getTextView(R.id.tv_item_yijiao_num);
                TextView tv_item_weijiao_num = holder.getTextView(R.id.tv_item_weijiao_num);
                TextView tv_item_jiaozuoyelv = holder.getTextView(R.id.tv_item_jiaozuoyelv);
                tv_item_time.setText(obj.getTime());
                tv_item_yijiao_num.setText(obj.getYijiao());
                tv_item_weijiao_num.setText(obj.getWeijiao());
                tv_item_jiaozuoyelv.setText(obj.getTijiaolv()+"%");
            }
        };
        rv_benzhouzuoye.setLayoutManager(new LinearLayoutManager(mContext));
        rv_benzhouzuoye.setNestedScrollingEnabled(false);
        rv_benzhouzuoye.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getWeekOperationDetail();


    }

    private void getWeekOperationDetail() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getWeekOperationDetail(map, new MyCallBack<List<BenzhouzuoyeObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<BenzhouzuoyeObj> obj) {
                adapter.setList(obj,true);
            }
        });


    }


    @Override
    protected void onViewClick(View v) {

    }
}
