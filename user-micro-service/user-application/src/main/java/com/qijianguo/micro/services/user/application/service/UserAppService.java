package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.user.application.event.publish.UserEventPublish;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.entity.Token;
import com.qijianguo.micro.services.user.domain.captcha.service.CaptchaDomainService;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.entity.UserFactory;
import com.qijianguo.micro.services.user.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User save(Captcha captcha) {
        phoneCaptchaAppService.validate(captcha);

        User user = userDomainService.createUserByPhone(UserFactory.toUserDO(captcha.getPhoneNumber()));

        Token token = new Token();
        token.setId(String.valueOf(user.getId()));
        token.setData(user);
        token.setTimeout(7);
        token.setTimeUnit(TimeUnit.DAYS);
        captcha.setToken(token);
        tokenCaptchaDomainService.create(captcha);

        user.setToken(token.getToken());

        userEventPublish.userCreated(user);

        return user;
    }

}
