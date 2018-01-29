package com.sk.lgtea.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.sk.lgtea.R;
import com.sk.lgtea.module.home.network.response.StudyDetailObj;

/**
 * Created by Administrator on 2017/7/21.
 */

public class KechengEvaluateCommentAdapter extends BaseRecyclerAdapter<StudyDetailObj.CommentListBean.ReplyListBean> {


    public KechengEvaluateCommentAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int i, StudyDetailObj.CommentListBean.ReplyListBean bean) {
        TextView tv_item_kecheng_evaluate_comment_name = holder.getTextView(R.id.tv_item_kecheng_evaluate_comment_name);
        TextView tv_item_kecheng_evaluate_comment_huifu = holder.getTextView(R.id.tv_item_kecheng_evaluate_comment_huifu);
        TextView tv_item_kecheng_evaluate_comment_toname = holder.getTextView(R.id.tv_item_kecheng_evaluate_comment_toname);
        TextView tv_item_kecheng_evaluate_comment_comment = holder.getTextView(R.id.tv_item_kecheng_evaluate_comment_comment);
        tv_item_kecheng_evaluate_comment_name.setText(bean.getName());
        tv_item_kecheng_evaluate_comment_toname.setText(bean.getName_to());
        if (bean.getCode().equals("commen")) {
            tv_item_kecheng_evaluate_comment_huifu.setVisibility(View.GONE);
            tv_item_kecheng_evaluate_comment_toname.setVisibility(View.GONE);

        }else {
            tv_item_kecheng_evaluate_comment_huifu.setVisibility(View.VISIBLE);
            tv_item_kecheng_evaluate_comment_toname.setVisibility(View.VISIBLE);

        }

        tv_item_kecheng_evaluate_comment_comment.setText(bean.getContent());


        // mAdapter.setList(bean.getReply_list(), true);

    }


}
