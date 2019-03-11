package com.heitugs.xiannongexpress.ui.activity.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.model.LoginModel;
import com.heitugs.xiannongexpress.model.MsgModel;
import com.heitugs.xiannongexpress.utils.UrlUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/3 0003.
 */
public class TestJsonActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
        initData();
        textView = (TextView) findViewById(R.id.json_text);
    }

    private void initData() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://app.heitugs.com/user/l_no.aspx?username=test&password=111111").build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String aa = response.body().string();
                Gson gson = new Gson();
                LoginModel login = gson.fromJson(aa, LoginModel.class);
                final String msg = login.getMsg();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(msg);
                    }
                });
            }
        });


    }

    private void twoData() {
        OkHttpClient client = new OkHttpClient();
        Request mRequest = new Request.Builder().url(UrlUtil.aa).build();
        Call mCall = client.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String ppp = response.body().string();
                Gson gson = new Gson();


            }
        });
    }
}
