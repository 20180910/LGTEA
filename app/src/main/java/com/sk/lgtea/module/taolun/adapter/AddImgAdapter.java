package com.sk.lgtea.module.taolun.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.RxBus;
import com.sk.lgtea.R;
import com.sk.lgtea.module.taolun.event.AddImgEvent;

/**
 * Created by Administrator on 2017/11/25.
 */

public class AddImgAdapter extends BaseRecyclerAdapter<String> {

    private int selectImgIndex;

    public AddImgAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder itemHolder;
        if(viewType==1){
            itemHolder = new RecyclerViewHolder(this.mContext, this.mInflater.inflate(R.layout.item_fatie_addimg, parent, false));
        }else{
            itemHolder = new RecyclerViewHolder(this.mContext, this.mInflater.inflate(R.layout.item_fatie_img, parent, false));
        }
        return itemHolder;
    }
    @Override
    public void bindData(RecyclerViewHolder itemHolder, int itemPosition, String bean) {
        itemHolder.itemView.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if(itemPosition==mList.size()){
                    selectImgIndex =-1;
                }else{
                    selectImgIndex =itemPosition;
                }
                addPhoto();
            }
            private void addPhoto() {
                RxBus.getInstance().post(new AddImgEvent(selectImgIndex));
            }
        });
        if(getItemViewType(itemPosition)==1){

        }else{
            ImageView imageView = itemHolder.getImageView(R.id.iv_add_img);
            int   screenW = mContext.getResources().getDisplayMetrics().widthPixels;
            int wh=(screenW- PhoneUtils.dip2px(mContext,30))/3;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            params.width = wh;
            params.height = wh;
            params.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
            imageView.setLayoutParams(params);

            Glide.with(mContext).load(bean).error(R.color.c_press).into(imageView);
        }
    }
    @Override
    public int getItemViewType(int itemPosition) {
        if(itemPosition==mList.size()&&mList.size()<3){
            return 1;
        }else{
            return 0;
        }
    }
    @Override
    public int getItemCount() {
        if(mList==null){
            return 0;
        }else if(mList.size()>=3){
            return mList.size();
        }else{
            return mList==null?0:mList.size()+1;
        }
    }
}

