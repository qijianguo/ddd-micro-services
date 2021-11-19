package com.qijianguo.micro.services.base.exception;

/**
 * @author qijianguo
 */
public enum EmBusinessError implements CommonError {

    UNKNOWN_ERROR(101, "unknown err"),
    SUCCESS(200, "success"),

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
    public CommonError setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
        return this;
    }
}
