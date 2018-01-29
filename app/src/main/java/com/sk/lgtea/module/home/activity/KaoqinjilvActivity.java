package com.sk.lgtea.module.home.activity;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.github.baseclass.rx.MySubscriber;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.bean.TongjiBean;
import com.sk.lgtea.module.home.event.ChuqinEvent;
import com.sk.lgtea.module.home.event.QueqinEvent;
import com.sk.lgtea.module.home.fragment.ChuqinFragment;
import com.sk.lgtea.module.home.fragment.QueqinFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/13.
 */

public class KaoqinjilvActivity extends BaseActivity {


    @BindView(R.id.ctl_kaoqinjilv)
    CommonTabLayout ctl_kaoqinjilv;
    @BindView(R.id.fl_kaoqinjilv)
    FrameLayout fl_kaoqinjilv;
    ArrayList<CustomTabEntity> list = new ArrayList<>();
    ArrayList<Fragment> fragList = new ArrayList<>();

    String course_id, current_time;

    @Override
    protected int getContentView() {
        setAppTitle("考勤记录");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_kaoqinjilv;
    }

    @Override
    protected void initView() {
        getValue();
        list.add(new TongjiBean("出勤"));
        list.add(new TongjiBean("缺勤"));
        fragList.add(ChuqinFragment.newInstance(course_id,current_time));
        fragList.add(QueqinFragment.newInstance(course_id,current_time));
        ctl_kaoqinjilv.setTabData(list, this, R.id.fl_kaoqinjilv, fragList);

    }

    private void getValue() {

        course_id = getIntent().getStringExtra(Constant.IParam.course_id);
        current_time = getIntent().getStringExtra(Constant.IParam.current_time);
    }

    @Override
    protected void initData() {



    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(ChuqinEvent.class, new MySubscriber<ChuqinEvent>() {
            @Override
            public void onMyNext(ChuqinEvent event) {
                Log.i("===","==event="+event.num+"");

               list.set(0,new TongjiBean("出勤"+event.num+"人"));
            }
                });
        getRxBusEvent(QueqinEvent.class, new MySubscriber<QueqinEvent>() {
            @Override
            public void onMyNext(QueqinEvent event) {
                Log.i("===","==event="+event.num+"");

               list.set(1,new TongjiBean("缺勤"+event.num+"人"));
            }
                });
    }

    @Override
    protected void onViewClick(View v) {

    }
}
