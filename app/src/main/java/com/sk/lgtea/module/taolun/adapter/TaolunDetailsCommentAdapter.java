package com.sk.lgtea.module.taolun.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.sk.lgtea.R;
import com.sk.lgtea.module.taolun.network.response.TaolunDetailsObj;

/**
 * Created by Administrator on 2017/7/21.
 */

public class TaolunDetailsCommentAdapter extends BaseRecyclerAdapter<TaolunDetailsObj.CommentListBean.ReplyListBean> {

    public TaolunDetailsCommentAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }


    @Override
    public void bindData(RecyclerViewHolder holder, int position, TaolunDetailsObj.CommentListBean.ReplyListBean bean) {
        TextView tv_item_taolun_details_comment_name=holder.getTextView(R.id.tv_item_taolun_details_comment_name);
        TextView tv_item_taolun_details_comment=holder.getTextView(R.id.tv_item_taolun_details_comment);
        ImageView iv_item_taolun_details_comment=holder.getImageView(R.id.iv_item_taolun_details_comment);
        if (position==0) {
            iv_item_taolun_details_comment.setVisibility(View.GONE);
        }else {
            iv_item_taolun_details_comment.setVisibility(View.VISIBLE);
        }
        tv_item_taolun_details_comment_name.setText(bean.getName());
        tv_item_taolun_details_comment.setText(bean.getContent());

    }


}
