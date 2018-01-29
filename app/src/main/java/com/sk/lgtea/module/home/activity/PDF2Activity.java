package com.sk.lgtea.module.home.activity;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.baseclass.rx.IOCallBack;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.module.home.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/26.
 */

public class PDF2Activity extends BaseActivity {
    @BindView(R.id.pdf_view)
    PDFView pdf_view;

    String pdfUrl,title;
    int  numSize=0,num=0;

    @Override
    protected int getContentView() {
        setBackIcon(R.drawable.back_white);

        return R.layout.act_pdf2;
    }

    @Override
    protected void initView() {
        pdfUrl = getIntent().getStringExtra(Constant.IParam.video_pdf);
        title=getIntent().getStringExtra(Constant.IParam.title);
        setAppTitle(title);

    }

    @Override
    protected void initData() {
      showProgress();
//        showPDF();
        getData(1,false);

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        showPDF();

    }

    private void showPDF() {


        RXStart(new IOCallBack<InputStream>() {
            @Override
            public void call(Subscriber<? super InputStream> subscriber) {
                try {
                    Log.i("===","==="+pdfUrl);
                    URL url = new URL(pdfUrl);
                    HttpURLConnection connection = (HttpURLConnection)
                            url.openConnection();
                    connection.setRequestMethod("GET");//试过POST 可能报错
                    connection.setDoInput(true);
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);
                    //实现连接
                    connection.connect();
                    System.out.println("connection.getResponseCode()=" + connection.getResponseCode());
                    if (connection.getResponseCode() == 200) {
                        InputStream is = connection.getInputStream();
                        //这里给过去就行了

                        Log.i("===","==is="+is);
                        subscriber.onNext(is);
                        subscriber.onCompleted();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("===","==IOException="+e.getMessage());
                }


            }

            @Override
            public void onMyNext(InputStream inputStream) {
                Log.i("===","==inputStream="+inputStream);
                pl_load.showContent();
                showLoading();
                pdf_view.fromStream( inputStream)
                        .enableSwipe(true) // allows to block changing pages using swipe
                        .swipeHorizontal(false)
                        .enableDoubletap(false)
                        .defaultPage(0)
                        // 允许在当前页面上绘制一些内容，通常在屏幕中间可见。
                        .onDraw(new OnDrawListener() {
                            @Override
                            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                            }
                        })
                        // 允许在每一页上单独绘制一个页面。只调用可见页面
                        .onLoad(new OnLoadCompleteListener() {
                            @Override
                            public void loadComplete(int nbPages) {
                                Log.i("===","=onLoad==nbPages="+nbPages);
//                                numSize=nbPages;


                            }
                        }) // 加载文档并开始呈现后调用。
                        .onPageChange(new OnPageChangeListener() {
                            @Override
                            public void onPageChanged(int page, int pageCount) {
                                Log.i("===","=onPageChanged==page="+page+"===pageCount=="+pageCount);
//                                num=page+1;
//                                setAppTitle(num+"/"+numSize+"页");
                                dismissLoading();
                            }
                        })
                        .onPageScroll(new OnPageScrollListener() {
                            @Override
                            public void onPageScrolled(int page, float positionOffset) {
                                Log.i("===","=onPageScrolled==page="+page+"===pageCount=="+positionOffset);

                            }
                        })
                        .onError(new OnErrorListener() {
                            @Override
                            public void onError(Throwable t) {
                                Log.i("===","==onError="+t.getMessage());

                            }
                        })
                        .enableAnnotationRendering(false) // render注释（such as comments，颜色或形式）
                        .password(null)
                        .scrollHandle(new ScrollHandle() {
                            @Override
                            public void setScroll(float position) {
                                Log.i("===","==setScroll=position="+position);


                            }

                            @Override
                            public void setupLayout(PDFView pdfView) {

                            }

                            @Override
                            public void destroyLayout() {

                            }

                            @Override
                            public void setPageNum(int pageNum) {
                                Log.i("===","==setPageNum=pageNum="+pageNum);

                            }

                            @Override
                            public boolean shown() {
                                return false;
                            }

                            @Override
                            public void show() {

                            }

                            @Override
                            public void hide() {

                            }

                            @Override
                            public void hideDelayed() {


                            }
                        })
                        .load();



            }
            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);
                pl_load.showErrorText();
            }


        });





    }

    @Override
    protected void onViewClick(View v) {

    }
}
