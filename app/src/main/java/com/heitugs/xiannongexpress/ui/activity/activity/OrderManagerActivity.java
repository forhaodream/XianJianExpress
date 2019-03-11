package com.heitugs.xiannongexpress.ui.activity.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.base.BaseActivity;
import com.heitugs.xiannongexpress.model.OrderManagerModel;
import com.heitugs.xiannongexpress.ui.activity.adapter.ManagerAdapter;
import com.heitugs.xiannongexpress.ui.activity.adapter.SimpleAdapter;
import com.heitugs.xiannongexpress.utils.UrlUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class OrderManagerActivity extends BaseActivity implements View.OnClickListener {
    private String usernum;
    private ImageView returnImg;
    private String sendAddress;
    private ManagerAdapter adapter;
    private ListView mListView;
    private List<OrderManagerModel.DataEntity> mData;
    private String startS, startFullS, nameS, phoneS;
    private String startE, startFullE, nameE, phoneE;
    private List<String> datas;
    private OrderManagerModel.DataEntity entity;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    adapter = new ManagerAdapter(mData);
                    mListView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manager);
        getUid();
        mListView = $(R.id.manager_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mData.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        returnImg = $(R.id.manager_return);
        returnImg.setOnClickListener(this);
        addOrder();
    }


    private void addOrder() {
        mData = new ArrayList<>();
        String url = UrlUtil.ownOrderUrl + usernum + "&type=l";
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request mRequest = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(mRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String order = response.body().string();
                Gson gson = new Gson();
                final OrderManagerModel managerModel = gson.fromJson(order, OrderManagerModel.class);
                if (managerModel != null) {
                    mData = managerModel.getData();
                    handler.obtainMessage(0).sendToTarget();
                } else {
                    Looper.prepare();
                    Toast.makeText(OrderManagerActivity.this, "空", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

            }
        });
    }

    private void getUid() {
        SharedPreferences uid = getSharedPreferences("save_uid", MODE_PRIVATE);
        usernum = uid.getString("uid", null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manager_return:
                finish();
                break;
        }
    }
}
