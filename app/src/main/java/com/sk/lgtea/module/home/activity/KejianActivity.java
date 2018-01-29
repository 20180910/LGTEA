package com.sk.lgtea.module.home.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.HistoryCourseWareListObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/11.
 */

public class KejianActivity extends BaseActivity {
    @BindView(R.id.rv_study_today)
    RecyclerView rv_study_today;

    @BindView(R.id.rv_study_history)
    RecyclerView rv_study_history;
    BaseRecyclerAdapter todayAdapter;

    LoadMoreAdapter historyAdapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的课件");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_kejian;
    }

    @Override
    protected void initView() {
        todayAdapter=new BaseRecyclerAdapter<HistoryCourseWareListObj.TodayCourseWareBean>(mContext, R.layout.item_study_today) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, HistoryCourseWareListObj.TodayCourseWareBean bean) {
                ImageView iv_item_study_tody_icon = holder.getImageView(R.id.iv_item_study_tody_icon);
                TextView tv_item_study_tody_title = holder.getTextView(R.id.tv_item_study_tody_title);
                TextView tv_item_study_tody_name = holder.getTextView(R.id.tv_item_study_tody_name);
                TextView tv_item_study_tody_time = holder.getTextView(R.id.tv_item_study_tody_time);



                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.width = (PhoneUtils.getScreenWidth(mContext)- PhoneUtils.dip2px(mContext,20))/2;
                layoutParams.height = (int) ( layoutParams.width*0.64);

                iv_item_study_tody_icon.setLayoutParams(layoutParams);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_item_study_tody_icon);
                tv_item_study_tody_title.setText(bean.getTitle());
                tv_item_study_tody_name.setText(bean.getKeynote_speaker());
                tv_item_study_tody_time.setText(bean.getAdd_time());

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        String video_pdf=bean.getVideo_pdf().substring(bean.getVideo_pdf().lastIndexOf(".")+1,bean.getVideo_pdf().length());
                        Log.d("=========","video_pdf===="+video_pdf);
                        Log.d("=========","===="+bean.getImage_url());
                        Intent intent=new Intent();
                        if (video_pdf.equals("mp4")||video_pdf.equals("MP4")) {
                            intent.putExtra(Constant.IParam.type,"0");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);
                            Log.d("=========","==intent=="+bean.getImage_url());
                        }else if (video_pdf.equals("pdf")||video_pdf.equals("PDF")){
                            intent.putExtra(Constant.IParam.type,"1");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);
                        }else {
                            intent.putExtra(Constant.IParam.type,"2");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);

                        }
                    }
                });

            }
        };


        rv_study_today.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_study_today.setNestedScrollingEnabled(false);
//        rv_study_today.addItemDecoration(new DividerGridItemDecoration(mContext,PhoneUtils.dip2px(mContext,12),R.color.white));
        rv_study_today.setAdapter(todayAdapter);


        historyAdapter=new LoadMoreAdapter<HistoryCourseWareListObj.HistoryCourseWareBean>(mContext, R.layout.item_study_history,pageSize,nsv) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, HistoryCourseWareListObj.HistoryCourseWareBean bean) {
                ImageView iv_item_study_history_icon = holder.getImageView(R.id.iv_item_study_history_icon);
                TextView tv_item_study_history_title = holder.getTextView(R.id.tv_item_study_history_title);
                TextView tv_item_study_history_downlad_num = holder.getTextView(R.id.tv_item_study_history_downlad_num);
                TextView tv_item_study_history_zan_num = holder.getTextView(R.id.tv_item_study_history_zan_num);
                TextView tv_item_study_history_look_num = holder.getTextView(R.id.tv_item_study_history_look_num);
                TextView tv_item_study_history_time = holder.getTextView(R.id.tv_item_study_history_time);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_item_study_history_icon);
                tv_item_study_history_title.setText(bean.getTitle());
                tv_item_study_history_downlad_num.setText(bean.getSales());
                tv_item_study_history_zan_num.setText(bean.getThumbup_count());
                tv_item_study_history_look_num.setText(bean.getCourseware_record_count());
                tv_item_study_history_time.setText(bean.getAdd_time());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        String video_pdf=bean.getVideo_pdf().substring(bean.getVideo_pdf().lastIndexOf(".")+1,bean.getVideo_pdf().length());
                        Log.d("=========","video_pdf===="+video_pdf);
                        Intent intent=new Intent();
                        if (video_pdf.equals("mp4")) {
                            intent.putExtra(Constant.IParam.type,"0");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);
                        }else if (video_pdf.equals("pdf")){
                            intent.putExtra(Constant.IParam.type,"1");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);
                        }else {
                            intent.putExtra(Constant.IParam.type,"2");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);

                        }

                    }
                });


            }
        };
        historyAdapter.setOnLoadMoreListener(this);

        rv_study_history.setLayoutManager(new LinearLayoutManager(mContext));
        rv_study_history.setNestedScrollingEnabled(false);
        rv_study_history.addItemDecoration(getItemDivider());
        rv_study_history.setAdapter(historyAdapter);



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
        ApiRequest.getHistorysCourseWareList(map, new MyCallBack<HistoryCourseWareListObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(HistoryCourseWareListObj obj) {
                todayAdapter.setList(obj.getTodayCourseWare(),true);
                if(isLoad){
                    pageNum++;
                    historyAdapter.addList(obj.getHistoryCourseWare(),true);
                }else{
                    pageNum=2;
                    historyAdapter.setList(obj.getHistoryCourseWare(),true);
                }



            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
