package com.qijianguo.micro.services.base.libs.service.weixin.bean;

public class BaseResp {

    private static final String SUCCESS_CODE = "0";
    private String errcode;
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean isSuccess() {
        return this.errcode == null || this.errcode.isEmpty() || this.errcode.equals("0");
    }
}
