package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.application.event.publish.UserEventPublish;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.entity.CaptchaFactory;
import com.qijianguo.micro.services.user.domain.captcha.service.CaptchaDomainService;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.entity.UserFactory;
import com.qijianguo.micro.services.user.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
@Service
public class UserAppService {

    @Autowired
    private CaptchaAppService phoneCaptchaAppService;
    @Autowired
    private CaptchaDomainService tokenCaptchaDomainService;
    @Autowired
    private UserDomainService userDomainService;
    @Autowired
    private UserEventPublish userEventPublish;

    @Transactional(rollbackFor = BusinessException.class)
    public User save(Captcha captcha) {
        phoneCaptchaAppService.validate(captcha);

        User user = userDomainService.createUserByPhone(UserFactory.toUserDO(captcha.getPhoneNumber()));

        CaptchaFactory.addToken(captcha, String.valueOf(user.getId()), user, 7, TimeUnit.DAYS);

        tokenCaptchaDomainService.create(captcha);

        user.setToken(captcha.getTokenValue());

        userEventPublish.userCreated(user);

        return user;
    }

}
