package com.heitugs.xiannongexpress.ui.activity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class LocationActivity extends BaseActivity implements View.OnClickListener {
    private TextView hTv, qTv, mTv, jTv, dTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        findId();
    }

    private void findId() {
        hTv = $(R.id.location_h);
        qTv = $(R.id.location_q);
        mTv = $(R.id.location_m);
        jTv = $(R.id.location_j);
        dTv = $(R.id.location_d);
        hTv.setOnClickListener(this);
        qTv.setOnClickListener(this);
        mTv.setOnClickListener(this);
        jTv.setOnClickListener(this);
        dTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.location_h:
                SharedPreferences.Editor h = getSharedPreferences("h", MODE_PRIVATE).edit();
                h.putString("h", "哈尔滨");
                h.commit();
                Intent htoHome = new Intent(LocationActivity.this, HomeActivity.class);
                startActivity(htoHome);
                break;
            case R.id.location_q:
                SharedPreferences.Editor q = getSharedPreferences("q", MODE_PRIVATE).edit();
                q.putString("q", "齐齐哈尔");
                q.commit();
                Intent qtoHome = new Intent(LocationActivity.this, HomeActivity.class);
                qtoHome.putExtra("q", "齐齐哈尔");
                startActivity(qtoHome);
                break;
            case R.id.location_m:
                SharedPreferences.Editor m = getSharedPreferences("m", MODE_PRIVATE).edit();
                m.putString("m", "牡丹江");
                m.commit();
                Intent mtoHome = new Intent(LocationActivity.this, HomeActivity.class);
                startActivity(mtoHome);
                break;
            case R.id.location_j:
                SharedPreferences.Editor j = getSharedPreferences("j", MODE_PRIVATE).edit();
                j.putString("j", "佳木斯");
                j.commit();
                Intent jtoHome = new Intent(LocationActivity.this, HomeActivity.class);
                startActivity(jtoHome);
                break;
            case R.id.location_d:
                SharedPreferences.Editor d = getSharedPreferences("d", MODE_PRIVATE).edit();
                d.putString("d", "大庆");
                d.commit();
                Intent dtoHome = new Intent(LocationActivity.this, HomeActivity.class);
                startActivity(dtoHome);
                break;
        }

    }
}
