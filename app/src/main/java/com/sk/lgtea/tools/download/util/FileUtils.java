package com.sk.lgtea.tools.download.util;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;

/**
 * Created by aspsine on 15-4-19.
 */
public class FileUtils {
    public static final String DOWNLOAD_DIR = "lgteadownload";

    public static final File getDownloadDir() {
        /*if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(context.getExternalCacheDir(), DOWNLOAD_DIR);
        }
        return new File(context.getCacheDir(), DOWNLOAD_DIR);*/
        File file = new File(Environment.getExternalStorageDirectory(), DOWNLOAD_DIR);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    public static final String getPrefix(@NonNull String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static final String getSuffix(@NonNull String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(getDownloadDir()+"/"+fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Log.i("===","删除单个文件" + fileName + "成功！");
                return true;
            } else {
                Log.i("===","删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            Log.i("===","文件" + fileName + "不存在！");
            return false;
        }
    }
}
