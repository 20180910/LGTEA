package com.sk.lgtea.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sk.lgtea.tools.download.entity.AppInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory.TAG;


/**
 * Created by Administrator on 2017/12/22.
 */

public class DBHelper extends SQLiteOpenHelper {
    private  static  final String DB_NAME = "file_complete.db";
    private  static  final String TABLE_NAME="complete_info";
    private  static  final int DB_VERSION = 1;
    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelper newInstance(Context context) {
        DBHelper dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        return dbHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTable(db);
        createTable(db);
    }
    private   void createTable(SQLiteDatabase db) {
        /*id fileName  title  houZhui  fileSize image url*/
        db.execSQL("create table " + TABLE_NAME + "(_id integer primary key autoincrement, id integer, fileName text,title text, image text , url text , houZhui text, fileSize text ,creattime text)");
    }
    private  void dropTable(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + TABLE_NAME);
    }
    private String dateToString() {
        String ymdhms = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(ymdhms);
        return df.format(new Date());
    }
    public void insert(AppInfo info) {
        /*if(hasMoreData(info.getTitle())){
            return;
        }*/
        deleteSameData(info.getId());
        Log.i(TAG+"===","======"+info.getImage()+"==="+info.getFileSize());
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into "
                        + TABLE_NAME
                        + "(id, fileName, title, image,url, houZhui, fileSize,creattime) values(?, ?, ?, ?, ?, ?,?,?)",
                new Object[]{info.getId(), info.getFileName()+"."+info.getHouZhui(), info.getTitle(),info.getImage(), info.getUrl(), info.getHouZhui(), info.getFileSize(),dateToString()});
    }
    public void deleteSameData(String id){
        SQLiteDatabase db = getWritableDatabase();
         db.delete(TABLE_NAME, "id=?", new String[]{id});
    }
    public boolean hasMoreData(String title){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select id from " + TABLE_NAME + " where title = ? ", new String[]{title});
        boolean hasMore = cursor.moveToNext();
        cursor.close();
        return hasMore;
    }

    public List<AppInfo> getCompleteInfo(String str,String placeholder) {
        List<AppInfo> list = new ArrayList<AppInfo>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "
                        + TABLE_NAME
                        + " where fileName in ("+placeholder+") order by creattime desc",
                str.split(","));









        while (cursor.moveToNext()) {
             /*id fileName  title  houZhui  fileSize image url*/
            AppInfo info = new AppInfo();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setFileName(cursor.getString(cursor.getColumnIndex("fileName")));
            info.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            info.setHouZhui(cursor.getString(cursor.getColumnIndex("houZhui")));
            info.setFileSize(cursor.getString(cursor.getColumnIndex("fileSize")));
            info.setImage(cursor.getString(cursor.getColumnIndex("image")));
            info.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            list.add(info);
        }
        cursor.close();
        return list;
    }

}
