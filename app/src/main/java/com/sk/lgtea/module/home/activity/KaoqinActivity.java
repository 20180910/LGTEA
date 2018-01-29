package com.sk.lgtea.module.home.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.github.androidtools.DateUtils;
import com.github.androidtools.PhoneUtils;
import com.github.rxjava.rxbus.RxUtils;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.linechart.LineChartView;
import com.sk.lgtea.linechart.PieChartView;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.ErweimaObj;
import com.sk.lgtea.module.home.network.response.KaoqinObj;
import com.sk.lgtea.module.home.network.response.KechengliebiaoObj;
import com.sk.lgtea.tools.AndroidUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Administrator on 2017/12/13.
 */

public class KaoqinActivity extends BaseActivity {
    @BindView(R.id.iv_kaoqin_erweima_icon)
    ImageView iv_kaoqin_erweima_icon;
    @BindView(R.id.tv_kaoqin_banji)
    TextView tv_kaoqin_banji;
    @BindView(R.id.tv_kaoqin_time)
    TextView tv_kaoqin_time;
    @BindView(R.id.tv_kaoqin_tongji_details)
    TextView tv_kaoqin_tongji_details;
    @BindView(R.id.pcv_kaoqin)
    PieChartView pcv_kaoqin;
    @BindView(R.id.tv_kaoqin_chuqin_num)
    TextView tv_kaoqin_chuqin_num;
    @BindView(R.id.tv_kaoqin_queqin_num)
    TextView tv_kaoqin_queqin_num;
    @BindView(R.id.tv_kaoqin_zongrenshu)
    TextView tv_kaoqin_zongrenshu;
    @BindView(R.id.tv_kaoqin_chuqinlv)
    TextView tv_kaoqin_chuqinlv;
   /* @BindView(R.id.lcv_kaoqin)
    LineChartView lcv_kaoqin;*/
   @BindView(R.id.ll_kaoqin_lcv)
   LinearLayout ll_kaoqin_lcv;
    List<KechengliebiaoObj> list=new ArrayList<>();
    List<String>kechengName=new ArrayList<>();
    private int orangeColor = Color.argb(255, 244, 183, 88);
    private int blueColor = Color.argb(255, 125, 198, 250);
    String time,course_id;
    int chuqinNum,queqinNum;
    List<KaoqinObj.ListTimeBean> kaoqinList=new ArrayList<>();
    Dialog dlg;
    Dialog dialog;
    private TextView tv;
//    private Subscription subscribe;

    @Override
    protected int getContentView() {
        setAppTitle("考勤");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_kaoqin;
    }

    @Override
    protected void initView() {

        SimpleDateFormat sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd");
        time   =    sDateFormat.format(new    java.util.Date());
        tv_kaoqin_time.setText(time);


       /* List<Double> datas = new ArrayList<>();
        datas.add(0.0);
        datas.add(0.0);
        datas.add(0.0);
        datas.add(0.0);
        datas.add(0.0);
        datas.add(0.0);
        datas.add(0.0);

        List<String> mDescription = new ArrayList<>();

        mDescription.add("");
        mDescription.add("");
        mDescription.add("");
        mDescription.add("");
        mDescription.add("");
        mDescription.add("");
        mDescription.add("");
        lcv_kaoqin.setDatas(datas, mDescription);*/

    }

    @Override
    protected void initData() {
        showProgress();
        getCourseList();


    }

    private void initPieDatas(float chuqin,float queqin) {

        List<Float> mRatios = new ArrayList<>();

        List<String> mDescription = new ArrayList<>();

        List<Integer> mArcColors = new ArrayList<>();

        mRatios.add(chuqin);
        mRatios.add(queqin);
        mArcColors.add(blueColor);
        mArcColors.add(orangeColor);
        mDescription.add("描述一");
        mDescription.add("描述二");

        //点击动画开启
//        pcv_kaoqin.setCanClickAnimation(true);
        pcv_kaoqin.setDatas(mRatios, mArcColors, mDescription);
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
                    tv_kaoqin_banji.setText(list.get(0).getCourse_name());
                    //网络请求
                    showLoading();
                   getAttendanceStatistic();
                }else {
                    course_id=0+"";
                    showLoading();
                    getAttendanceStatistic();
                    tv_kaoqin_banji.setEnabled(false);
                    tv_kaoqin_banji.setText("暂无数据");
//                    showMsg("暂无课程");
//                    getActivity().finish();
                }
            }
        });
    }

    private void getAttendanceStatistic() {

        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("course_id",course_id);
        map.put("current_time",time);
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getAttendanceStatistic(map, new MyCallBack<KaoqinObj>(mContext) {
            @Override
            public void onSuccess(KaoqinObj obj) {

                chuqinNum=obj.getChuqin();
                queqinNum=obj.getQueqin();
                int allNum=chuqinNum+queqinNum;
                //修改
                float  chuqinnum =Float.parseFloat(obj.getChuqin()+"");
                float  queqinnum =Float.parseFloat(obj.getQueqin()+"");
                float  allnum =chuqinnum+queqinnum;


                Log.i("===","====chuqinnum="+chuqinnum);
                Log.i("===","====queqinnum="+queqinnum);
                Log.i("===","====allnum="+allnum);
                Log.i("===","====obj.getList_time()="+obj.getList_time().size());
                tv_kaoqin_chuqin_num.setText("出勤"+chuqinNum+"人");
                tv_kaoqin_queqin_num.setText("缺勤"+queqinNum+"人");
              ;
                float queqin;
                float chuqin;
                //Float
                if (queqinnum==0) {
                     queqin=0f;
                }else {
                     queqin=queqinnum/allnum;
                }
                if (chuqinnum==0) {
                     chuqin=0f;
                }else {
                     chuqin=chuqinnum/allnum;
                }
                tv_kaoqin_zongrenshu.setText("总共"+allNum+"人");
                initLineDatas(obj.getList_time());
                Log.i("===","==queqin="+queqin);
                Log.i("===","==chuqin="+chuqin);
                initPieDatas( chuqin, queqin );





            }
        });

    }
    private void initLineDatas(List<KaoqinObj.ListTimeBean> bean) {

         double tijiaolv=0.0;
         List<Double> datas = new ArrayList<>();
         List<String> description = new ArrayList<>();

         for (int i = bean.size()-1; i >= 0; i--) {
         tijiaolv=tijiaolv+bean.get(i).getTijiaolv();
         datas.add(bean.get(i).getTijiaolv());
         description.add(bean.get(i).getTime());
         }
         double pingjun= AndroidUtils.round(tijiaolv/7);
         tv_kaoqin_chuqinlv.setText(pingjun+"%");



        /*<com.sk.lgtea.linechart.LineChartView
                        android:paddingBottom="10dp"
                        android:id="@+id/lcv_kaoqin"
                        android:layout_width="match_parent"
                        android:layout_marginLeft="-30dp"
                        android:layout_height="0dp"
                        android:layout_weight="1"
                        />-->*/
        LineChartView lcv_kaoqin=new LineChartView(mContext);
        lcv_kaoqin.setPadding(0,0,0, PhoneUtils.dip2px(mContext,10));

        lcv_kaoqin.setDatas(datas, description);
        ll_kaoqin_lcv.removeAllViews();
        ll_kaoqin_lcv.addView(lcv_kaoqin);









    }


    @OnClick({R.id.iv_kaoqin_erweima_icon, R.id.tv_kaoqin_banji, R.id.tv_kaoqin_time, R.id.tv_kaoqin_tongji_details})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_kaoqin_erweima_icon:
                getQRCode();
                break;
            case R.id.tv_kaoqin_banji:
                onOptionPicker();

                break;
            case R.id.tv_kaoqin_time:

                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        time= DateUtils.dateToString(date,"yyyy-MM-dd");
                        tv_kaoqin_time.setText(time);
                        showLoading();
                        getAttendanceStatistic();

                    }
                }).setRange(1950, Calendar.getInstance().get(Calendar.YEAR)).setType(new boolean[]{true, true, true, false, false, false}).build();
                pvTime.show();
                break;
            case R.id.tv_kaoqin_tongji_details:
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.course_id,course_id);
                intent.putExtra(Constant.IParam.current_time,time);
                STActivity(intent,KaoqinjilvActivity.class);
                break;
        }
    }

    private void getQRCode() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getQrCode(map, new MyCallBack<ErweimaObj>(mContext) {
            @Override
            public void onSuccess(ErweimaObj obj) {
                String distribution_url = obj.getDistribution_url();
                Log.i("===","==="+distribution_url);
                if (!distribution_url.equals("")) {
                    showDialog(distribution_url);
                }else {
                    if (dialog!=null) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }

                    showMsg("暂无课程");
                }


            }
        });
    }

    private void showDialog(String distribution_url) {
        if (dialog == null) {
            dialog=new Dialog(mContext);
            View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_ercode_show, null);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//这句话，就是决定上面的那个黑框，也就是dialog的title。
            tv = dialogView.findViewById(R.id.tv);
            ImageView qrcode = dialogView.findViewById(R.id.img4_2);
            int   screenW = mContext.getResources().getDisplayMetrics().widthPixels;
            int wh= (int) (screenW/2);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)qrcode.getLayoutParams();
            params.width = wh;
            params.height = wh;
            qrcode.setLayoutParams(params);

            Glide.with(mContext).load(distribution_url).error(R.color.c_press).into(qrcode);

            dialog.setContentView(dialogView);


            //计时次数
            startJiShi( );
        }
        if(!dialog.isShowing()){
            dialog.show();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
//                    if (subscribe != null) {
//                        subscribe.unsubscribe();
//                    }
                }
            });
        }

        tv.setText(getRnd());
    }

    private void startJiShi(   ) {
        final long count = 10;

        Subscription  subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .take(10)//计时次数
                .map(integer -> count - integer)
                .compose(RxUtils.appSchedulers())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        if (dialog!=null&&dialog.isShowing()) {
                            getQRCode();
                            getAttendanceStatistic();
                        }
                        startJiShi();

                    }
                    @Override
                    public void onNext(Long aLong) {
                        Log.i("===","==aLong="+aLong);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                });
        addSubscription(subscribe);
    }


    public void onOptionPicker() {
        SinglePicker<String> picker = new SinglePicker<>(mContext, kechengName);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setShadowVisible(true);
        picker.setTextSize(20);
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
                tv_kaoqin_banji.setText(list.get(index).getCourse_name());
                //网络请求
                showLoading();
                getAttendanceStatistic();
            }
        });
        picker.show();
    }

}
