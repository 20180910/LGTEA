package com.sk.lgtea.module.home.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.MySubscriber;
import com.github.customview.MyTextView;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseFragment;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.activity.KaoqinActivity;
import com.sk.lgtea.module.home.activity.KechengbiaoActivity;
import com.sk.lgtea.module.home.activity.KejianActivity;
import com.sk.lgtea.module.home.activity.NewsActvity;
import com.sk.lgtea.module.home.activity.TongjiActivity;
import com.sk.lgtea.module.home.activity.ZiXunDetailActivity;
import com.sk.lgtea.module.home.event.NewsEvent;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.HomeRoastingChartObj;
import com.sk.lgtea.module.home.network.response.InformationListObj;
import com.sk.lgtea.module.home.network.response.TypeAssemBlageObj;
import com.sk.lgtea.module.home.network.response.UnreadNewsObj;
import com.sk.lgtea.tools.GlideLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.github.baseclass.rx.RxBusHelper.getRxBusEvent;

/**
 * Created by Administrator on 2017/12/4.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.iv_home_news)
    ImageView iv_home_news;
    @BindView(R.id.tv_home_xiaoxi_num)
    MyTextView tv_home_xiaoxi_num;
    @BindView(R.id.bn_home)
    Banner bn_home;
    @BindView(R.id.ll_home_kechengbiao)
    LinearLayout ll_home_kechengbiao;
    @BindView(R.id.ll_home_kaoqin)
    LinearLayout ll_home_kaoqin;
    @BindView(R.id.ll_home_kejian)
    LinearLayout ll_home_kejian;
    @BindView(R.id.ll_home_tongji)
    LinearLayout ll_home_tongji;
    @BindView(R.id.rv_home_xueyuan)
    RecyclerView rv_home_xueyuan;
    @BindView(R.id.rv_home_type)
    RecyclerView rv_home_type;
    private List<String> bannerList = new ArrayList<String>();
    LoadMoreAdapter adapter;
    BaseRecyclerAdapter typeAdapter;
    String news_num;
    ;

    @Override
    protected int getContentView() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {
        pcfl.disableWhenHorizontalMove(true);
        rv_home_xueyuan.setFocusable(false);


        typeAdapter=new BaseRecyclerAdapter<TypeAssemBlageObj>(mContext,R.layout.item_home_type) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, TypeAssemBlageObj obj) {
                ImageView iv_item_home_type_icon = holder.getImageView(R.id.iv_item_home_type_icon);
                TextView tv_item_home_type_name = holder.getTextView(R.id.tv_item_home_type_name);
                Glide.with(mContext).load(obj.getImg_url()).error(R.color.c_press).into(iv_item_home_type_icon);
                tv_item_home_type_name.setText(obj.getTitle());

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        switch (i){
                            case 0:
                                STActivity(KechengbiaoActivity.class);
                                break;
                            case 1:
                                STActivity(KaoqinActivity.class);
                                break;
                            case 2:
                                STActivity(KejianActivity.class);
                                break;

                            case 3:
                                STActivity(TongjiActivity.class);
                                break;
                        }

                    }
                });
            }
        };
        rv_home_type.setLayoutManager(new GridLayoutManager(mContext,4));
        rv_home_type.setNestedScrollingEnabled(false);
        rv_home_type.setAdapter(typeAdapter);


        adapter = new LoadMoreAdapter<InformationListObj>(mContext, R.layout.item_xueyuan_zixun, pageSize,nsv) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, InformationListObj bean) {
                ImageView iv_zixun = holder.getImageView(R.id.iv_zixun);
                TextView tv_zixun_content = holder.getTextView(R.id.tv_zixun_content);
                TextView tv_zixun_time = holder.getTextView(R.id.tv_zixun_time);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_zixun);
                tv_zixun_content.setText(bean.getTitle());
                tv_zixun_time.setText(bean.getAdd_time());


                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {

                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.information_id,bean.getInformation_id());
                        STActivity(intent,ZiXunDetailActivity.class);
                    }
                });

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_home_xueyuan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_home_xueyuan.setNestedScrollingEnabled(false);
        rv_home_xueyuan.addItemDecoration(getItemDivider());
        rv_home_xueyuan.setAdapter(adapter);
        nsv.scrollTo(0, 0);

    }

    @Override
    protected void initData() {
        showProgress();
        getRoastingChart();
        getTypeAssemBlage();
        getUnreadNews();
        getData(1,false);



    }



    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String>map=new HashMap<String,String>();
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getInformationList(map, new MyCallBack<List<InformationListObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<InformationListObj> obj) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj,true);
                }else{
                    pageNum=2;
                    adapter.setList(obj,true);
                }

            }
        });

    }
    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(NewsEvent.class, new MySubscriber<NewsEvent>() {
            @Override
            public void onMyNext(NewsEvent event) {
                if (event.is_check.equals("1")) {
                    showLoading();
                    getUnreadNews();
                }

            }
        });
    }

    private void getUnreadNews() {
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getUnreadNews(map, new MyCallBack<UnreadNewsObj>(mContext) {
            @Override
            public void onSuccess(UnreadNewsObj obj) {
                news_num=obj.getNews_num();
                if (news_num.equals("0")) {
                    tv_home_xiaoxi_num.setVisibility(View.GONE);
                }else {
                    tv_home_xiaoxi_num.setVisibility(View.VISIBLE);
                    tv_home_xiaoxi_num.setText(news_num);
                }





            }
        });

    }

    private void getTypeAssemBlage() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getTypeAssemBlage(map, new MyCallBack<List<TypeAssemBlageObj>>(mContext) {
            @Override
            public void onSuccess(List<TypeAssemBlageObj> obj) {
                typeAdapter.setList(obj,true);
            }
        });

    }

    private void getRoastingChart() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getRoastingChart(map, new MyCallBack<HomeRoastingChartObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(HomeRoastingChartObj obj) {
                bannerList = new ArrayList<String>();
                ;
                for (int i = 0; i < obj.getRoasting_list().size(); i++) {
                    bannerList.add(obj.getRoasting_list().get(i).getImg_url());

                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.height = (int) (PhoneUtils.getScreenWidth(mContext) / 2.25);
                layoutParams.width = PhoneUtils.getScreenWidth(mContext);

                bn_home.setLayoutParams(layoutParams);


                bn_home.setImages(bannerList);
                bn_home.setImageLoader(new GlideLoader());
                bn_home.start();
                bn_home.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
            }
        });

    }


    @OnClick({R.id.iv_home_news, R.id.ll_home_kechengbiao, R.id.ll_home_kaoqin, R.id.ll_home_kejian, R.id.ll_home_tongji})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_news:
                STActivity(NewsActvity.class);
                break;
            case R.id.ll_home_kechengbiao:
                STActivity(KechengbiaoActivity.class);
                break;
            case R.id.ll_home_kaoqin:
                STActivity(KaoqinActivity.class);
                break;
            case R.id.ll_home_kejian:
                STActivity(KejianActivity.class);
                break;
            case R.id.ll_home_tongji:
                STActivity(TongjiActivity.class);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }
    }
}
