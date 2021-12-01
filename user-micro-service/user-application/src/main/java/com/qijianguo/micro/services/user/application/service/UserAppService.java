package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.user.application.event.publish.UserEventPublish;
import com.qijianguo.micro.services.user.domain.verification.entity.Verification;
import com.qijianguo.micro.services.user.domain.verification.entity.VerificationFactory;
import com.qijianguo.micro.services.user.domain.verification.service.VerificationDomainService;
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
    private VerificationDomainService tokenVerificationDomainService;
    @Autowired
    private UserDomainService userDomainService;
    @Autowired
    private UserEventPublish userEventPublish;

    public User save(Verification verification) {
        phoneCaptchaAppService.validate(verification);

        User user = userDomainService.createUserByPhone(UserFactory.toUserDO(verification.getPhoneNumber()));

        VerificationFactory.addToken(verification, String.valueOf(user.getId()), user, 7, TimeUnit.DAYS);

        tokenVerificationDomainService.create(verification);

        user.setToken(verification.getTokenValue());

        userEventPublish.userCreated(user);

        return user;
    }

}
