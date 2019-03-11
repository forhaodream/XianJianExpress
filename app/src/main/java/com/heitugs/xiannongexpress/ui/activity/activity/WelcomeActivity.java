package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.heitugs.xiannongexpress.BuildConfig;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.ui.activity.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class WelcomeActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    //底部小店图片
    private ImageView[] dots;

    //记录当前选中位置
    private int currentIndex;
    // 跳转
    private Button mBtn;

    //引导图片资源
    private static final int[] pics = {R.mipmap.wel_one, R.mipmap.wel_t, R.mipmap.wel_three, R.mipmap.wel_four};

    private SharedPreferences sp;
    private int count = 5;
    private Animation animation;

    boolean isFirstIn = false;
    private static final String SP_NAME = "first_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
//        boolean isFisrtRun = sharedPreferences.getBoolean("isFirstRun", true);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        if (isFisrtRun) {
//            Log.d("debug", "第一次运行");
//            editor.putBoolean("isFisrtRun", false);
//            editor.commit();
//        } else {
//            Log.d("debug", "不是第一次运行");
//        }
//        if (null != sharedPreferences) {
//            goAdsAty();
//        } else {
        setContentView(R.layout.activity_welcome);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);

        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        isFirstIn = sp.getBoolean("isFirst", true);
        if (isFirstIn) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
            handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    switch (msg.what) {
                        case 1:
                            int time = Integer.valueOf(msg.arg1);
                            if (time == 1) {
                                showHome();
//                                getCount();
                            }
                    }
                    return false;
                }
            });
            //Thre();
        } else {
            goAdsAty();
        }
        // isFirst();
        views = new ArrayList<>();

        mBtn = (Button) findViewById(R.id.welcome_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(toHome);
            }
        });
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            views.add(iv);

        }
        vp = (ViewPager) findViewById(R.id.viewpager);
        vpAdapter = new ViewPagerAdapter(views);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);
        initDots();
//        }
    }

    private void showHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();

            }
        }
    };

    private void Thre() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 5; i > 0; i--) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }

    private void goAdsAty() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, AdsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.welcome_ll);
        dots = new ImageView[pics.length];
        // 循环小点图片
        for (int i = 0; i < pics.length; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true); // 都设置为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i); // 设置位置tag,方便去除与当前位置对应
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false); // 设置为白色,即选中状态
    }

    /**
     * 设置当前的引导页
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vp.setCurrentItem(position);
    }

    /**
     * 这只是当前引导小点的选中
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length - 1 || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = position;
    }


    //当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int i, float v, int i1) {

        if (i == 3) {
            mBtn.setVisibility(View.VISIBLE);
        }
    }

    //当新的页面被选中时调用
    @Override
    public void onPageSelected(int i) {

        setCurDot(i);
    }

    // 当滑动状态该改变时调用
    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        setCurView(position);
        setCurDot(position);


    }

    private void isFirst() {
        SharedPreferences setting = getSharedPreferences("a", 0);
        Boolean userFirst = setting.getBoolean("FIRST", true);
        if (userFirst) { // 第一次
            setting.edit().putBoolean("FIRST", false).commit();
            Toast.makeText(WelcomeActivity.this, "第一次", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(WelcomeActivity.this, "不是第一次", Toast.LENGTH_LONG).show();
        }
    }
}
