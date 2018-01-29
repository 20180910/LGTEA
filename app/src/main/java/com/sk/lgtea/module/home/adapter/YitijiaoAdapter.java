package com.sk.lgtea.module.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.RxBus;
import com.sk.lgtea.R;
import com.sk.lgtea.module.home.event.WenjianEvent;
import com.sk.lgtea.module.home.network.response.YitijiaoObj;

/**
 * Created by Administrator on 2017/7/21.
 */

public class YitijiaoAdapter extends BaseRecyclerAdapter<YitijiaoObj.OperationSubmitBeanX>{

    public YitijiaoAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int i, YitijiaoObj.OperationSubmitBeanX bean) {
        ImageView iv_item_yitijiao_icon = holder.getImageView(R.id.iv_item_yitijiao_icon);
        TextView tv_item_yitijiao_name = holder.getTextView(R.id.tv_item_yitijiao_name);
        TextView tv_item_yitijiao_time = holder.getTextView(R.id.tv_item_yitijiao_time);
        TextView tv_item_yitijiao_summary = holder.getTextView(R.id.tv_item_yitijiao_summary);
        LinearLayout ll_item_yitijiao_img = (LinearLayout) holder.getView(R.id.ll_item_yitijiao_img);




        ImageView iv_item_yitijiao_img1 = holder.getImageView(R.id.iv_item_yitijiao_img1);
        ImageView iv_item_yitijiao_img2 = holder.getImageView(R.id.iv_item_yitijiao_img2);
        ImageView iv_item_yitijiao_img3 = holder.getImageView(R.id.iv_item_yitijiao_img3);


        RelativeLayout rl_item_yitijiao_img1 = (RelativeLayout) holder.getView(R.id.rl_item_yitijiao_img1);
        RelativeLayout rl_item_yitijiao_img2 = (RelativeLayout) holder.getView(R.id.rl_item_yitijiao_img2);
        RelativeLayout rl_item_yitijiao_img3= (RelativeLayout) holder.getView(R.id.rl_item_yitijiao_img3);





        TextView tv_item_yitijiao_img1 = holder.getTextView(R.id.tv_item_yitijiao_img1);
        TextView tv_item_yitijiao_img2 = holder.getTextView(R.id.tv_item_yitijiao_img2);
        TextView tv_item_yitijiao_img3 = holder.getTextView(R.id.tv_item_yitijiao_img3);







        int   screenW = mContext.getResources().getDisplayMetrics().widthPixels;
        int wh=(screenW- PhoneUtils.dip2px(mContext,10))/3;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rl_item_yitijiao_img1.getLayoutParams();
        params.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
        params.width = wh;
        params.height = wh;
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) rl_item_yitijiao_img2.getLayoutParams();
        params2.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
        params2.width = wh;
        params2.height = wh;
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) rl_item_yitijiao_img3.getLayoutParams();
        params3.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
        params3.width = wh;
        params3.height = wh;
        rl_item_yitijiao_img1.setLayoutParams(params);
        rl_item_yitijiao_img2.setLayoutParams(params2);
        rl_item_yitijiao_img3.setLayoutParams(params3);

        Glide.with(mContext).load(bean.getHead_portrait()).error(R.drawable.my_people).into(iv_item_yitijiao_icon);
        tv_item_yitijiao_name.setText(bean.getName());
        tv_item_yitijiao_time.setText(bean.getAdd_time());
        tv_item_yitijiao_summary.setText(bean.getContent());
        if (bean.getContent().equals("")) {
            tv_item_yitijiao_summary.setVisibility(View.GONE);
        }else {
            tv_item_yitijiao_summary.setVisibility(View.VISIBLE);
        }
        if (bean.getOperation_submit().size()!=0) {
            ll_item_yitijiao_img.setVisibility(View.VISIBLE);
            if (bean.getOperation_submit().size()==1) {


                String value=bean.getOperation_submit().get(0).getImages();
                String id=bean.getOperation_submit().get(0).getId();
                String houzhui= value.substring( value.lastIndexOf("."),value.length());
                String name=value.substring(value.lastIndexOf("/")+1,value.length());

                Log.i("===","===houzhui="+houzhui);
                if (houzhui.equals(".gif")||houzhui.equals(".jpg")
                        ||houzhui.equals(".png")||houzhui.equals(".bmp")||houzhui.equals(".jpeg")) {
                    tv_item_yitijiao_img1.setVisibility(View.GONE);
                    iv_item_yitijiao_img1.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(value).error(R.color.c_press).into(iv_item_yitijiao_img1);

                }else {
                    tv_item_yitijiao_img1.setVisibility(View.VISIBLE);
                    iv_item_yitijiao_img1.setVisibility(View.GONE);
                    tv_item_yitijiao_img1.setText(name);
                    tv_item_yitijiao_img1.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            RxBus.getInstance().post(new WenjianEvent(id,name,value));



                        }
                    });



                }


                rl_item_yitijiao_img1.setVisibility(View.VISIBLE);
                rl_item_yitijiao_img2.setVisibility(View.INVISIBLE);
                rl_item_yitijiao_img3.setVisibility(View.INVISIBLE);

//    Glide.with(mContext).load(bean.getOperation_submit().get(0).getImages()).into(iv_item_yitijiao_img1);

            }else if (bean.getOperation_submit().size()==2){

                String value=bean.getOperation_submit().get(0).getImages();
                String value2=bean.getOperation_submit().get(1).getImages();
                String id=bean.getOperation_submit().get(0).getId()+"";
                String id2=bean.getOperation_submit().get(1).getId()+"";

                String houzhui= value.substring( value.lastIndexOf("."),value.length());
                String houzhui2= value2.substring( value2.lastIndexOf("."),value2.length());

                String name=value.substring(value.lastIndexOf("/")+1,value.length());
                String name2=value2.substring(value2.lastIndexOf("/")+1,value2.length());

                if (houzhui.equals(".gif")||houzhui.equals(".jpg")
                        ||houzhui.equals(".png")||houzhui.equals(".bmp")||houzhui.equals(".jpeg")) {
                    tv_item_yitijiao_img1.setVisibility(View.GONE);
                    iv_item_yitijiao_img1.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(value).error(R.color.c_press).into(iv_item_yitijiao_img1);

                }else {
                    tv_item_yitijiao_img1.setVisibility(View.VISIBLE);
                    iv_item_yitijiao_img1.setVisibility(View.GONE);
                    tv_item_yitijiao_img1.setText(name);
                    tv_item_yitijiao_img1.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            RxBus.getInstance().post(new WenjianEvent(id,name,value));



                        }
                    });

                }

                if (houzhui2.equals(".gif")||houzhui2.equals(".jpg")
                        ||houzhui2.equals(".png")||houzhui2.equals(".bmp")||houzhui2.equals(".jpeg")) {
                    tv_item_yitijiao_img2.setVisibility(View.GONE);
                    iv_item_yitijiao_img2.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(value2).error(R.color.c_press).into(iv_item_yitijiao_img2);

                }else {
                    tv_item_yitijiao_img2.setVisibility(View.VISIBLE);
                    iv_item_yitijiao_img2.setVisibility(View.GONE);
                    tv_item_yitijiao_img2.setText(name2);
                    tv_item_yitijiao_img2.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            RxBus.getInstance().post(new WenjianEvent(id2,name2,value2));



                        }
                    });

                }





                rl_item_yitijiao_img1.setVisibility(View.VISIBLE);
                rl_item_yitijiao_img2.setVisibility(View.VISIBLE);
                rl_item_yitijiao_img3.setVisibility(View.INVISIBLE);
//                Glide.with(mContext).load(bean.getOperation_submit().get(0).getImages()).into(iv_item_yitijiao_img1);
//                Glide.with(mContext).load(bean.getOperation_submit().get(1).getImages()).into(iv_item_yitijiao_img2);

            }else if (bean.getOperation_submit().size()==3){



                String value=bean.getOperation_submit().get(0).getImages();
                String value2=bean.getOperation_submit().get(1).getImages();
                String value3=bean.getOperation_submit().get(2).getImages();

                String id=bean.getOperation_submit().get(0).getId()+"";
                String id2=bean.getOperation_submit().get(1).getId()+"";
                String id3=bean.getOperation_submit().get(2).getId()+"";

                String houzhui= value.substring( value.lastIndexOf("."),value.length());
                String houzhui2= value2.substring( value2.lastIndexOf("."),value2.length());
                String houzhui3= value3.substring( value3.lastIndexOf("."),value3.length());

                String name=value.substring(value.lastIndexOf("/")+1,value.length());
                String name2=value2.substring(value2.lastIndexOf("/")+1,value2.length());
                String name3=value3.substring(value3.lastIndexOf("/")+1,value3.length());

                if (houzhui.equals(".gif")||houzhui.equals(".jpg")
                        ||houzhui.equals(".png")||houzhui.equals(".bmp")||houzhui.equals(".jpeg")) {
                    tv_item_yitijiao_img1.setVisibility(View.GONE);
                    iv_item_yitijiao_img1.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(value).error(R.color.c_press).into(iv_item_yitijiao_img1);

                }else {
                    tv_item_yitijiao_img1.setVisibility(View.VISIBLE);
                    iv_item_yitijiao_img1.setVisibility(View.GONE);
                    tv_item_yitijiao_img1.setText(name);
                    tv_item_yitijiao_img1.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            RxBus.getInstance().post(new WenjianEvent(id,name,value));



                        }
                    });

                }

                if (houzhui2.equals(".gif")||houzhui2.equals(".jpg")
                        ||houzhui2.equals(".png")||houzhui2.equals(".bmp")||houzhui2.equals(".jpeg")) {
                    tv_item_yitijiao_img2.setVisibility(View.GONE);
                    iv_item_yitijiao_img2.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(value2).error(R.color.c_press).into(iv_item_yitijiao_img2);

                }else {
                    tv_item_yitijiao_img2.setVisibility(View.VISIBLE);
                    iv_item_yitijiao_img2.setVisibility(View.GONE);
                    tv_item_yitijiao_img2.setText(name2);
                    tv_item_yitijiao_img2.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            RxBus.getInstance().post(new WenjianEvent(id2,name2,value2));



                        }
                    });

                }
                if (houzhui3.equals(".gif")||houzhui3.equals(".jpg")
                        ||houzhui3.equals(".png")||houzhui3.equals(".bmp")||houzhui3.equals(".jpeg")) {
                    tv_item_yitijiao_img3.setVisibility(View.GONE);
                    iv_item_yitijiao_img3.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(value3).error(R.color.c_press).into(iv_item_yitijiao_img3);

                }else {
                    tv_item_yitijiao_img3.setVisibility(View.VISIBLE);
                    iv_item_yitijiao_img3.setVisibility(View.GONE);
                    tv_item_yitijiao_img3.setText(name3);
                    tv_item_yitijiao_img3.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            RxBus.getInstance().post(new WenjianEvent(id3,name3,value3));



                        }
                    });

                }



                rl_item_yitijiao_img1.setVisibility(View.VISIBLE);
                rl_item_yitijiao_img2.setVisibility(View.VISIBLE);
                rl_item_yitijiao_img3.setVisibility(View.VISIBLE);
//                Glide.with(mContext).load(bean.getOperation_submit().get(0).getImages()).into(iv_item_yitijiao_img1);
//                Glide.with(mContext).load(bean.getOperation_submit().get(1).getImages()).into(iv_item_yitijiao_img2);
//                Glide.with(mContext).load(bean.getOperation_submit().get(2).getImages()).into(iv_item_yitijiao_img3);
            }

        }else {
            ll_item_yitijiao_img.setVisibility(View.GONE);

        }



    }


}
