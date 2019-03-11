package com.heitugs.xiannongexpress.ui.activity.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heitugs.xiannongexpress.R;
import com.heitugs.xiannongexpress.model.OrderManagerModel;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class ManagerAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<OrderManagerModel.DataEntity> mData;
    private List<String> datas;

    public ManagerAdapter(List<OrderManagerModel.DataEntity> datas) {
        this.mData = datas;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        System.out.println("adapter" + mData);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager, null);
            holder = new ViewHolder();
            // 始发地
            holder.startAds = (TextView) convertView.findViewById(R.id.manager_start_ads);
            holder.startName = (TextView) convertView.findViewById(R.id.manager_start_name);
            holder.startPhone = (TextView) convertView.findViewById(R.id.manager_start_phone);
            //  终点
            holder.endAds = (TextView) convertView.findViewById(R.id.manager_end_ads);
            holder.endName = (TextView) convertView.findViewById(R.id.manager_end_name);
            holder.endPhone = (TextView) convertView.findViewById(R.id.manager_end_phone);
            // 状态
            holder.state = (TextView) convertView.findViewById(R.id.manager_state);
            // 完成订单
            holder.finishTv = (TextView) convertView.findViewById(R.id.order_finish);
           // holder.finishTv.setOnClickListener(new MyListener(i));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.startName.setText(mData.get(i).getSendAddress());
        holder.endAds.setText(mData.get(i).getReceiveAddress());
        holder.state.setText(mData.get(i).getPay_status());

        return convertView;
    }

    class ViewHolder {
        TextView timeTv;
        TextView startName, startAds, startPhone;
        TextView endPhone, endAds, endName;
        TextView priceTv;
        TextView state;
        TextView finishTv;
    }

    private class MyListener implements View.OnClickListener {
        int mPosition;

        public MyListener(int inPosition) {
            mPosition = inPosition;
        }

        @Override
        public void onClick(View v) {

            mData.remove(mPosition);
        }

    }

}
