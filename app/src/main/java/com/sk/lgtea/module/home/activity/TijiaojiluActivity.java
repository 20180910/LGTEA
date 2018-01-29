package com.sk.lgtea.module.home.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.bean.TongjiBean;
import com.sk.lgtea.module.home.fragment.WeitijiaoFragment;
import com.sk.lgtea.module.home.fragment.YitijiaoFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/12.
 */

public class TijiaojiluActivity extends BaseActivity {
    @BindView(R.id.ctl_tijiaojilu)
    CommonTabLayout ctl_tijiaojilu;
    @BindView(R.id.fl_tijiaojilu)
    FrameLayout fl_tijiaojilu;
    ArrayList<CustomTabEntity> list = new ArrayList<>();
    ArrayList<Fragment> fragList = new ArrayList<>();
    String operation_id;

    @Override
    protected int getContentView() {
        setAppTitle("作业提交记录");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_tijiaojilu;
    }

    @Override
    protected void initView() {
        getValue();

        list.add(new TongjiBean("已提交"));
        list.add(new TongjiBean("未提交"));
        fragList.add(YitijiaoFragment.newInstance(operation_id));
        fragList.add(WeitijiaoFragment.newInstance(operation_id));
        ctl_tijiaojilu.setTabData(list, this, R.id.fl_tijiaojilu, fragList);



    }

    private void getValue() {
        operation_id=getIntent().getStringExtra(Constant.IParam.operation_id);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
