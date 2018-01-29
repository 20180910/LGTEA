package com.sk.lgtea.module.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.linechart.LineChartView;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.activity.BenzhouzuoyeActivity;
import com.sk.lgtea.module.home.activity.TijiaojiluActivity;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.KechengliebiaoObj;
import com.sk.lgtea.module.home.network.response.ZuoyetongjiObj;
import com.sk.lgtea.tools.AndroidUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by Administrator on 2017/12/12.
 */

public class ZuoyetongjiFragment extends BaseFragment {
    @BindView(R.id.tv_zuoyetongji_zuixin_details)
    TextView tv_zuoyetongji_zuixin_details;
    @BindView(R.id.tv_zuoyetongji_kecheng)
    TextView tv_zuoyetongji_kecheng;
    @BindView(R.id.sb_zuoyetongji)
    SeekBar sb_zuoyetongji;
    @BindView(R.id.tv_zuoyetongji_yijiao_num)
    TextView tv_zuoyetongji_yijiao_num;
    @BindView(R.id.tv_zuoyetongji_weijiao_num)
    TextView tv_zuoyetongji_weijiao_num;
    @BindView(R.id.tv_zuoyetongji_benzhou_details)
    TextView tv_zuoyetongji_benzhou_details;
    @BindView(R.id.tv_zuoyetongji_tijiaolv)
    TextView tv_zuoyetongji_tijiaolv;
    @BindView(R.id.lcv_zuoyetongji)
    LineChartView lcv_zuoyetongji;
    List<KechengliebiaoObj> list=new ArrayList<>();
    List<String>kechengName=new ArrayList<>();
    String course_id,operation_id;
    int yitijiaoNum,weitijiaoNum,allNum,progressNum;

    @Override
    protected int getContentView() {
        return R.layout.frag_zuoyetongji;
    }

    public static ZuoyetongjiFragment newInstance() {
        Bundle args = new Bundle();
        ZuoyetongjiFragment fragment = new ZuoyetongjiFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        sb_zuoyetongji.setOnTouchListener(new SeekBar.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        showProgress();
        getCourseList();
    }

    private void getCourseList() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getCourseList(map, new MyCallBack<List<KechengliebiaoObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<KechengliebiaoObj> obj) {
                if (obj.size()!=0) {
                   list=obj;
                    for (int i = 0; i < obj.size(); i++) {
                        kechengName.add(obj.get(i).getCourse_name());
                    }
                    course_id=list.get(0).getCourse_id();
                    tv_zuoyetongji_kecheng.setText(list.get(0).getCourse_name());
                    Log.i("===","==course_id="+course_id);
                    //网络请求
                    getHomeworkStatistic(course_id);
                }else {
                    getHomeworkStatistic(0+"");
                    tv_zuoyetongji_kecheng.setText("暂无课程");
                    tv_zuoyetongji_kecheng.setEnabled(false);

//                    showMsg("暂无课程");
//                    getActivity().finish();
                }
            }
        });
    }

    private void getHomeworkStatistic(String id) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("course_id",id);
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getHomeworkStatistic(map, new MyCallBack<ZuoyetongjiObj>(mContext) {
            @Override
            public void onSuccess(ZuoyetongjiObj obj) {
                operation_id=obj.getOperation_id();
                yitijiaoNum=obj.getYijiao();
                weitijiaoNum=obj.getWeijiao();
                initLineDatas(obj.getList_time());
                allNum=yitijiaoNum+weitijiaoNum;
                tv_zuoyetongji_yijiao_num.setText("已交"+yitijiaoNum+"人");
                tv_zuoyetongji_weijiao_num.setText("未交"+weitijiaoNum+"人");
                if (allNum==yitijiaoNum) {
                    progressNum=100;
                }else {
                    progressNum=yitijiaoNum*100/allNum;
                }
                sb_zuoyetongji.setProgress(progressNum);
            }
        });
    }


    private void initLineDatas(List<ZuoyetongjiObj.ListTimeBean> bean) {
        double tijiaolv=0.0;
        List<Double> datas = new ArrayList<>();
        List<String> description = new ArrayList<>();
        for (int i = bean.size()-1; i >= 0; i--) {
            tijiaolv=tijiaolv+bean.get(i).getTijiaolv();
            datas.add(bean.get(i).getTijiaolv());
            description.add(bean.get(i).getTime());
        }
        double pingjun= AndroidUtils.round(tijiaolv/7);
        tv_zuoyetongji_tijiaolv.setText(pingjun+"%");
        //setMaxData()

        lcv_zuoyetongji.setDatas(datas, description);
    }

    public void onOptionPicker() {
        SinglePicker<String> picker = new SinglePicker<>(getActivity(), kechengName);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(0);
        picker.setWheelModeEnable(true);
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(0xFF279BAA);//前四位值是透明度
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                course_id=list.get(index).getCourse_id();
                tv_zuoyetongji_kecheng.setText(list.get(index).getCourse_name());
                //网络请求
                getHomeworkStatistic(course_id);
            }
        });
        picker.show();
    }


    @OnClick({R.id.tv_zuoyetongji_zuixin_details,
            R.id.tv_zuoyetongji_benzhou_details,
    R.id.tv_zuoyetongji_kecheng})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_zuoyetongji_zuixin_details:
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.operation_id,operation_id);
                STActivity(intent,TijiaojiluActivity.class);
                break;
            case R.id.tv_zuoyetongji_benzhou_details:
                STActivity(BenzhouzuoyeActivity.class);
                break;
            case R.id.tv_zuoyetongji_kecheng:
                onOptionPicker();
                break;
        }
    }
}
