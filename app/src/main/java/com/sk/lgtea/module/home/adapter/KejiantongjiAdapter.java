package com.sk.lgtea.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.RxBus;
import com.sk.lgtea.R;
import com.sk.lgtea.module.home.event.PreviewEvent;
import com.sk.lgtea.module.home.network.response.KejiantongjiObj;

/**
 * Created by Administrator on 2017/7/21.
 */

public class KejiantongjiAdapter extends BaseRecyclerAdapter<KejiantongjiObj.CoursewareListBean> {



    public KejiantongjiAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }
    @Override
    public void bindData(RecyclerViewHolder holder, int i, KejiantongjiObj.CoursewareListBean obj) {
        TextView tv_item_kejiantongji_name = holder.getTextView(R.id.tv_item_kejiantongji_name);
        TextView tv_item_kejiantongji_liulan = holder.getTextView(R.id.tv_item_kejiantongji_liulan);
        TextView tv_item_kejiantongji_details = holder.getTextView(R.id.tv_item_kejiantongji_details);
        tv_item_kejiantongji_name.setText(obj.getTitle());
        tv_item_kejiantongji_liulan.setText("预览"+obj.getLiulan_list());
        tv_item_kejiantongji_details.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                RxBus.getInstance().post(new PreviewEvent(obj.getCourseware_id()));
            }
        });


    }


}