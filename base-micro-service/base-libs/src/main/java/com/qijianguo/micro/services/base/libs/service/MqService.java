package com.qijianguo.micro.services.base.libs.service;

import com.qijianguo.micro.services.base.libs.bean.SmsRequest;
import com.qijianguo.micro.services.base.libs.bean.SmsResponse;

public interface MqService {

    SmsResponse sendSms(SmsRequest request);
}
