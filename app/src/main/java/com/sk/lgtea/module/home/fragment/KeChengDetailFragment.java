package com.sk.lgtea.module.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyImageView;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/12/5.
 */

public class KeChengDetailFragment extends BaseFragment {
    @BindView(R.id.iv_kecheng_detail_icon)
    MyImageView iv_kecheng_detail_icon;
    @BindView(R.id.tv_kecheng_detail_name)
    TextView tv_kecheng_detail_name;
    @BindView(R.id.tv_kecheng_detail_jieshao)
    TextView tv_kecheng_detail_jieshao;
    @BindView(R.id.tv_kecheng_detail_kecheng_jianjie)
    TextView tv_kecheng_detail_kecheng_jianjie;

    @Override
    protected int getContentView() {
        return R.layout.frag_kecheng_detail;
    }

    public static KeChengDetailFragment newInstance() {

        Bundle args = new Bundle();

        KeChengDetailFragment fragment = new KeChengDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    public void setImg(String img) {
        Glide.with(mContext).load(img).error(R.drawable.my_people).into(iv_kecheng_detail_icon);

    }
    public void setIndividuality_signature(String individuality_signature) {
        if (individuality_signature.equals("")) {
            tv_kecheng_detail_jieshao.setText("无个性签名");
        }else {
            tv_kecheng_detail_jieshao.setText(individuality_signature);
        }

    }

    public void setName(String keynote_speaker) {
        tv_kecheng_detail_name.setText(keynote_speaker);
    }

    public void setCourseware(String courseware_introduction) {
        tv_kecheng_detail_kecheng_jianjie.setText(courseware_introduction);

    }

    @Override
    protected void onViewClick(View v) {

    }
}
