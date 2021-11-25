package com.qijianguo.micro.services.user.application.event.subscribe;

import com.qijianguo.micro.services.user.application.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qijianguo
 */
@Service
public class CaptchaEventSubscribe {

    @Autowired
    private UserAppService userAppService;

    public void phoneVerified() {
        // get data from MQ...


    }

}
