package com.sk.lgtea.module.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.home.Constant;
import com.sk.lgtea.module.home.network.ApiRequest;
import com.sk.lgtea.module.home.network.response.InformationMoreObj;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/5.
 */

public class ZiXunDetailActivity extends BaseActivity {
    @BindView(R.id.wv_zixun_detail)
    WebView wv_zixun_detail;
    String information_id;
    @BindView(R.id.tv_zixun_detail_title)
    TextView tv_zixun_detail_title;
    @BindView(R.id.tv_zixun_detail_author)
    TextView tv_zixun_detail_author;
    @BindView(R.id.tv_zixun_detail_time)
    TextView tv_zixun_detail_time;
    @Override
    protected int getContentView() {
        setAppTitle("资讯详情");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_zi_xun;
    }

    @Override
    protected void initView() {
        initWebView();
        getValue();

    }
    private void getValue() {
        information_id = getIntent().getStringExtra(Constant.IParam.information_id);
    }


    @Override
    protected void initData() {
        showProgress();
        getInformationMore();

    }

    private void getInformationMore() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("information_id",information_id);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getInformationMore(map, new MyCallBack<InformationMoreObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(InformationMoreObj obj) {
                tv_zixun_detail_title.setText(obj.getTitle());
                tv_zixun_detail_author.setText(obj.getAuthor());
                tv_zixun_detail_time.setText(obj.getAdd_time());
                showWebView(obj.getContent());


            }
        });

    }

    private void initWebView() {
        WebSettings wb = wv_zixun_detail.getSettings();

        wb.setJavaScriptEnabled(true);
//设置自适应
        wb.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL.SINGLE_COLUMN);

        wb.setDefaultTextEncodingName("UTF-8");

        wb.setAppCacheEnabled(true);

        wb.setCacheMode(WebSettings.LOAD_DEFAULT);

        wv_zixun_detail.getSettings().setDefaultTextEncodingName("utf-8");// 避免中文乱码

        wv_zixun_detail.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        WebSettings settings = wv_zixun_detail.getSettings();

        settings.setJavaScriptEnabled(true);

        settings.setNeedInitialFocus(false);

        settings.setSupportZoom(true);

        settings.setLoadWithOverviewMode(true);//适应屏幕

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        settings.setLoadsImagesAutomatically(true);//自动加载图片

        wv_zixun_detail.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
    private void showWebView(String content) {
        Document doc_Dis = Jsoup.parse(content);
        Elements ele_Img = doc_Dis.getElementsByTag("shareImg");
        if (ele_Img.size() != 0) {
            for (Element e_Img : ele_Img) {
                e_Img.attr("width", "100%");
                e_Img.attr("height", "auto");
            }
        }
        String newHtmlContent = doc_Dis.toString();
        wv_zixun_detail.loadDataWithBaseURL(null, newHtmlContent, "text/html", "utf-8", null);
    }


    @Override
    protected void onViewClick(View v) {

    }
}