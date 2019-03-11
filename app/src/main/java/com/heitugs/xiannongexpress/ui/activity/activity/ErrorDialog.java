package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.heitugs.xiannongexpress.R;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class ErrorDialog extends Dialog {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_error);
    }

    public ErrorDialog(Context context) {
        super(context);
    }
}
