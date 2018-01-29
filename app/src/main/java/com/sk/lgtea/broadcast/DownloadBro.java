package com.sk.lgtea.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sk.lgtea.Constant;
import com.sk.lgtea.tools.IntentUtils;


/**
 * Created by Administrator on 2017/12/23.
 */

public class DownloadBro extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction(); //动作

        if (action.equals("download")) {
            String path = intent.getStringExtra(Constant.IParam.path);

            IntentUtils.openFileIntent(context,path);

        }
    }

}
