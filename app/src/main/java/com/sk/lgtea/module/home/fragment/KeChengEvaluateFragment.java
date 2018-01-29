package com.sk.lgtea.module.home.fragment;

import android.content.Intent;
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
import com.github.baseclass.rx.RxBus;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.activity.KechengMoreReplyActivity;
import com.sk.lgtea.module.home.adapter.KechengEvaluateCommentAdapter;
import com.sk.lgtea.module.home.event.ScrollViewEvent;
import com.sk.lgtea.module.home.event.StudyKejianEvent;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.StudyDetailObj;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/12/5.
 */

public class KeChengEvaluateFragment extends BaseFragment {
    @BindView(R.id.et_kecheng_evaluate_content)
    MyEditText et_kecheng_evaluate_content;
    @BindView(R.id.tv_kecheng_evaluate_pinglun)
    MyTextView tv_kecheng_evaluate_pinglun;
    @BindView(R.id.rv_kecheng_evaluate)
    RecyclerView rv_kecheng_evaluate;
    @BindView(R.id.tv_kecheng_evaluate_pinglun_num)
    TextView tv_kecheng_evaluate_pinglun_num;
    LoadMoreAdapter adapter;
    String courseware_id;
    String content="",id;
    int  type=1;

    @Override
    protected int getContentView() {
        return R.layout.frag_kecheng_evaluate;
    }

    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<StudyDetailObj.CommentListBean>(mContext, R.layout.item_kecheng_evaluate, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, StudyDetailObj.CommentListBean bean) {
                ImageView iv_item_kecheng_evaluate_icon = holder.getImageView(R.id.iv_item_kecheng_evaluate_icon);
                TextView tv_item_kecheng_evaluate_name = holder.getTextView(R.id.tv_item_kecheng_evaluate_name);
                TextView tv_item_kecheng_evaluate_time = holder.getTextView(R.id.tv_item_kecheng_evaluate_time);
                TextView tv_item_kecheng_evaluate_content = holder.getTextView(R.id.tv_item_kecheng_evaluate_content);
                TextView tv_item_kecheng_evaluate_reply = holder.getTextView(R.id.tv_item_kecheng_evaluate_reply);
                View v_item_kecheng_evaluate_reply_line = holder.getView(R.id.v_item_kecheng_evaluate_reply_line);
                RecyclerView rv_item_kecheng_evaluate_reply = (RecyclerView) holder.getView(R.id.rv_item_kecheng_evaluate_reply);

                Glide.with(mContext).load(bean.getPhoto()).error(R.drawable.my_people).into(iv_item_kecheng_evaluate_icon);
                tv_item_kecheng_evaluate_name.setText(bean.getName());
                tv_item_kecheng_evaluate_time.setText(com.github.androidtools.DateUtils.dateToString(new Date(bean.getComment_time()*1000 ),"yyyy-MM-dd"));
                tv_item_kecheng_evaluate_content.setText(bean.getContent());
                if (bean.getReply_list().size() == 0) {
                    rv_item_kecheng_evaluate_reply.setVisibility(View.GONE);
                } else {
                    rv_item_kecheng_evaluate_reply.setVisibility(View.VISIBLE);
                }
                if (bean.getReply_list().size() >= 2) {
                    tv_item_kecheng_evaluate_reply.setVisibility(View.VISIBLE);
                } else {
                    tv_item_kecheng_evaluate_reply.setVisibility(View.GONE);
                }
                tv_item_kecheng_evaluate_content.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        et_kecheng_evaluate_content.requestFocus();
                        et_kecheng_evaluate_content.setHint("请评论"+bean.getName());
                        type=2;
                        courseware_id=bean.getComments_id();
                        RxBus.getInstance().post(new ScrollViewEvent());



                    }
                });
//                bean.getReply_list()

                KechengEvaluateCommentAdapter mAdapter = new KechengEvaluateCommentAdapter(mContext, R.layout.item_kecheng_evaluate_comment);
                mAdapter.setList(bean.getReply_list(), true);
                rv_item_kecheng_evaluate_reply.setLayoutManager(new LinearLayoutManager(mContext));
                rv_item_kecheng_evaluate_reply.setNestedScrollingEnabled(false);
                rv_item_kecheng_evaluate_reply.setAdapter(mAdapter);
                tv_item_kecheng_evaluate_reply.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IParam.comments_id, bean.getComments_id());
                        STActivity(intent, KechengMoreReplyActivity.class);


                    }
                });



                //

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_kecheng_evaluate.setLayoutManager(new LinearLayoutManager(mContext));
        rv_kecheng_evaluate.setNestedScrollingEnabled(false);
        rv_kecheng_evaluate.setAdapter(adapter);

    }

    public static KeChengEvaluateFragment newInstance() {

        Bundle args = new Bundle();

        KeChengEvaluateFragment fragment = new KeChengEvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_kecheng_evaluate_pinglun})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_kecheng_evaluate_pinglun:

                content=getSStr(et_kecheng_evaluate_content);
                if (content.equals("")) {
                    showMsg("内容不能为空");
                    return;
                }

                getAddCommentCourseWare();

                break;
        }
    }

    private void getAddCommentCourseWare() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("forum_comment_id",courseware_id);
        map.put("user_id",getUserId());
        map.put("type",type+"");
        map.put("content",content);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getAddCommentCourseWare(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                et_kecheng_evaluate_content.setText("");
                et_kecheng_evaluate_content.setHint("请输入评论");
                courseware_id=id;
                type=1;
                RxBus.getInstance().post(new StudyKejianEvent(Config.comment));


            }
        });


    }

    public void setData(List<StudyDetailObj.CommentListBean> obj) {
        adapter.setList(obj, true);
    }

    public void addData(List<StudyDetailObj.CommentListBean> obj) {
        adapter.addList(obj, true);
    }

    public void setComment_count(String comment_count) {
        tv_kecheng_evaluate_pinglun_num.setText("学生评论("+comment_count+")");

    }

    public void setCourseware_id(String coursewareId) {
        id=coursewareId;
        courseware_id=coursewareId;

    }
}
