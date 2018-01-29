package com.sk.lgtea.module.taolun.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.rx.MySubscriber;
import com.github.baseclass.rx.RxBus;
import com.github.customview.MyTextView;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.network.request.UploadImgBody;
import com.sk.lgtea.module.taolun.adapter.AddImgAdapter;
import com.sk.lgtea.module.taolun.event.AddImgEvent;
import com.sk.lgtea.module.taolun.event.FatieEvent;
import com.sk.lgtea.module.taolun.network.request.FatieBody;
import com.sk.lgtea.network.ApiRequest;
import com.sk.lgtea.tools.BitmapUtils;
import com.sk.lgtea.tools.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import top.zibin.luban.Luban;

/**
 * Created by Administrator on 2017/12/11.
 */

public class FatieActivity extends BaseActivity {
    @BindView(R.id.et_fatie_title)
    EditText et_fatie_title;
    @BindView(R.id.et_fatie_content)
    EditText et_fatie_content;
    @BindView(R.id.rv_fatie_img)
    RecyclerView rv_fatie_img;
    @BindView(R.id.tv_fatie_fabu)
    MyTextView tv_fatie_fabu;
    AddImgAdapter addImgAdapter;
    private BottomSheetDialog selectPhotoDialog;
    private int selectImgIndex;
    String title,content;

    @Override
    protected int getContentView() {
        setAppTitle("发帖");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_fatie;
    }

    @Override
    protected void initView() {
        addImgAdapter=new AddImgAdapter(mContext,R.layout.item_fatie_addimg);

        addImgAdapter.setList(new ArrayList());
        rv_fatie_img.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        rv_fatie_img.setAdapter(addImgAdapter);

    }
    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(AddImgEvent.class, new MySubscriber<AddImgEvent>() {
            @Override
            public void onMyNext(AddImgEvent event) {
                selectImgIndex = event.selectImgIndex;
                showSelectPhotoDialog();
            }
        });
    }
    private void showSelectPhotoDialog() {
        if (selectPhotoDialog == null) {
            View sexView= LayoutInflater.from(mContext).inflate(R.layout.popu_select_photo,null);
            sexView.findViewById(R.id.tv_select_photo).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                    selectPhoto();
                }
            });
            sexView.findViewById(R.id.tv_take_photo).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                    takePhoto();
                }
            });
            sexView.findViewById(R.id.tv_photo_cancle).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                }
            });
            selectPhotoDialog = new BottomSheetDialog(mContext);
            selectPhotoDialog.setCanceledOnTouchOutside(true);
            selectPhotoDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            selectPhotoDialog.setContentView(sexView);
        }
        selectPhotoDialog.show();
    }
    //选择相册
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3000);
    }
    private String path = Environment.getExternalStorageDirectory() +
            File.separator + Environment.DIRECTORY_DCIM + File.separator;
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "IMG_" + dateFormat.format(date);
    }
    Uri photoUri;
    private String imgSaveName="";
    //拍照
    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String fileName = getPhotoFileName() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imgSaveName = path + fileName;
                photoUri = Uri.fromFile(new File(imgSaveName));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, 2000);
            }
        }
    }
    private void uploadImg() {
        showLoading();
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String newPath= ImageUtils.filePath;
                ImageUtils.makeFolder(newPath);
                FileInputStream fis = null;
                try {
                    List<File> files = Luban.with(mContext).load(imgSaveName).get();
                    String imgStr = BitmapUtils.bitmapToString2(files.get(0));
                    subscriber.onNext(imgStr);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            @Override
            public void onMyNext(String baseImg) {
                UploadImgBody item=new UploadImgBody();
                item.setFile(baseImg);
                String rnd = getRnd();
                Map<String,String> map=new HashMap<String,String>();
                map.put("rnd",rnd);
                map.put("sign", GetSign.getSign(map));
                ApiRequest.uploadImg(map,item, new MyCallBack<BaseObj>(mContext) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        if(selectImgIndex ==-1){
                            if(isEmpty(addImgAdapter.getList())){
                                List<String> list=new ArrayList<String>();
                                list.add(obj.getImg());
                                addImgAdapter.setList(list);
                            }else{
                                addImgAdapter.getList().add(obj.getImg());
                            }
                        }else{
                            addImgAdapter.getList().set(selectImgIndex,obj.getImg());
                        }
                        addImgAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);
                dismissLoading();
                showToastS("图片处理失败");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK){
            return;
        }
        switch (requestCode){
            case 2000:
                uploadImg();
                break;
            case 3000:
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null,null);
                if (cursor != null && cursor.moveToFirst()) {
                    imgSaveName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    uploadImg();
                }
                break;
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_fatie_fabu})
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_fatie_fabu:
                title=getSStr(et_fatie_title);
                content=getSStr(et_fatie_content);
                if (TextUtils.isEmpty(title)) {
                    showMsg("标题不能为空");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    showMsg("内容不能为空");
                    return;
                }
                fate();


                
            break;
        }
    }

    private void fate() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",GetSign.getSign(map));
        FatieBody body=new FatieBody();
        body.setTitle(title);
        body.setContent(content);
        List< FatieBody.ImagesBean> imgBean=new ArrayList<>();
        if(notEmpty(addImgAdapter.getList())){
            for (int i = 0; i < addImgAdapter.getList().size(); i++) {
                FatieBody.ImagesBean bean=new FatieBody.ImagesBean();
                bean.setImages((String)addImgAdapter.getList().get(i));
                imgBean.add(bean);
            }
        }
        body.setImages(imgBean);
        com.sk.lgtea.module.taolun.network.ApiRequest.postMessage(map,body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();
                RxBus.getInstance().post(new FatieEvent());


            }
        });

    }
}
