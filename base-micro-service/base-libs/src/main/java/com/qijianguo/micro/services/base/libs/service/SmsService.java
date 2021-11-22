package com.qijianguo.micro.services.base.libs.service;

import com.qijianguo.micro.services.base.libs.bean.SmsRequest;

/**
 * 短信服务
 * @author qijianguo
 */
public interface SmsService {

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @param code 验证码
     * @return
     */
    boolean sendVerifyCode(String phone, String code);

    /**
     * 发送验证码
     * @param smsRequest
     * @return
     */
    boolean sendVerifyCode(SmsRequest smsRequest);

}
