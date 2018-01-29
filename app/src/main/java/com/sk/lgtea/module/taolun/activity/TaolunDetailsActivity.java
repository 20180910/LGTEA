package com.sk.lgtea.module.taolun.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.DateUtils;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.customview.MyImageView;
import com.github.customview.MyTextView;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.taolun.Constant;
import com.sk.lgtea.module.taolun.adapter.TaolunDetailsCommentAdapter;
import com.sk.lgtea.module.taolun.network.ApiRequest;
import com.sk.lgtea.module.taolun.network.response.DianzanObj;
import com.sk.lgtea.module.taolun.network.response.TaolunDetailsObj;
import com.sk.lgtea.tools.SoftKeyBoardListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class TaolunDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_taolun_details_icon)
    MyImageView iv_taolun_details_icon;
    @BindView(R.id.tv_taolun_details_message)
    TextView tv_taolun_details_message;
    @BindView(R.id.tv_taolun_details_zan)
    TextView tv_taolun_details_zan;
    @BindView(R.id.tv_taolun_details_all)
    TextView tv_taolun_details_all;
    @BindView(R.id.rv_taolun_details)
    RecyclerView rv_taolun_details;
    @BindView(R.id.et_taolun_details_discuss)
    EditText et_taolun_details_discuss;
    @BindView(R.id.iv_taolun_details_discuss)
    ImageView iv_taolun_details_discuss;
    @BindView(R.id.iv_taolun_details_zan)
    ImageView iv_taolun_details_zan;
    @BindView(R.id.iv_taolun_details_collection)
    ImageView iv_taolun_details_collection;
    @BindView(R.id.iv_taolun_details_share)
    ImageView iv_taolun_details_share;
    @BindView(R.id.tv_item_taolun_details_content)
    TextView tv_item_taolun_details_content;
    @BindView(R.id.rv_item_taolun_details_img)
    RecyclerView rv_item_taolun_details_img;
    @BindView(R.id.tv_taolun_details_title)
    TextView tv_taolun_details_title;
    @BindView(R.id.tv_taolun_details_name)
    TextView tv_taolun_details_name;
    @BindView(R.id.tv_taolun_details_time)
    TextView tv_taolun_details_time;
    @BindView(R.id.tv_taolun_details_pinglun)
    MyTextView tv_taolun_details_pinglun;
    LoadMoreAdapter adapter;
    BaseRecyclerAdapter imgadapter;
    String discussion_forum_id;
    String content="";
    int type=1;
    String tieziId;



    @Override
    protected int getContentView() {
        setAppTitle("讨论区");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_taolun_details;
    }

    @Override
    protected void initView() {
        getValue();

        SoftKeyBoardListener.setListener(mContext, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            //有软键盘
            @Override
            public void keyBoardShow(int height) {
                if (type==1) {
                    et_taolun_details_discuss.setHint("请输入你的评论");
                }
                et_taolun_details_discuss.setCursorVisible(true);
                iv_taolun_details_discuss.setVisibility(View.GONE);
                iv_taolun_details_zan.setVisibility(View.GONE);
                iv_taolun_details_share.setVisibility(View.GONE);
                tv_taolun_details_pinglun.setVisibility(View.VISIBLE);
            }

            //无软键盘
            @Override
            public void keyBoardHide(int height) {
                et_taolun_details_discuss.setHint("");
                content=getSStr(et_taolun_details_discuss);
                if (!content.equals("")) {
                    et_taolun_details_discuss.setCursorVisible(true);
                    iv_taolun_details_discuss.setVisibility(View.GONE);
                    iv_taolun_details_zan.setVisibility(View.GONE);
                    iv_taolun_details_share.setVisibility(View.GONE);
                    tv_taolun_details_pinglun.setVisibility(View.VISIBLE);

                }else {
                    type=1;
                    discussion_forum_id=tieziId;
                    et_taolun_details_discuss.setCursorVisible(false);
                    iv_taolun_details_discuss.setVisibility(View.VISIBLE);
                    iv_taolun_details_zan.setVisibility(View.VISIBLE);
                    iv_taolun_details_share.setVisibility(View.GONE);
                    tv_taolun_details_pinglun.setVisibility(View.GONE);

                }



            }
        });




        imgadapter = new BaseRecyclerAdapter<String>(mContext, R.layout.item_taolun_img) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, String bean) {
                ImageView iv_item_taolun_img = holder.getImageView(R.id.iv_item_taolun_img);

                int   screenW = mContext.getResources().getDisplayMetrics().widthPixels;
                int wh=(screenW-PhoneUtils.dip2px(mContext,30))/3;
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv_item_taolun_img.getLayoutParams();
                params.width = wh;
                params.height =  wh;
                params.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
                iv_item_taolun_img.setLayoutParams(params);

                Glide.with(mContext).load(bean).error(R.color.c_press).into(iv_item_taolun_img);

            }
        };
        rv_item_taolun_details_img.setLayoutManager(new GridLayoutManager(mContext, 3));
//        rv_item_taolun_details_img.addItemDecoration(new DividerGridItemDecoration(mContext, PhoneUtils.dip2px(mContext, 5), R.color.white));
        rv_item_taolun_details_img.setNestedScrollingEnabled(false);
        rv_item_taolun_details_img.setAdapter(imgadapter);


        adapter = new LoadMoreAdapter<TaolunDetailsObj.CommentListBean>(mContext, R.layout.item_taolun_details, pageSize, nsv) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, TaolunDetailsObj.CommentListBean bean) {
                TextView tv_item_taolun_detalis_name = holder.getTextView(R.id.tv_item_taolun_detalis_name);
                ImageView iv_item_taolun_details_line = holder.getImageView(R.id.iv_item_taolun_details_line);
                TextView tv_item_taolun_detalis_time = holder.getTextView(R.id.tv_item_taolun_detalis_time);
                TextView tv_item_taolun_detalis_comment = holder.getTextView(R.id.tv_item_taolun_detalis_comment);
                TextView tv_taolun_details_more_comment = holder.getTextView(R.id.tv_taolun_details_more_comment);
                LinearLayout ll_item_taolun_details_erjipinlun = (LinearLayout) holder.getView(R.id.ll_item_taolun_details_erjipinlun);
                RecyclerView rv_item_taolun_details_comment = (RecyclerView) holder.getView(R.id.rv_item_taolun_details_comment);
                if (bean.getReply_list().size() >= 2) {
                    tv_taolun_details_more_comment.setVisibility(View.VISIBLE);
                    iv_item_taolun_details_line.setVisibility(View.VISIBLE);
                    rv_item_taolun_details_comment.setVisibility(View.VISIBLE);
                } else {
                    tv_taolun_details_more_comment.setVisibility(View.GONE);
                    iv_item_taolun_details_line.setVisibility(View.INVISIBLE);
                    rv_item_taolun_details_comment.setVisibility(View.GONE);
                }
                tv_item_taolun_detalis_name.setText(bean.getName());
                tv_item_taolun_detalis_time.setText(DateUtils.dateToString(new Date(bean.getComment_time() * 1000), "yyyy-MM-dd HH:mm"));
                tv_item_taolun_detalis_comment.setText(bean.getContent());

                List<TaolunDetailsObj.CommentListBean.ReplyListBean> replyList = new ArrayList<>();
                for (int i1 = 0; i1 < bean.getReply_list().size(); i1++) {
                    replyList.add(bean.getReply_list().get(i1));
                }

//                mAdapter.set
                TaolunDetailsCommentAdapter mAdapter = new TaolunDetailsCommentAdapter(mContext, R.layout.item_taolun_details_comment);
                mAdapter.setList(replyList, true);
                rv_item_taolun_details_comment.setLayoutManager(new LinearLayoutManager(mContext));
                rv_item_taolun_details_comment.setNestedScrollingEnabled(false);
                rv_item_taolun_details_comment.setAdapter(mAdapter);

                tv_taolun_details_more_comment.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IParam.comments_id, bean.getComments_id());
                        STActivity(intent, MoreReplyActivity.class);
                    }
                });
                //二级评论
                tv_item_taolun_detalis_comment.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        type=2;
                        discussion_forum_id=bean.getComments_id();
                        //弹出软键盘
//

                        InputMethodManager mInput= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        mInput.toggleSoftInput(0,InputMethodManager.SHOW_FORCED);
                        et_taolun_details_discuss.setHint("请输入你对"+bean.getName()+"的评论");
                        et_taolun_details_discuss.requestFocus();//获取焦点
                    }
                });

            }


        };
        adapter.setOnLoadMoreListener(this);
        rv_taolun_details.setLayoutManager(new LinearLayoutManager(mContext));
        rv_taolun_details.setNestedScrollingEnabled(false);
        rv_taolun_details.setAdapter(adapter);


    }

    private void getValue() {
        tieziId=getIntent().getStringExtra(Constant.IParam.discussion_forum_id);
        discussion_forum_id = tieziId;
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
        map.put("discussion_forum_id", discussion_forum_id);
        map.put("pagesize", pageSize + "");
        map.put("page", page + "");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getDiscussionForumDetail(map, new MyCallBack<TaolunDetailsObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(TaolunDetailsObj obj) {

                tv_taolun_details_title.setText(obj.getTitle());
                tv_item_taolun_details_content.setText(obj.getContent());
                Glide.with(mContext).load(obj.getHead_portrait()).error(R.drawable.my_people).into(iv_taolun_details_icon);
                tv_taolun_details_name.setText(obj.getName());
                tv_taolun_details_time.setText(DateUtils.dateToString(new Date(obj.getAdd_time() * 1000), "yyyy-MM-dd HH:mm"));
                tv_taolun_details_message.setText(obj.getComment_count());
                tv_taolun_details_zan.setText(obj.getThumbup_count());
                if (obj.getIs_thumbup().equals("0")) {
                    tv_taolun_details_zan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.my_collection_zan),
                            null,
                            null,
                            null);
                    iv_taolun_details_zan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_dianzan));
                } else {
                    tv_taolun_details_zan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.my_collection_zan_selset),
                            null,
                            null,
                            null);
                    iv_taolun_details_zan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dianzan_select));
                }
                List<String> imgList = new ArrayList<String>();
                for (int i = 0; i < obj.getImage_list().size(); i++) {
                    imgList.add(obj.getImage_list().get(i));
                }
                imgadapter.setList(imgList, true);


                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getComment_list(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getComment_list(), true);
                }


            }
        });


    }


    @OnClick({R.id.tv_taolun_details_all,
            R.id.iv_taolun_details_zan,
            R.id.iv_taolun_details_collection,
            R.id.iv_taolun_details_share,
            R.id.tv_taolun_details_pinglun})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_taolun_details_all:
                break;
            case R.id.iv_taolun_details_zan:
                //点赞
                getthumbupForum();


                break;
            case R.id.iv_taolun_details_collection:
                break;
            case R.id.iv_taolun_details_share:
                showFenXiang();
                break;
            case R.id.tv_taolun_details_pinglun:


                getAddCommentCourseWare();


                break;
        }
    }

    private void getthumbupForum() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("forum_courseware_id",tieziId);
        map.put("type",1+"");
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getthumbupForum(map, new MyCallBack<DianzanObj>(mContext) {
            @Override
            public void onSuccess(DianzanObj obj) {
                if (obj.getIs_thumbup()==0) {
                    tv_taolun_details_zan.setText(obj.getThumbup_count()+"");
                    iv_taolun_details_zan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_dianzan));
                    tv_taolun_details_zan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.my_collection_zan),
                            null,
                            null,
                            null);
                }else {
                    tv_taolun_details_zan.setText(obj.getThumbup_count()+"");
                    iv_taolun_details_zan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dianzan_select));
                    tv_taolun_details_zan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.my_collection_zan_selset),
                            null,
                            null,
                            null);

                }


            }
        });

    }

    private void getAddCommentCourseWare() {
        content=getSStr(et_taolun_details_discuss);
        if (TextUtils.isEmpty(content)) {
            showMsg("评论不能为空！");
            return;
        }
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("forum_comment_id",discussion_forum_id);
        map.put("user_id",getUserId());
        map.put("type",type+"");
        map.put("content",content+"");
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getAddCommentDiscussionForum(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {

                discussion_forum_id=tieziId;
                showLoading();
                getData(1, false);
                showMsg(obj.getMsg());
                et_taolun_details_discuss.setText("");
            }
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏软键盘 //
        imm.hideSoftInputFromWindow(et_taolun_details_discuss.getWindowToken(), 0);

    }

}

