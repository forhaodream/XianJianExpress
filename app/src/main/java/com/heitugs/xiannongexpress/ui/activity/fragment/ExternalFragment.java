package com.heitugs.xiannongexpress.ui.activity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heitugs.xiannongexpress.R;

/**
 * Created by Administrator on 2016/9/12 0012.
 */


public class ExternalFragment extends Fragment {
    private TextView priceTv;

    private String price;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_external, container, false);
        priceTv = (TextView) view.findViewById(R.id.product_price);
        getPrice();
        return view;
    }

    private void getPrice() {
        SharedPreferences orderPrice = getActivity().getSharedPreferences("save_endPrice", Context.MODE_PRIVATE);
        price = String.valueOf(orderPrice.getInt("endPrice", 0));
        priceTv.setText(price);
    }
}