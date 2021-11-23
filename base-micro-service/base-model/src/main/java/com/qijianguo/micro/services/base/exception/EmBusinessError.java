package com.qijianguo.micro.services.base.exception;

/**
 * @author qijianguo
 */
public enum EmBusinessError implements CommonError {

    SUCCESS(200, "请求成功"),
    UNKNOWN_ERROR(101, "未知错误，请重试!"),
    PARAMETER_ERROR(102, "参数错误!"),

    // USER：1000

    // ACCOUNT: 2000

    // ...
    ;

    private Integer errCode;

    private String errMsg;

    EmBusinessError(int errCode, String errorMsg) {
        this.errCode = errCode;
        this.errMsg = errorMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
    }
}
