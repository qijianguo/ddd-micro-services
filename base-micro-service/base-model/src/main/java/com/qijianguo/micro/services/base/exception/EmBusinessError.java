package com.qijianguo.micro.services.base.exception;

/**
 * @author qijianguo
 */
public enum EmBusinessError implements CommonError {

    SUCCESS(200, "Success"),
    UNKNOWN_ERROR(101, "Unknown error!"),
    PARAMETER_ERROR(102, "Parameter error!"),

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
    public CommonError setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
        return this;
    }
}
