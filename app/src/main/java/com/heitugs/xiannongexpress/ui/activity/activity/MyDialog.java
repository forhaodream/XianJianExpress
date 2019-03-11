package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.heitugs.xiannongexpress.R;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_normal_layout);
    }
}
