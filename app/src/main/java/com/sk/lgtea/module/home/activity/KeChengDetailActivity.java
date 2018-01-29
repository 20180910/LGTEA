package com.sk.lgtea.module.home.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.rx.MySubscriber;
import com.github.customview.MyTextView;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.bean.KeChengDetailBean;
import com.sk.lgtea.module.home.event.DownLoadSuccessEvent;
import com.sk.lgtea.module.home.event.ScrollViewEvent;
import com.sk.lgtea.module.home.event.StudyKejianEvent;
import com.sk.lgtea.module.home.fragment.KeChengDetailFragment;
import com.sk.lgtea.module.home.fragment.KeChengEvaluateFragment;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.StudyDetailObj;
import com.sk.lgtea.module.taolun.network.response.DianzanObj;
import com.sk.lgtea.tools.download.entity.AppInfo;
import com.sk.lgtea.tools.download.util.DownloadUtils;
import com.sk.lgtea.video.LandLayoutVideo;
import com.sk.lgtea.video.SampleListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/5.
 */

public class KeChengDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.ctl_study_kecheng)
    CommonTabLayout ctl_study_kecheng;
    @BindView(R.id.llv_kechengdetail)
    LandLayoutVideo llv_kechengdetail;
    @BindView(R.id.iv_kecheng_detail_back)
    ImageView iv_kecheng_detail_back;
    @BindView(R.id.iv_kecheng_detail_back_pdf)
    ImageView iv_kecheng_detail_back_pdf;
    @BindView(R.id.tv_kecheng_detail_bg)
    TextView tv_kecheng_detail_bg;
    @BindView(R.id.tv_kecheng_detail_look)
    MyTextView tv_kecheng_detail_look;
    @BindView(R.id.rl_kecheng_detail_video)
    RelativeLayout rl_kecheng_detail_video;
    @BindView(R.id.rl_kecheng_detail_pdf)
    RelativeLayout rl_kecheng_detail_pdf;
    @BindView(R.id.iv_kecheng_detail_pdf)
    ImageView iv_kecheng_detail_pdf;
    @BindView(R.id.vp_kecheng_detail)
    ViewPager vp_kecheng_detail;
    @BindView(R.id.ll_kecheng_share)
    LinearLayout ll_kecheng_share;
    @BindView(R.id.ll_kecheng_download)
    LinearLayout ll_kecheng_download;
    @BindView(R.id.iv_kecheng_collection)
    ImageView iv_kecheng_collection;
    @BindView(R.id.ll_kecheng_collection)
    LinearLayout ll_kecheng_collection;
    @BindView(R.id.iv_kecheng_dianzan)
    ImageView iv_kecheng_dianzan;
    @BindView(R.id.ll_kecheng_dianzan)
    LinearLayout ll_kecheng_dianzan;
    @BindView(R.id.tv_kecheng_detail_title)
    TextView tv_kecheng_detail_title;

    private ImageView[] mImageViews;
    List<String> imgIdArray = new ArrayList<>();
    String type, courseware_id, image_url;
    BaseRecyclerAdapter adapter;

    private boolean isPlay;
    private boolean isPause;
    String videoUrl = "",pdfUrl="",imgUrl="",attachmentUrl;

    private OrientationUtils orientationUtils;

    ArrayList<CustomTabEntity> list = new ArrayList<>();
    ArrayList<Fragment> fragList = new ArrayList<>();
    private KeChengEvaluateFragment evaluateFragment;
    private KeChengDetailFragment detailFragment;
    private String tagId,title;
    private StudyDetailObj studyDetailObj;


    @Override
    protected int getContentView() {

        return R.layout.act_kecheng_detail;
    }

    @Override
    protected void initView() {

        getValue();
        getMoreEvaluateData();
        //                    getVideo(videoUrl);


//        getRcv();
        list.add(new KeChengDetailBean("详情"));
        list.add(new KeChengDetailBean("评论"));

        detailFragment = KeChengDetailFragment.newInstance();
        evaluateFragment = KeChengEvaluateFragment.newInstance();
        fragList.add(detailFragment);
        fragList.add(evaluateFragment);
        ctl_study_kecheng.setTabData(list, this, R.id.fl_study_kecheng, fragList);

        ll_kecheng_download.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {

                startDownload();
            }
        });
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("courseware_id", courseware_id);
        map.put("pagesize", pageSize + "");
        map.put("page", page + "");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getCourseWareDetail(map, new MyCallBack<StudyDetailObj>(mContext, pcfl, pl_load) {

            @Override
            public void onSuccess(StudyDetailObj obj) {
                studyDetailObj = obj;
                tagId = obj.getCourseware_id();
                tv_kecheng_detail_title.setText(obj.getTitle());
                title=obj.getTitle();
                detailFragment.setImg(obj.getKeynote_speaker_image());
                detailFragment.setIndividuality_signature(obj.getIndividuality_signature());
                detailFragment.setName(obj.getKeynote_speaker());
                detailFragment.setCourseware(obj.getCourseware_introduction());
                evaluateFragment.setComment_count(obj.getComment_count());
                evaluateFragment.setCourseware_id(obj.getCourseware_id());
                attachmentUrl = obj.getAttachment();

                if (obj.getIs_collect().equals("0")) {
                    iv_kecheng_collection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.study_collnect_normal));
                } else {
                    iv_kecheng_collection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.study_collnect_select));
                }
                if (obj.getIs_thumbup().equals("0")) {
                    iv_kecheng_dianzan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.study_zan));
                } else {
                    iv_kecheng_dianzan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dianzan_select));
                }
                if (isLoad) {
                    pageNum++;
                    evaluateFragment.addData(obj.getComment_list());
                } else {
                    pageNum = 2;
                    evaluateFragment.setData(obj.getComment_list());
                }


            }
        });

    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(StudyKejianEvent.class, new MySubscriber<StudyKejianEvent>() {
            @Override
            public void onMyNext(StudyKejianEvent event) {
                if (event.type.equals(Config.comment)) {
                    showLoading();
                    getData(1, false);
                }
            }
        });
        getRxBusEvent(ScrollViewEvent.class, new MySubscriber() {
            @Override
            public void onMyNext(Object o) {
                nsv.scrollTo(0, 0);
            }
        });
        getRxBusEvent(DownLoadSuccessEvent.class, new MySubscriber<DownLoadSuccessEvent>() {
            @Override
            public void onMyNext(DownLoadSuccessEvent event) {
                if (event.type.equals("success")) {
                    //courseware_id
                    getDownloadRecord();
                    showMsg("下载已完成。请到个人中心->我的下载中查看。");
                }else if (event.type.equals("yes")){
                    showMsg("已下载。请到个人中心->我的下载中查看。");

                }else if (event.type.equals("no")){
                    showMsg("下载中...");

                }



            }
        });
    }

    private void getDownloadRecord() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("courseware_id",courseware_id);
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getDownloadRecord(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {

            }
        });

    }

    private void getMoreEvaluateData() {
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i("===", "===" + scrollY);
                canRefresh = scrollY == 0 ? true : false;
                if (evaluateFragment == null || evaluateFragment.isHidden()) {
                    return;
                }
                if (!v.canScrollVertically(1)) {
                    loadMore();
                }
            }
        });
    }

    private void getValue() {
        type = getIntent().getStringExtra(Constant.IParam.type);
        image_url = getIntent().getStringExtra(Constant.IParam.image_url);
        Log.d("=========", "==image_url==" + image_url);
        courseware_id = getIntent().getStringExtra(Constant.IParam.courseware_id);

        if (type.equals("0")) {
            videoUrl = getIntent().getStringExtra(Constant.IParam.video_pdf);
            getVideo(videoUrl,image_url);
            rl_kecheng_detail_video.setVisibility(View.VISIBLE);
            rl_kecheng_detail_pdf.setVisibility(View.GONE);

        } else if (type.equals("1")){
            pdfUrl=getIntent().getStringExtra(Constant.IParam.video_pdf);
            rl_kecheng_detail_video.setVisibility(View.GONE);
            rl_kecheng_detail_pdf.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(image_url).error(R.color.c_press).into(iv_kecheng_detail_pdf);


        }else {
            imgUrl=getIntent().getStringExtra(Constant.IParam.video_pdf);
            rl_kecheng_detail_video.setVisibility(View.GONE);
            rl_kecheng_detail_pdf.setVisibility(View.VISIBLE);
            tv_kecheng_detail_bg.setVisibility(View.GONE);
            tv_kecheng_detail_look.setVisibility(View.GONE);
            Glide.with(mContext).load(imgUrl).error(R.color.c_press).into(iv_kecheng_detail_pdf);


        }

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
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(this);


            mImageViews[i] = imageView;
            Glide.with(mContext).
                    load(imgIdArray.get(i)).
                    error(R.drawable.banner).
                    into(imageView);
        }

        //设置Adapter
        vp_kecheng_detail.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        vp_kecheng_detail.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
//        vp_kecheng_detail.setCurrentItem((mImageViews90) );


    }



    /**
     * @author xiaanming
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
            ((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            try {
                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
            } catch (Exception e) {
                //handler something
            }
            return mImageViews[position % mImageViews.length];
        }
    }


    private void getVideo(String url,String imgUrl) {

        //断网自动重新链接，url前接上ijkhttphook:
//        String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(imgUrl).error(R.color.c_press).into(imageView);
//        imageView.setImageResource(R.drawable.banner);
        resolveNormalVideoUI();
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, llv_kechengdetail);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(imageView)

                .setIsTouchWiget(true)//是否可以滑动界面改变进度，声音等 默认true
                .setRotateViewAuto(false)//是否开启自动旋转
                .setLockLand(false)//一全屏就锁屏横屏，默认false竖屏，可配合setRotateViewAuto使用
                .setShowFullAnimation(false)//全屏动画
                .setNeedLockFull(true)//是否需要全屏锁定屏幕功能
                .setSeekRatio(1)//调整触摸滑动快进的比例
                .setUrl(url)
                .setCacheWithPlay(false)//是否边缓存，m3u8等无效
                .setVideoTitle("")
                .setStandardVideoAllCallBack(new SampleListener() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                    }

                    @Override
                    public void onClickStartError(String url, Object... objects) {
                        super.onClickStartError(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);

                        }
                    }
                }).build(llv_kechengdetail);

        llv_kechengdetail.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                llv_kechengdetail.startWindowFullscreen(KeChengDetailActivity.this, true, true);
            }
        });
    }

    private void resolveNormalVideoUI() {
        //增加title
        llv_kechengdetail.getTitleTextView().setVisibility(View.GONE);
        llv_kechengdetail.getBackButton().setVisibility(View.GONE);
        llv_kechengdetail.setShrinkImageRes(R.drawable.icon_suoxiao);
        llv_kechengdetail.setEnlargeImageRes(R.drawable.icon_fangda);
    }

    private GSYVideoPlayer getCurPlay() {
        if (llv_kechengdetail.getFullWindowPlayer() != null) {
            return llv_kechengdetail.getFullWindowPlayer();
        }
        return llv_kechengdetail;
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            getCurPlay().release();
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            llv_kechengdetail.onConfigurationChanged(this, newConfig, orientationUtils);
        }
    }


    @OnClick({R.id.iv_kecheng_detail_back,
            R.id.iv_kecheng_detail_back_pdf,
            R.id.tv_kecheng_detail_look,
            R.id.ll_kecheng_share,
            R.id.ll_kecheng_collection,
            R.id.ll_kecheng_dianzan})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_kecheng_detail_back:
                onBackPressed();
                break;
            case R.id.iv_kecheng_detail_back_pdf:
                finish();
                break;
            case R.id.tv_kecheng_detail_look:
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.video_pdf,pdfUrl);
                intent.putExtra(Constant.IParam.title,title);
//                STActivity(intent,PDFActivity.class);
                STActivity(intent,PDF2Activity.class);

//                vp_kecheng_detail.setVisibility(View.VISIBLE);
//                pdf_kecheng_detail.setVisibility(View.VISIBLE);
//                iv_kecheng_detail_pdf.setVisibility(View.GONE);
//                tv_kecheng_detail_look.setVisibility(View.GONE);
//                tv_kecheng_detail_bg.setVisibility(View.GONE);
                break;
            case R.id.ll_kecheng_share:
//                Application.
                showFenXiang();

                break;
            case R.id.ll_kecheng_collection:
                getCollectMerchant();

                break;
            case R.id.ll_kecheng_dianzan:
                getthumbupForum();

                break;
        }
    }


    BottomSheetDialog fenXiangDialog;
    /*public void showFenXiang(){
        if (fenXiangDialog == null) {
            View sexView= LayoutInflater.from(mContext).inflate(R.layout.popu_fen_xiang,null);
            sexView.findViewById(R.id.iv_yaoqing_wx).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {

                    fenXiangDialog.dismiss();

                }
            });
            sexView.findViewById(R.id.iv_yaoqing_friend).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    fenXiangDialog.dismiss();

                }
            });
            sexView.findViewById(R.id.iv_yaoqing_qq).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    fenXiangDialog.dismiss();
                }
            });
            sexView.findViewById(R.id.iv_yaoqing_qzone).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    fenXiangDialog.dismiss();
                }
            });
            sexView.findViewById(R.id.tv_fenxiang_cancle).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    fenXiangDialog.dismiss();
                }
            });
            fenXiangDialog=new BottomSheetDialog(mContext);
            fenXiangDialog.setCanceledOnTouchOutside(true);
            fenXiangDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            fenXiangDialog.setContentView(sexView);
        }
        fenXiangDialog.show();
    }


*/






    private void startDownload() {

        if (TextUtils.isEmpty(attachmentUrl)) {
            showMsg("暂无附件下载");
            return;
        }
//        attachmentUrl.


        AppInfo info = new AppInfo(studyDetailObj.getCourseware_id(), studyDetailObj.getTitle(), studyDetailObj.getTitle(), image_url, studyDetailObj.getAttachment());
        showLoading();
        RXStart(new IOCallBack<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(DownloadUtils.isExistFile(info));
                subscriber.onCompleted();
            }

            @Override
            public void onMyNext(Boolean isExist) {
                dismissLoading();
                DownloadUtils.startDownload(isExist, mContext, info);
            }
        });
    }

    private void getCollectMerchant() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("courseware_id", courseware_id);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getCollectMerchant(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                if (obj.getIs_collect().equals("0")) {
                    iv_kecheng_collection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.study_collnect_normal));
                } else {
                    iv_kecheng_collection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.study_collnect_select));
                }

            }
        });


    }

    private void getthumbupForum() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("forum_courseware_id", courseware_id);
        map.put("type", 2 + "");
        map.put("sign", GetSign.getSign(map));
       com.sk.lgtea.module.taolun.network.ApiRequest.getthumbupForum(map, new MyCallBack<DianzanObj>(mContext) {
            @Override
            public void onSuccess(DianzanObj obj) {
                if (obj.getIs_thumbup() == 0) {
                    iv_kecheng_dianzan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.study_zan));
                } else {
                    iv_kecheng_dianzan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dianzan_select));
                }

            }
        });

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int indext = position + 1;


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
