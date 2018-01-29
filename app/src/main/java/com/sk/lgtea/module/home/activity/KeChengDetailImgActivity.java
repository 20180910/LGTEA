package com.sk.lgtea.module.home.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/7.
 */

public class KeChengDetailImgActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.iv_kecheng_detail_img_back_pdf)
    ImageView iv_kecheng_detail_img_back_pdf;
    @BindView(R.id.tv_kecheng_detail_img_yeshu)
    TextView tv_kecheng_detail_img_yeshu;
    @BindView(R.id.iv_kecheng_detail_suoxiao_pdf)
    ImageView iv_kecheng_detail_suoxiao_pdf;
    @BindView(R.id.vp_kecheng_detail_img)
    ViewPager vp_kecheng_detail_img;
    private ImageView[] mImageViews;
    List<String> imgIdArray=new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.act_kecheng_detail_img;
    }

    @Override
    protected void initView() {
        getRcv();

    }
    private void getRcv() {
        imgIdArray.add("zzz");
        imgIdArray.add("zzz");
        imgIdArray.add("zzz");
        imgIdArray.add("zzz");
        imgIdArray.add("zzz");
        imgIdArray.add("zzz");
        imgIdArray.add("zzz");
        imgIdArray.add("zzz");
        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.size()];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(this);


            mImageViews[i] = imageView;
            Glide.with(mContext).
                    load(imgIdArray.get(i)).
                    error(R.drawable.banner).
                    into(imageView);
        }

        //设置Adapter
        vp_kecheng_detail_img.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        vp_kecheng_detail_img.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
//        vp_kecheng_detail.setCurrentItem((mImageViews90) );



    }
    /**
     *
     * @author xiaanming
     *
     */
    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgIdArray.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            try {
                ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
            }catch(Exception e){
                //handler something
            }
            return mImageViews[position % mImageViews.length];
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.iv_kecheng_detail_img_back_pdf, R.id.iv_kecheng_detail_suoxiao_pdf})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_kecheng_detail_img_back_pdf:
                finish();
                break;
            case R.id.iv_kecheng_detail_suoxiao_pdf:
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int indext=position+1;
        tv_kecheng_detail_img_yeshu.setText(indext+"/"+imgIdArray.size()+"页");

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
