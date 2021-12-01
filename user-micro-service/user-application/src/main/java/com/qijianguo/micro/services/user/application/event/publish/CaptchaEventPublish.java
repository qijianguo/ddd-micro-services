package com.qijianguo.micro.services.user.application.event.publish;

import com.qijianguo.micro.services.user.domain.verification.entity.Phone;
import org.springframework.stereotype.Service;

/**
 * @author qijianguo
 */
@Service
public class CaptchaEventPublish {

    public void publish(Phone phone) {
        // 推送到MQ
        // mqService.push("SMS", phone.getPhone(), phone.getCode());
    }
}
