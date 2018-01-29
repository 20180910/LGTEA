package com.sk.lgtea.module.my.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.view.MyDialog;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.activity.KeChengDetailActivity;
import com.sk.lgtea.module.my.network.ApiRequest;
import com.sk.lgtea.module.my.network.response.CollectObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyCollectionActivity extends BaseActivity {
    @BindView(R.id.rv_my_collection)
    RecyclerView rv_my_collection;
    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的收藏");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_my_collection;
    }

    @Override
    protected void initView() {





        adapter = new LoadMoreAdapter<CollectObj.CourseWareBean>(mContext, R.layout.item_my_collection, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int i, CollectObj.CourseWareBean bean) {
                SwipeMenuLayout sml_collection = (SwipeMenuLayout) holder.getView(R.id.sml_collection);
                holder.getView(R.id.ll_my_collection_item).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        String video_pdf = bean.getVideo_pdf().substring(bean.getVideo_pdf().lastIndexOf(".")+1, bean.getVideo_pdf().length());
                        Log.d("=========", "video_pdf====" + video_pdf);
                        Log.d("=========", "====" + bean.getImage_url());
                        Intent intent = new Intent();
                        if (video_pdf.equals("mp4")||video_pdf.equals("MP4")) {
                            intent.putExtra(Constant.IParam.type, "0");
                            intent.putExtra(Constant.IParam.courseware_id, bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf, bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url, bean.getImage_url());
                            STActivity(intent, KeChengDetailActivity.class);
                            Log.d("=========", "==intent==" + bean.getImage_url());
                        } else if (video_pdf.equals("pdf")||video_pdf.equals("PDF")){
                            intent.putExtra(Constant.IParam.type, "1");
                            intent.putExtra(Constant.IParam.courseware_id, bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf, bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url, bean.getImage_url());
                            STActivity(intent, KeChengDetailActivity.class);
                        }else {
                            intent.putExtra(Constant.IParam.type, "2");
                            intent.putExtra(Constant.IParam.courseware_id, bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf, bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url, bean.getImage_url());
                            STActivity(intent, KeChengDetailActivity.class);

                        }
                    }
                });
                TextView tv_my_collection_delete = holder.getTextView(R.id.tv_my_collection_delete);
                tv_my_collection_delete.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                        mDialog.setMessage("确认删除吗?");
                        mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
//                                showMsg("删除咯");
                                getDelMyCollect(bean.getCourseware_id());
                            }
                        });
                        mDialog.create().show();
                    }
                });

                ImageView iv_item_my_collection_icon = holder.getImageView(R.id.iv_item_my_collection_icon);
                TextView tv_item_my_collection_name = holder.getTextView(R.id.tv_item_my_collection_name);
                TextView tv_item_my_collection_download = holder.getTextView(R.id.tv_item_my_collection_download);
                TextView tv_item_my_collection_zan = holder.getTextView(R.id.tv_item_my_collection_zan);
                TextView tv_item_my_collection_time = holder.getTextView(R.id.tv_item_my_collection_time);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_item_my_collection_icon);
                tv_item_my_collection_name.setText(bean.getTitle());
                tv_item_my_collection_zan.setText(bean.getCourseware_record_count());
                tv_item_my_collection_download.setText(bean.getSales());
                tv_item_my_collection_time.setText(bean.getAdd_time());


            }


        };

        adapter.setOnLoadMoreListener(this);
        rv_my_collection.setLayoutManager(new LinearLayoutManager(mContext));
        rv_my_collection.setAdapter(adapter);
    }

    private void getDelMyCollect(String courseware_id) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("courseware_id",courseware_id);
        map.put("user_id",getUserId());
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getDelMyCollect(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                adapter.notifyDataSetChanged();
                showLoading();
                getData(1, false);

            }
        });

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
        map.put("user_id", getUserId());
        map.put("pagesize", pageSize + "");
        map.put("page", page + "");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getMyCollection(map, new MyCallBack<CollectObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(CollectObj obj) {
                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getCourseWare(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getCourseWare(), true);
                }


            }
        });


    }

    @Override
    protected void onViewClick(View v) {

    }
}
