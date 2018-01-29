package com.sk.lgtea.module.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.sk.lgtea.R;
import com.sk.lgtea.module.home.network.response.KejiantongjiXiangqingObj;

/**
 * Created by Administrator on 2017/7/21.
 */

public class ChakanjiluAdapter extends BaseRecyclerAdapter<KejiantongjiXiangqingObj.CoursewareRecordListBean> {


    public ChakanjiluAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int i, KejiantongjiXiangqingObj.CoursewareRecordListBean bean) {
        ImageView iv_item_chakanjilu_icon = holder.getImageView(R.id.iv_item_chakanjilu_icon);
        TextView tv_item_chakanjilu_name = holder.getTextView(R.id.tv_item_chakanjilu_name);
        TextView tv_item_chakanjilu_time = holder.getTextView(R.id.tv_item_chakanjilu_time);

        Glide.with(mContext).load(bean.getHead_portrait()).error(R.drawable.my_people).into(iv_item_chakanjilu_icon);
        tv_item_chakanjilu_name.setText(bean.getName());
        tv_item_chakanjilu_time.setText(bean.getAdd_time());


    }


}
