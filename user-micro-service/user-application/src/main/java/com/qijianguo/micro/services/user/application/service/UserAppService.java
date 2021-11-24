package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.user.application.event.publish.UserEventPublish;
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

    public User save(String phone, Integer code) {
        
        phoneCaptchaAppService.validate(phone, String.valueOf(code));

        User userByPhone = userDomainService.createUserByPhone(phone);

        userEventPublish.userCreated(userByPhone);

        return userByPhone;
    }

}
