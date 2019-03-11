package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.heitugs.xiannongexpress.R;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class AdsActivity extends Activity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AdsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
