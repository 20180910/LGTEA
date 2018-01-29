package com.sk.lgtea.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.sk.lgtea.R;
import com.sk.lgtea.base.BackCall;

public class DialogUtil {





    // 二维码
    public static Dialog showERCodeDialog(String img, final Context context, final BackCall call) {
        final Dialog dlg = new Dialog(context, R.style.PushUpInDialogThem);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.dialog_ercode_show, null);
        ImageView img4_2 = (ImageView)layout.findViewById(R.id.img4_2);
        Glide.with(context).load(img).error(R.color.c_press).into(img4_2);

        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        dlg.onWindowAttributesChanged(lp);
        dlg.setCanceledOnTouchOutside(true);
        dlg.setContentView(layout);
        dlg.setCancelable(false);
        return dlg;
    }




}
