package com.qijianguo.micro.services.user.infrastructure.exception;

import com.qijianguo.micro.services.base.exception.CommonError;

/**
 * @author qijianguo
 */
public enum UserEmBusinessError implements CommonError {

    CODE_REQ_PHONE_REQ_FREQUENCY(1000, "验证码请求频繁，请在%d秒后再试."),
    CODE_REQ_MAX_COUNTS(1001, "验证码请求频繁，超出每日请求次数.");

    ;

    private Integer errCode;

    private String errMsg;

    UserEmBusinessError(int errCode, String errorMsg) {
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
