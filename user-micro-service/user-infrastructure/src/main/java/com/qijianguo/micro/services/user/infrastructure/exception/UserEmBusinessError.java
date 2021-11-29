package com.qijianguo.micro.services.user.infrastructure.exception;

import com.qijianguo.micro.services.base.exception.CommonError;

/**
 * @author qijianguo
 */
public enum UserEmBusinessError implements CommonError{

    CODE_REQ_PHONE_REQ_FREQUENCY(1000, "验证码请求频繁."),
    CODE_REQ_MAX_COUNTS(1001, "验证码请求频繁，超出每日请求次数."),
    CODE_EXPIRED(1002, "验证码错误或已过期."),

    CAPTCHA_IMG_ERROR(1003, "图片验证码错误"),
    CAPTCHA_TYPE_NOT_SUPPORT(1004, "验证码类型不支持"),
    CAPTCHA_TOKEN_NOT_INIT(1005, "Token未初始化"),
    TOKEN_EXPIRED(1006, "Token失效.")



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
    public void setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
    }
}
