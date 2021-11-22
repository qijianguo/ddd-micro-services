package com.qijianguo.micro.services.base.libs.service.sms;

import com.qijianguo.micro.services.base.libs.bean.SmsRequest;
import com.qijianguo.micro.services.base.libs.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author qijianguo
 */
@Component
public class DefaultSmsServiceHandler implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultSmsServiceHandler.class);

    @Override
    public boolean sendVerifyCode(String phone, String code) {
        logger.info("start send msg: {} >> {}", phone, code);
        // TODO 发送短信

        logger.info("finished send msg: {} >> {}", phone);
        return false;
    }

    @Override
    public boolean sendVerifyCode(SmsRequest smsRequest) {
        logger.info("start send msg: {} >> {}", smsRequest.getPhone(), smsRequest.getCode());
        // TODO 发送短信

        logger.info("finished send msg: {} >> {}", smsRequest.getPhone());
        return false;
    }
}
