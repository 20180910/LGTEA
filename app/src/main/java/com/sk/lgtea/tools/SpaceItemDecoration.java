package com.sk.lgtea.tools;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by administartor on 2017/8/3.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;
    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
//        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mSpace;
//        }
    }
}
