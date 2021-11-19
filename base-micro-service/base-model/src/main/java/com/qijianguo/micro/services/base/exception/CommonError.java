package com.qijianguo.micro.services.base.exception;

/**
 * @author qijianguo
 * @version 1.0
 * @Title: CommonError
 * @Description: TODO
 * @date 2019/2/16 12:40
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

    CommonError setErrorMsg(String errorMsg);
}
