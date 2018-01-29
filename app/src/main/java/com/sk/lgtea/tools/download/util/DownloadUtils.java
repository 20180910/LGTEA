package com.sk.lgtea.tools.download.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.aspsine.multithreaddownload.DownloadManager;
import com.github.baseclass.rx.RxBus;
import com.sk.lgtea.database.DBHelper;
import com.sk.lgtea.module.home.event.DownLoadSuccessEvent;
import com.sk.lgtea.tools.download.entity.AppInfo;
import com.sk.lgtea.tools.download.service.DownloadService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.sk.lgtea.tools.download.CrashHandler.TAG;

/**
 * Created by Administrator on 2017/12/22.
 */

public class DownloadUtils {
    public static boolean isExistFile(AppInfo info) {
        List<String> fileName= getFileName();
        if (fileName == null||fileName.size()==0) {
            return false;
        }
        boolean isExist=false;
        for (int i = 0; i < fileName.size(); i++) {
            if((info.getFileName()+"."+info.getHouZhui()).equalsIgnoreCase(fileName.get(i))){
                isExist=true;
                break;
            }
        }
        return isExist;
    }
    public static void startDownload(boolean isExist,Context context, AppInfo info){
        String msg="文件正在下载中,是否重新下载?";
        if(isExist){
            msg="文件已下载,是否重新下载?";
        }
        if (isExist|| DownloadManager.getInstance().getDownloadInfo(info.getId()) != null) {
            RxBus.getInstance().post(new DownLoadSuccessEvent("yes"));
//            MyDialog.Builder mDialog = new MyDialog.Builder(context);
//            mDialog.setMessage(msg);
//            mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    DownloadManager.getInstance().delete(info.getId());
//                    DownloadService.intentDownload(context, info);
//                }
//            });
//            mDialog.create().show();
        } else {
            DownloadService.intentDownload(context, info);
            RxBus.getInstance().post(new DownLoadSuccessEvent("no"));
        }
    }
    public static List<AppInfo> getDownloadCompleteFile(Context context) {
        List<AppInfo>list=new ArrayList<>();
        List<String> fileName = getFileName();
        if(fileName==null||fileName.size()==0){
            return list;
        }
        StringBuffer name=new StringBuffer();
        StringBuffer placeholder=new StringBuffer();

        for (int i = 0; i < fileName.size(); i++) {
            if(!TextUtils.isEmpty(fileName.get(i))){
                name.append(fileName.get(i)+",");
                placeholder.append("?,");
            }
        }
        if(!TextUtils.isEmpty(name)){
            name.deleteCharAt(name.length()-1);
            placeholder.deleteCharAt(placeholder.length()-1);
        }
        list = DBHelper.newInstance(context).getCompleteInfo(name.toString(),placeholder.toString());
        return list;
    }

    public static List<String> getFileName() {
        //要复制的文件目录
        List<String> fileName=new ArrayList<>();
        File[] currentFiles;
        File root = FileUtils.getDownloadDir();
        //如同判断SD卡是否存在或者文件是否存在
        //如果不存在则 return出去
        if (!root.exists()) {
            return null;
        }
        //如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();
        //遍历要复制该目录下的全部文件
        for (int i = 0; i < currentFiles.length; i++) {
            if (!currentFiles[i].isDirectory()) {
                fileName.add(currentFiles[i].getName());
            }
        }
        return fileName;
    }

    public static void openAssignFolder(Context activity ){
        File parentFlie = new File(FileUtils.getDownloadDir()+"/");
        Log.i(TAG+"===","==="+parentFlie.getPath());
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(Uri.fromFile(parentFlie), "*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity.startActivity(intent);
    }
    public static void openAssignFolder(Context activity,String path){
        File file = FileUtils.getDownloadDir();
        if(null==file || !file.exists()){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "file/*");
        try {
            activity.startActivity(intent);
            activity.startActivity(Intent.createChooser(intent,"选择浏览工具"));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
