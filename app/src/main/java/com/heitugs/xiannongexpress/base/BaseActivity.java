package com.heitugs.xiannongexpress.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public abstract class BaseActivity extends Activity {

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $(int rsId) {
        return (T) findViewById(rsId);
    }



    // 简化Toast
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // 页面跳转
    public void startAty(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * Bundle bundle = new Bundle();
     * bundle.putString("name", nameEd.toString());
     * startAty(TakeActivity.class, bundle);
     */

    // 携带数据的页面跳转
    public void startAty(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startAtyForResult(Class<?> cls, Bundle bundle,
                                  int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}
