package com.heitugs.xiannongexpress.model;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class AddressModel {
    private String size;
    private String sendTime;
    private String receiveTime;
    private String sendAddress;
    private String receiveAddress;

    public AddressModel(String size, String sendTime, String receiveTime, String sendAddress, String receiveAddress) {
        this.size = size;
        this.sendTime = sendTime;
        this.receiveTime = receiveTime;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
}
