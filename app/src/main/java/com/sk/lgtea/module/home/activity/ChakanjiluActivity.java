package com.sk.lgtea.module.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.adapter.ChakanjiluAdapter;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.KejiantongjiXiangqingObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/12.
 */

public class ChakanjiluActivity extends BaseActivity {
    @BindView(R.id.rv_chakanjilu)
    RecyclerView rv_chakanjilu;
    LoadMoreAdapter adapter;
    String courseware_id;

    @Override
    protected int getContentView() {
        setAppTitle("被查看记录");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_chakanjindu;
    }

    @Override
    protected void initView() {
        getValue();
        adapter=new LoadMoreAdapter<KejiantongjiXiangqingObj>(mContext,R.layout.item_chakanjilu,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, KejiantongjiXiangqingObj bean) {
                TextView tv_item_chakanjilu_timedate = holder.getTextView(R.id.tv_item_chakanjilu_timedate);
                RecyclerView rv_item_chakanjilu = (RecyclerView) holder.getView(R.id.rv_item_chakanjilu);

                tv_item_chakanjilu_timedate.setText(bean.getAdd_time());
                ChakanjiluAdapter mAdapter=new ChakanjiluAdapter(mContext,R.layout.item_chakanjilu_list);
                mAdapter.setList(bean.getCourseware_record_list(),true);
                rv_item_chakanjilu.setLayoutManager(new LinearLayoutManager(mContext));
                rv_item_chakanjilu.setNestedScrollingEnabled(false);
                rv_item_chakanjilu.setAdapter(mAdapter);



            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_chakanjilu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_chakanjilu.setNestedScrollingEnabled(false);
        rv_chakanjilu.setAdapter(adapter);

    }

    private void getValue() {
        courseware_id=getIntent().getStringExtra(Constant.IParam.courseware_id);


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
        map.put("courseware_id",courseware_id);
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getCourseWareStatisticDetail(map, new MyCallBack<List<KejiantongjiXiangqingObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<KejiantongjiXiangqingObj> obj) {
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
