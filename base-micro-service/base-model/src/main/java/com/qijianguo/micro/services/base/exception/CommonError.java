package com.qijianguo.micro.services.base.exception;

/**
 * @author qijianguo
 */
public interface CommonError {

    /**
     * 错误码
     * @return
     */
    int getErrCode();

    /**
     * 错误信息
     * @return
     */
    String getErrMsg();

    void setErrorMsg(String errorMsg);
}
