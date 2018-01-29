package com.sk.lgtea.module.my.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.MyTextView;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.tools.AndroidUtils;
import com.sk.lgtea.tools.IntentUtils;
import com.sk.lgtea.tools.download.entity.AppInfo;
import com.sk.lgtea.tools.download.util.DownloadUtils;
import com.sk.lgtea.tools.download.util.FileUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyUploadActivity extends BaseActivity {
    @BindView(R.id.rv_my_download)
    RecyclerView rv_my_download;
    @BindView(R.id.tv_my_download_quanxuan)
    TextView tv_my_download_quanxuan;
    @BindView(R.id.tv_my_download_detele)
    MyTextView tv_my_download_detele;
    @BindView(R.id.ll_my_download_delete)
    LinearLayout ll_my_download_delete;
    @BindView(R.id.ll_my_download_bianji)
    LinearLayout ll_my_download_bianji;

    private boolean isEdit;
    BaseRecyclerAdapter adapter;
    int  num=0;

    @Override
    protected int getContentView() {
        setAppTitle("我的下载");
        setAppRightTitle("管理");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_my_upload;
    }

    @Override
    protected void initView() {
        adapter = new BaseRecyclerAdapter<AppInfo>(mContext, R.layout.item_my_download) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, AppInfo bean) {
                CheckBox ch_item_my_download = (CheckBox) holder.getView(R.id.ch_item_my_download);
                ImageView iv_item_my_download_icon = holder.getImageView(R.id.iv_item_my_download_icon);
                TextView tv_item_my_download_name = holder.getTextView(R.id.tv_item_my_download_name);
                TextView tv_item_my_download_type = holder.getTextView(R.id.tv_item_my_download_type);
                TextView tv_item_my_download_size = holder.getTextView(R.id.tv_item_my_download_size);
                Log.d("=======", "=====bean.getImage()=" + bean.getImage());
                if (bean.getImage().equals("")) {
                    iv_item_my_download_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.wenjian));

                }else {
                    Glide.with(mContext).load(bean.getImage()).error(R.color.c_press).into(iv_item_my_download_icon);
                }


                tv_item_my_download_name.setText(bean.getTitle());
                if (bean.getHouZhui().equals("mp4")||bean.getHouZhui().equals("MP4")) {
                    tv_item_my_download_type.setText("视频");
                } else {
                    tv_item_my_download_type.setText(bean.getHouZhui());
                }

                tv_item_my_download_size.setText(formetFileSize(Double.parseDouble(bean.getFileSize())));
//
                if (isEdit) {
                    ch_item_my_download.setVisibility(View.VISIBLE);
                } else {
                    ch_item_my_download.setVisibility(View.GONE);
                }
                ch_item_my_download.setChecked(bean.isSelect());
                ch_item_my_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ((AppInfo) adapter.getList().get(i)).setSelect(ch_item_my_download.isChecked());
                        if (ch_item_my_download.isChecked()) {
                            num++;
                        }else {
                            num--;
                        }
                        tv_my_download_detele.setText(num+"");

                    }
                });

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        if (!isEdit) {
                            String path = FileUtils.getDownloadDir() + "/" +bean.getFileName();
                            Log.i("===","===path="+path);
                            IntentUtils.openFileIntent(mContext, path);

                        }


                    }
                });


            }
        };
        rv_my_download.setLayoutManager(new LinearLayoutManager(mContext));
        rv_my_download.setNestedScrollingEnabled(false);
        rv_my_download.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        showProgress();
//        adapter.setList(downloadCompleteFile,true);
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        RXStart(new IOCallBack<List<AppInfo>>() {
            @Override
            public void call(Subscriber<? super List<AppInfo>> subscriber) {
                subscriber.onNext(DownloadUtils.getDownloadCompleteFile(mContext));
                subscriber.onCompleted();
            }

            @Override
            public void onMyNext(List<AppInfo> appInfos) {
                adapter.setList(appInfos, true);
                pl_load.showContent();
            }

            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);
                pl_load.showErrorText();
            }
        });

    }

    @OnClick({R.id.app_right_tv, R.id.tv_my_download_quanxuan,R.id.ll_my_download_delete})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.app_right_tv:
                ArrayList<AppInfo> arrylist = (ArrayList<AppInfo>) adapter.getList();
                    isEdit = !isEdit;
                    if (isEdit) {
                        setAppRightTitle("完成");
                        ll_my_download_bianji.setVisibility(View.VISIBLE);
                    } else {
                        setAppRightTitle("编辑");
                        ll_my_download_bianji.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
                break;

            case R.id.tv_my_download_quanxuan:

                ArrayList<AppInfo> allList = (ArrayList<AppInfo>) adapter.getList();
                for (int i = 0; i < allList.size(); i++) {
                    allList.get(i).setSelect(true);
                }
                num=allList.size();
                adapter.notifyDataSetChanged();
                tv_my_download_detele.setText(num+"");

                break;
            case R.id.ll_my_download_delete:

                ArrayList<AppInfo> list = (ArrayList<AppInfo>) adapter.getList();
                Log.i("===","===list======"+list.size());

                ArrayList<String>path_list = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).isSelect() == true){
                        Log.i("===","=list=="+list.get(i).getFileName());
                        path_list.add(list.get(i).getFileName());
                    }
                    deleteDownloadFile(path_list);
                }



                break;
        }
    }

    public void deleteDownloadFile(List<String> path) {
        showLoading();
        RXStart(new IOCallBack<List<AppInfo>>() {
            @Override
            public void call(Subscriber<? super List<AppInfo>> subscriber) {
                for (int i = 0; i < path.size(); i++) {
                    FileUtils.deleteFile(path.get(i));
                }
                List<AppInfo> downloadCompleteFile = DownloadUtils.getDownloadCompleteFile(mContext);
                subscriber.onNext(downloadCompleteFile);
                subscriber.onCompleted();
            }

            @Override
            public void onMyNext(List<AppInfo> list) {
                dismissLoading();

                adapter.setList(list, true);
                num=0;
                tv_my_download_detele.setText(num+"");
            }

            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);
                dismissLoading();
            }
        });
    }

    public void deleteDownloadFile(String path) {
        List<String> list = new ArrayList<>();
        list.add(path);
        deleteDownloadFile(list);
    }

    public String formetFileSize(Double fileS) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format(AndroidUtils.chuFa(fileS, 1024)) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format(AndroidUtils.chuFa(fileS, 1048576)) + "M";
        } else {
            fileSizeString = df.format(AndroidUtils.chuFa(fileS, 1073741824)) + "G";
        }
        return fileSizeString;
    }


}
