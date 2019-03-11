package com.heitugs.xiannongexpress.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class ToastUtils {
    public static void showInfo(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
