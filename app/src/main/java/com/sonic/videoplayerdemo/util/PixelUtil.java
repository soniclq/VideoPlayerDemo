package com.sonic.videoplayerdemo.util;

import android.content.Context;
import android.view.View;

/**
 * Created by liqian-ps on 2018/4/26.
 */

public class PixelUtil {
    private static Context mContext;

    static public void init(Context   context){
        mContext  = context;
    }

//    //获取运行屏幕宽度
//    public static int getScreenWidth() {
////        DisplayMetrics dm = new DisplayMetrics();
////        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
////        //宽度 dm.widthPixels
////        //高度 dm.heightPixels
//////        LogUtil.i("info", "getScreenWidth" + dm.widthPixels);
////        return dm.widthPixels;
//    }

    /**
     * 获取控件宽
     */
    public static int getWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredWidth());
    }


    //DP转PX
    public static int dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //PX转DP
    public static int px2dp(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

