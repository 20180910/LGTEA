package com.sk.lgtea.module.home.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.DateUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.customview.MyImageView;
import com.github.customview.MyTextView;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.taolun.network.ApiRequest;
import com.sk.lgtea.module.taolun.network.response.MoreReplyObj;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/6.
 */

public class KechengMoreReplyActivity extends BaseActivity {
    @BindView(R.id.tv_kecheng_more_reply_name)
    TextView tv_kecheng_more_reply_name;
    @BindView(R.id.tv_kecheng_more_reply_time)
    TextView tv_kecheng_more_reply_time;
    @BindView(R.id.tv_kecheng_more_reply_comment)
    TextView tv_kecheng_more_reply_comment;
    @BindView(R.id.rv_kecheng_more_reply)
    RecyclerView rv_kecheng_more_reply;
    @BindView(R.id.et_kecheng_more_reply_discuss)
    EditText et_kecheng_more_reply_discuss;
    @BindView(R.id.tv_kecheng_more_reply_pinglun)
    MyTextView tv_kecheng_more_reply_pinglun;
    @BindView(R.id.iv_kecheng_more_reply)
    MyImageView iv_kecheng_more_reply;


    LoadMoreAdapter adapter;
    String comments_id, name = "", content, discussion_forum_id, reply_id;

    @Override
    protected int getContentView() {
        setAppTitle("全部回复");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_kecheng_more_reply;
    }

    @Override
    protected void initView() {
        getValue();
        adapter = new LoadMoreAdapter<MoreReplyObj.ReplyListBean>(mContext, R.layout.item_kecheng_more_reply, pageSize, nsv) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int i, MoreReplyObj.ReplyListBean bean) {
                TextView tv_item_kecheng_more_reply_name = holder.getTextView(R.id.tv_item_kecheng_more_reply_name);
                TextView tv_item_kecheng_more_reply_toname = holder.getTextView(R.id.tv_item_kecheng_more_reply_toname);
                TextView tv_item_kecheng_more_reply_huifu = holder.getTextView(R.id.tv_item_kecheng_more_reply_huifu);
                TextView tv_item_kecheng_more_reply_comment = holder.getTextView(R.id.tv_item_kecheng_more_reply_comment);
                tv_item_kecheng_more_reply_name.setText(bean.getName());
                tv_item_kecheng_more_reply_comment.setText(bean.getContent());
                if (bean.getCode().equals("commen")) {
                    tv_item_kecheng_more_reply_toname.setVisibility(View.GONE);
                    tv_item_kecheng_more_reply_huifu.setVisibility(View.GONE);
                } else {
                    tv_item_kecheng_more_reply_toname.setText(bean.getName_to());
                    tv_item_kecheng_more_reply_toname.setVisibility(View.VISIBLE);
                    tv_item_kecheng_more_reply_huifu.setVisibility(View.VISIBLE);
                }
                tv_item_kecheng_more_reply_comment.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        reply_id = bean.getReply_id();
                        //弹出软键盘
                        InputMethodManager mInput = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        mInput.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                        et_kecheng_more_reply_discuss.requestFocus();//获取焦点
                        et_kecheng_more_reply_discuss.setHint("请回复" + bean.getName());
                        tv_kecheng_more_reply_pinglun.setText("回复");

                    }
                });

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_kecheng_more_reply.setLayoutManager(new LinearLayoutManager(mContext));
        rv_kecheng_more_reply.setNestedScrollingEnabled(false);
        rv_kecheng_more_reply.setAdapter(adapter);

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
        map.put("comment_id", comments_id);
        map.put("user_id", getUserId());
        map.put("pagesize", pageSize + "");
        map.put("page", page + "");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getCommentDetail(map, new MyCallBack<MoreReplyObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(MoreReplyObj obj) {
                tv_kecheng_more_reply_name.setText(obj.getName());
                name = obj.getName();
                Glide.with(mContext).load(obj.getPhoto()).error(R.drawable.my_people).into(iv_kecheng_more_reply);

                et_kecheng_more_reply_discuss.setHint("请输入你对" + name + "的评论");
                discussion_forum_id = obj.getComments_id();
                tv_kecheng_more_reply_comment.setText(obj.getContent());
                tv_kecheng_more_reply_time.setText(DateUtils.dateToString(new Date(obj.getComment_time() * 1000), "yyyy-MM-dd HH:mm"));
                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getReply_list(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getReply_list(), true);
                }


            }
        });


    }

    private void getValue() {
        comments_id = getIntent().getStringExtra(Constant.IParam.comments_id);

    }


    @OnClick({R.id.tv_kecheng_more_reply_pinglun, R.id.tv_kecheng_more_reply_comment})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_kecheng_more_reply_pinglun:
                if (tv_kecheng_more_reply_pinglun.getText().toString().equals("评论")) {
                    getAddCommentCourseWare();
                } else {
                    getAddReply();
                }


                break;
            case R.id.tv_kecheng_more_reply_comment:
                //弹出软键盘
                InputMethodManager mInput = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mInput.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                et_kecheng_more_reply_discuss.requestFocus();//获取焦点
                et_kecheng_more_reply_discuss.setHint("请输入你对" + name + "的评论");
                tv_kecheng_more_reply_pinglun.setText("评论");

                break;
        }
    }

    private void getAddCommentCourseWare() {
        content = getSStr(et_kecheng_more_reply_discuss);
        if (TextUtils.isEmpty(content)) {
            showMsg("评论不能为空！");
            return;
        }
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("forum_comment_id", discussion_forum_id);
        map.put("user_id", getUserId());
        map.put("type", 2 + "");
        map.put("content", content);
        map.put("sign", GetSign.getSign(map));
        com.sk.lgtea.module.home.network.ApiRequest.getAddCommentCourseWare(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showLoading();
                getData(1, false);
                showMsg(obj.getMsg());
                et_kecheng_more_reply_discuss.setText("");
                et_kecheng_more_reply_discuss.setHint("请输入你对" + name + "的评论");
                tv_kecheng_more_reply_pinglun.setText("评论");
            }
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏软键盘 //
        imm.hideSoftInputFromWindow(et_kecheng_more_reply_discuss.getWindowToken(), 0);


    }

    private void getAddReply() {
        content = getSStr(et_kecheng_more_reply_discuss);
        if (TextUtils.isEmpty(content)) {
            showMsg("内容不能为空！");
            return;
        }
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("comment_id", comments_id);
        map.put("reply_id", reply_id);
        map.put("user_id", getUserId());
        map.put("content", content);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getAddReply(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showLoading();
                getData(1, false);
                showMsg(obj.getMsg());
                et_kecheng_more_reply_discuss.setText("");
                et_kecheng_more_reply_discuss.setHint("请输入你对" + name + "的评论");
                tv_kecheng_more_reply_pinglun.setText("评论");

            }
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏软键盘 //
        imm.hideSoftInputFromWindow(et_kecheng_more_reply_discuss.getWindowToken(), 0);


    }
}
