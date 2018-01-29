package com.sk.lgtea.module.home.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.github.androidtools.SPUtils;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyRadioButton;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.broadcast.MyOperationBro;
import com.sk.lgtea.module.home.fragment.HomeFragment;
import com.sk.lgtea.module.my.fragment.MyFragment;
import com.sk.lgtea.module.my.network.response.BanbengengxinObj;
import com.sk.lgtea.module.taolun.fragment.TaoLunFragment;
import com.sk.lgtea.network.ApiRequest;
import com.sk.lgtea.network.response.FenXiangObj;
import com.sk.lgtea.tools.SDFileHelper;
import com.sk.lgtea.tools.download.entity.AppInfo;
import com.sk.lgtea.tools.download.service.DownloadService;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    HomeFragment homeFragment;
    TaoLunFragment taoLunFragment;
    MyFragment myFragment;

    @BindView(R.id.layout_main_content)
    FrameLayout layout_main_content;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.rb_home)
    MyRadioButton rb_home;

    @BindView(R.id.rb_home_taolun)
    MyRadioButton rb_home_taolun;

    @BindView(R.id.rb_home_my)
    MyRadioButton rb_home_my;

    @BindView(R.id.rg_main)
    RadioGroup rg_main;
    private MyRadioButton selectButton;
    private LocalBroadcastManager localBroadcastManager;
    private MyOperationBro myOperationBro;
    SDFileHelper helper;


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
      helper = new SDFileHelper(this);

        setBroadcast();
        selectButton = rb_home;
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, homeFragment).commitAllowingStateLoss();
    }

    @Override
    protected void initData() {
        //检查版本更新
        getVersionUpdate();
//        getShareInformations();

    }



    private void getShareInformations() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getShareInformations(map, new MyCallBack<FenXiangObj>(mContext) {
            @Override
            public void onSuccess(FenXiangObj obj) {
                SPUtils.setPrefString(mContext, Config.share_link, obj.getShare_link());
                SPUtils.setPrefString(mContext, Config.content, obj.getContent());
                SPUtils.setPrefString(mContext, Config.shareImgUrl, obj.getImg());
//                downloadPicFromNet();

//                getShareImg(obj.getImg());
                helper.savePicture("logo.jpg",obj.getImg());
            }


        });

    }


    private void getVersionUpdate() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getVersionUpdate(map, new MyCallBack<BanbengengxinObj>(mContext) {
            @Override
            public void onSuccess(BanbengengxinObj obj) {
                dismissLoading();
                Log.i("===", "===getAppVersionCode=" + getAppVersionCode());

                if (obj.getAndroid_version() > getAppVersionCode()) {

                    MyDialog.Builder mDialog = new MyDialog.Builder(mContext);
                    mDialog.setMessage("检测到app有新版本是否更新?");
                    mDialog.setNegativeButton((dialog, which) -> dialog.dismiss());
                    mDialog.setPositiveButton((dialog, which) -> {
                        dialog.dismiss();
                        AppInfo info = new AppInfo();
                        info.setUrl(obj.getAndroid_vs_url());
                        info.setHouZhui("apk");
                        info.setFileName("lgtea");
                        info.setTitle("上理传播E学堂");
                        info.setImage("lgtea上理传播E学堂");
                        info.setId(obj.getAndroid_version() + "");
                        downloadApp(info);
                    });
                    mDialog.create().show();

                } else {


                }


            }
        });


    }

    private void downloadApp(AppInfo info) {
        showMsg("上理传播E学堂正在下载中...");
        DownloadService.intentDownload(mContext, info);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (Config.exitAPP.equals(intent.getAction())) {
            finish();
        }
    }

    @OnClick({R.id.rb_home, R.id.rb_home_taolun, R.id.rb_home_my})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                selectHome();
                break;
            case R.id.rb_home_taolun:
//                if ("0".equals(getUserId())) {
//                    selectButton.setChecked(true);
//                    STActivity(LoginActivity.class);
//                    return;
//                }
//                status_bar.setBackgroundColor(getResources().getColor(R.color.white));
                selectOrder();
                break;
            case R.id.rb_home_my:
//                if ("0".equals(getUserId())) {
//                    selectButton.setChecked(true);
//                    STActivity(LoginActivity.class);
//                    return;
//                }
//                status_bar.setBackgroundColor(getResources().getColor(R.color.home_green));
                selectMy();
                break;
        }
    }

    private void selectMy() {
        selectButton = rb_home_my;
        if (myFragment == null) {
            myFragment = new MyFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, myFragment).commitAllowingStateLoss();
        } else {
            showFragment(myFragment);
        }
        hideFragment(homeFragment);
        hideFragment(taoLunFragment);
    }

    private void selectOrder() {
        selectButton = rb_home_taolun;
        if (taoLunFragment == null) {
            taoLunFragment = new TaoLunFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, taoLunFragment).commitAllowingStateLoss();
        } else {
            showFragment(taoLunFragment);
        }
        hideFragment(myFragment);
        hideFragment(homeFragment);
    }


    private void selectHome() {
        selectButton = rb_home;
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, homeFragment).commitAllowingStateLoss();
        } else {
            showFragment(homeFragment);
        }
        hideFragment(taoLunFragment);
        hideFragment(myFragment);
    }

    private void setBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        myOperationBro = new MyOperationBro(new MyOperationBro.LoginBroInter() {
            @Override
            public void loginSuccess() {
                selectMy();
                selectButton.setChecked(true);

//                registerHuanXin();
            }

            @Override
            public void exitLogin() {
                selectHome();
                selectButton.setChecked(true);
                myFragment = null;
            }
        });
        localBroadcastManager.registerReceiver(myOperationBro, new IntentFilter(Config.Bro.operation));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(myOperationBro);
        }
    }


    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
