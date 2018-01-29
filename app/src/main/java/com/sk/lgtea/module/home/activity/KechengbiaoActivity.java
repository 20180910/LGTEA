package com.sk.lgtea.module.home.activity;

import android.support.v7.widget.GridLayoutManager;
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
import com.sk.lgtea.module.home.network.response.KechengbiaoObj;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/12/8.
 */

public class KechengbiaoActivity extends BaseActivity {
    @BindView(R.id.rv_kechengbiao)
    RecyclerView rv_kechengbiao;
    @BindView(R.id.rv_xingqiyi)
    RecyclerView rv_xingqiyi;
    @BindView(R.id.rv_xingqier)
    RecyclerView rv_xingqier;
    @BindView(R.id.rv_xingqisan)
    RecyclerView rv_xingqisan;
    @BindView(R.id.rv_xingqisi)
    RecyclerView rv_xingqisi;
    @BindView(R.id.rv_xingqiwu)
    RecyclerView rv_xingqiwu;
    @BindView(R.id.rv_xingqiliu)
    RecyclerView rv_xingqiliu;
    @BindView(R.id.rv_xingqitian)
    RecyclerView rv_xingqitian;
    BaseRecyclerAdapter dateAdapter,oneAdapter,twoAdapter,threeAdapter,fourAdapter,fiveAdapter,sixAdapter,sevenAdapter;

    @Override
    protected int getContentView() {
        setBackIcon(R.drawable.back_white);
        return R.layout.act_kechengbiao;
    }

    @Override
    protected void initView() {
        date();
        String[] xingqi = {"周一", "周二", "周三", "周四", "周五", "周六", "周天"};
        dateAdapter = new BaseRecyclerAdapter(mContext, R.layout.item_kechengbiao_date) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, Object o) {
                TextView tv_item_kechengbiao_xingqi = holder.getTextView(R.id.tv_item_kechengbiao_xingqi);
                tv_item_kechengbiao_xingqi.setText(xingqi[i]);
            }
        };
        rv_kechengbiao.setLayoutManager(new GridLayoutManager(mContext, 7));
        rv_kechengbiao.setNestedScrollingEnabled(false);
        dateAdapter.setTestListSize(xingqi.length);
        rv_kechengbiao.setAdapter(dateAdapter);

        oneAdapter = new BaseRecyclerAdapter<KechengbiaoObj.TimeHourBean>(mContext, R.layout.item_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KechengbiaoObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                tv_item_kecheng.setText(bean.getContent());
                if (bean.getCourse_id()==0) {

                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));


                }else {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.hongse));

                }





            }
        };
        rv_xingqiyi.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqiyi.setNestedScrollingEnabled(false);
        rv_xingqiyi.setAdapter(oneAdapter);


        twoAdapter = new BaseRecyclerAdapter <KechengbiaoObj.TimeHourBean>(mContext, R.layout.item_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KechengbiaoObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                tv_item_kecheng.setText(bean.getContent());
                if (bean.getCourse_id()==0) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.chengse));
                }


            }
        };
        rv_xingqier.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqier.setNestedScrollingEnabled(false);
        rv_xingqier.setAdapter(twoAdapter);

        threeAdapter = new BaseRecyclerAdapter<KechengbiaoObj.TimeHourBean>(mContext, R.layout.item_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KechengbiaoObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                tv_item_kecheng.setText(bean.getContent());
                if (bean.getCourse_id()==0) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.huangse));
                }


            }
        };
        rv_xingqisan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqisan.setNestedScrollingEnabled(false);
        rv_xingqisan.setAdapter(threeAdapter);

        fourAdapter = new BaseRecyclerAdapter<KechengbiaoObj.TimeHourBean>(mContext, R.layout.item_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KechengbiaoObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                tv_item_kecheng.setText(bean.getContent());
                if (bean.getCourse_id()==0) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.lvse));
                }



            }
        };
        rv_xingqisi.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqisi.setNestedScrollingEnabled(false);
        rv_xingqisi.setAdapter(fourAdapter);

        fiveAdapter = new BaseRecyclerAdapter<KechengbiaoObj.TimeHourBean>(mContext, R.layout.item_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KechengbiaoObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                tv_item_kecheng.setText(bean.getContent());
                if (bean.getCourse_id()==0) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.qingse));
                }


            }
        };
        rv_xingqiwu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqiwu.setNestedScrollingEnabled(false);
        rv_xingqiwu.setAdapter(fiveAdapter);

        sixAdapter = new BaseRecyclerAdapter<KechengbiaoObj.TimeHourBean>(mContext, R.layout.item_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KechengbiaoObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                tv_item_kecheng.setText(bean.getContent());
                if (bean.getCourse_id()==0) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.lanse));
                }


            }
        };
        rv_xingqiliu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqiliu.setNestedScrollingEnabled(false);
        rv_xingqiliu.setAdapter(sixAdapter);

        sevenAdapter = new BaseRecyclerAdapter<KechengbiaoObj.TimeHourBean>(mContext, R.layout.item_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KechengbiaoObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                tv_item_kecheng.setText(bean.getContent());
                if (bean.getCourse_id()==0) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.zise));
                }


            }
        };
        rv_xingqitian.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqitian.setNestedScrollingEnabled(false);
        rv_xingqitian.setAdapter(sevenAdapter);


    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);

    }
    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", 2 + "");
        map.put("user_id", getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getCurriculumSchedule(map, new MyCallBack<List<KechengbiaoObj>>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(List<KechengbiaoObj> obj) {
                if (obj.size() == 7) {
                    oneAdapter.setList(obj.get(0).getTime_hour(), true);
                    twoAdapter.setList(obj.get(1).getTime_hour(), true);
                    threeAdapter.setList(obj.get(2).getTime_hour(), true);
                    fourAdapter.setList(obj.get(3).getTime_hour(), true);
                    fiveAdapter.setList(obj.get(4).getTime_hour(), true);
                    sixAdapter.setList(obj.get(5).getTime_hour(), true);
                    sevenAdapter.setList(obj.get(6).getTime_hour(), true);

                }


            }
        });


    }

    public void date() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int week_day_month = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        setAppTitle(month + "月第" + week_day_month + "周");


    }


    @Override
    protected void onViewClick(View v) {

    }
}
