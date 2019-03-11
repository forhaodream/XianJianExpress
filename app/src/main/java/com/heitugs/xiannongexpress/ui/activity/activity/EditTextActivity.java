package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.heitugs.xiannongexpress.R;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class EditTextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_test);
        EditText editText = (EditText) findViewById(R.id.edit_test);
        editText.setText("dada");
    }
}
