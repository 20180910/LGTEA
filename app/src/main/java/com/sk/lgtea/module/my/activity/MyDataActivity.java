package com.sk.lgtea.module.my.activity;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.MyImageView;
import com.sk.lgtea.Config;
import com.sk.lgtea.GetSign;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BaseActivity;
import com.sk.lgtea.base.BaseObj;
import com.sk.lgtea.base.MyCallBack;
import com.sk.lgtea.module.my.Constant;
import com.sk.lgtea.module.my.network.request.UploadImgBody;
import com.sk.lgtea.network.ApiRequest;
import com.sk.lgtea.tools.BitmapUtils;
import com.sk.lgtea.tools.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import top.zibin.luban.Luban;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyDataActivity extends BaseActivity {
    @BindView(R.id.im_my_data_icon)
    MyImageView im_my_data_icon;
    @BindView(R.id.ll_my_data_icon)
    LinearLayout ll_my_data_icon;
    @BindView(R.id.tv_my_data_name)
    TextView tv_my_data_name;
    @BindView(R.id.ll_my_data_name)
    LinearLayout ll_my_data_name;
    @BindView(R.id.tv_my_data_zuanye)
    TextView tv_my_data_zuanye;
    @BindView(R.id.ll_my_data_zhuanye)
    LinearLayout ll_my_data_zhuanye;
    @BindView(R.id.tv_my_data_sex)
    TextView tv_my_data_sex;
    @BindView(R.id.ll_my_data_sex)
    LinearLayout ll_my_data_sex;
    @BindView(R.id.tv_my_data_phone)
    TextView tv_my_data_phone;
    @BindView(R.id.ll_my_data_phone)
    LinearLayout ll_my_data_phone;
    @BindView(R.id.tv_my_data_mailbox)
    TextView tv_my_data_mailbox;
    @BindView(R.id.ll_my_data_mailbox)
    LinearLayout ll_my_data_mailbox;
    @BindView(R.id.tv_my_data_qianming)
    TextView tv_my_data_qianming;
    @BindView(R.id.ll_my_data_qianming)
    LinearLayout ll_my_data_qianming;

    String name, avatar, className, sex, mobile, email,individuality_signature;
    private BottomSheetDialog selectPhotoDialog;
    private String imgUrl = "";

    @Override
    protected int getContentView() {
        setAppTitle("个人信息");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_my_data;
    }

    @Override
    protected void initView() {
        name = SPUtils.getPrefString(mContext, Config.name, "");
        avatar = SPUtils.getPrefString(mContext, Config.avatar, "");
        className = SPUtils.getPrefString(mContext, Config.class_name, "");
        sex = SPUtils.getPrefString(mContext, Config.sex, "");
        mobile = SPUtils.getPrefString(mContext, Config.mobile, "");
        email = SPUtils.getPrefString(mContext, Config.email, "");
        individuality_signature = SPUtils.getPrefString(mContext, Config.individuality_signature, "");

        tv_my_data_name.setText(name);
        tv_my_data_zuanye.setText(className);
        tv_my_data_sex.setText(sex);
        tv_my_data_phone.setText(mobile);
        Glide.with(mContext).load(avatar).error(R.drawable.my_people).into(im_my_data_icon);
        if (email.equals("")) {
            tv_my_data_mailbox.setText("设置");
        } else {
            tv_my_data_mailbox.setText(email);
        }
        if (individuality_signature.equals("")) {
            tv_my_data_qianming.setText("设置");
        } else {
            tv_my_data_qianming.setText(individuality_signature);
        }


    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.ll_my_data_name,
            R.id.ll_my_data_zhuanye,
            R.id.ll_my_data_sex,
            R.id.ll_my_data_phone,
            R.id.ll_my_data_mailbox,
            R.id.im_my_data_icon,
            R.id.ll_my_data_qianming})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_data_name:
                break;
            case R.id.ll_my_data_zhuanye:
                break;
            case R.id.ll_my_data_sex:
                break;
            case R.id.ll_my_data_phone:
                mobile=getSStr(tv_my_data_phone) ;

                Intent Phone = new Intent();
                Phone.putExtra(Constant.IParam.type, Constant.IParam.phone);
                Phone.putExtra(Constant.IParam.value, mobile);
                STActivityForResult(Phone, EditPhoneActivity.class, 001);
                break;
            case R.id.ll_my_data_mailbox:
                mobile=getSStr(tv_my_data_mailbox) ;
                Intent Mailbox = new Intent();
                Mailbox.putExtra(Constant.IParam.type, Constant.IParam.mailbox);
                Mailbox.putExtra(Constant.IParam.value, email);

                STActivityForResult(Mailbox, EditPhoneActivity.class, 002);
                break;
            case R.id.im_my_data_icon:
                showSelectPhotoDialog();
                break;
            case R.id.ll_my_data_qianming:
                mobile=getSStr(tv_my_data_qianming) ;
                Intent Qianming = new Intent();
                Qianming.putExtra(Constant.IParam.type, Constant.IParam.qianming);
                Qianming.putExtra(Constant.IParam.value, individuality_signature);
                STActivityForResult(Qianming, EditPhoneActivity.class, 003);
                break;
        }
    }

    private void showSelectPhotoDialog() {
        if (selectPhotoDialog == null) {
            View sexView = LayoutInflater.from(mContext).inflate(R.layout.popu_select_photo, null);
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
    private String imgSaveName = "";

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
        Log.i("========", "========" + imgSaveName);
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String newPath = ImageUtils.filePath;
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
                UploadImgBody body = new UploadImgBody();
                body.setFile(baseImg);
                String rnd = getRnd();
                Map<String, String> map = new HashMap<String, String>();
                map.put("rnd", rnd);
                map.put("sign", GetSign.getSign(map));
                ApiRequest.uploadImg(map, body, new MyCallBack<BaseObj>(mContext, true) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        imgUrl = obj.getImg();
                        Glide.with(mContext).load(imgSaveName).error(R.drawable.my_people).into(im_my_data_icon);
                        updateInfoForImg();
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


    private void updateInfoForImg() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("avatar", imgUrl);
        map.put("sign", GetSign.getSign(map));
        com.sk.lgtea.module.my.network.ApiRequest.updateUserImg(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                if (!TextUtils.isEmpty(imgUrl)) {
                    SPUtils.setPrefString(mContext, Config.avatar, imgUrl);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case 2000:
                uploadImg();
                break;
            case 3000:
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    imgSaveName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    uploadImg();
                }
                break;
            case 001:
                tv_my_data_phone.setText(data.getStringExtra(Constant.IParam.value));

                break;
            case 002:
                tv_my_data_mailbox.setText(data.getStringExtra(Constant.IParam.value));
                break;
            case 003:
                tv_my_data_qianming.setText(data.getStringExtra(Constant.IParam.value));
                break;
        }
    }


}
