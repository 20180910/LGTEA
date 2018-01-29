package com.sk.lgtea.module.taolun.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.sk.lgtea.R;

/**
 * Created by Administrator on 2017/7/21.
 */

public class TaolunImgAdapter extends BaseRecyclerAdapter<String> {


    public TaolunImgAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);

    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, String bean) {
        ImageView iv_item_taolun_img = holder.getImageView(R.id.iv_item_taolun_img);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.width = PhoneUtils.getScreenWidth(mContext)/3;
//        layoutParams.height = (int) ( layoutParams.width*0.45);
//
//        iv_item_taolun_img.setLayoutParams(layoutParams);


        int   screenW = mContext.getResources().getDisplayMetrics().widthPixels;
        int wh=(screenW-PhoneUtils.dip2px(mContext,30))/3;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv_item_taolun_img.getLayoutParams();
        params.width = wh;
        params.height =  wh;
        params.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
        iv_item_taolun_img.setLayoutParams(params);

        Glide.with(mContext).load(bean).error(R.color.c_press).into(iv_item_taolun_img);


    }
}
