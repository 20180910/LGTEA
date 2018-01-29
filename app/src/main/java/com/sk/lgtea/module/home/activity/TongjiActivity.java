package com.sk.lgtea.module.home.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.module.home.bean.TongjiBean;
import com.sk.lgtea.module.home.fragment.KejiantongjiFragment;
import com.sk.lgtea.module.home.fragment.ZuoyetongjiFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/12.
 */

public class TongjiActivity extends BaseActivity {
    @BindView(R.id.ctl_tongji)
    CommonTabLayout ctl_tongji;
    @BindView(R.id.fl_tongji)
    FrameLayout fl_tongji;
    ArrayList<CustomTabEntity> list = new ArrayList<>();
    ArrayList<Fragment> fragList = new ArrayList<>();

    @Override
    protected int getContentView() {
        setAppTitle("统计");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_tongji;
    }

    @Override
    protected void initView() {
        list.add(new TongjiBean("课件统计"));
        list.add(new TongjiBean("作业统计"));
        fragList.add(KejiantongjiFragment.newInstance());
        fragList.add(ZuoyetongjiFragment.newInstance());
        ctl_tongji.setTabData(list, this, R.id.fl_tongji, fragList);

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onViewClick(View v) {

    }
}
