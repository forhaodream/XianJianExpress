package com.heitugs.xiannongexpress.model;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class OrderModel {

    /**
     * msg : 成功
     * usernum :
     * companyid :
     * status : 1
     */
    private String msg;
    private String usernum;
    private String companyid;
    private String status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public String getUsernum() {
        return usernum;
    }

    public String getCompanyid() {
        return companyid;
    }

    public String getStatus() {
        return status;
    }
}
