package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.user.application.event.publish.UserEventPublish;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qijianguo
 */
@Service
public class UserAppService {

    @Autowired
    private CaptchaAppService phoneCaptchaAppService;
    @Autowired
    private UserDomainService userDomainService;
    @Autowired
    private UserEventPublish userEventPublish;

    public User save(Captcha captcha) {
        phoneCaptchaAppService.validate(captcha);

        User userByPhone = userDomainService.createUserByPhone(captcha.getPhoneNumber());

        userEventPublish.userCreated(userByPhone);

        return userByPhone;
    }

}
