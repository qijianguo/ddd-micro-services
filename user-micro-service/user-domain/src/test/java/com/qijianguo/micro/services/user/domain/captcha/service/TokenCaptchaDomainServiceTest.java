package com.qijianguo.micro.services.user.domain.captcha.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.entity.UserFactory;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.entity.CaptchaFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication(scanBasePackages = {"com.qijianguo.micro.services.user.**", "com.qijianguo.micro.services.base.**"})
public class TokenCaptchaDomainServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(TokenCaptchaDomainServiceTest.class);

    @Autowired
    private CaptchaDomainService tokenCaptchaDomainService;

    @Test(expected = BusinessException.class)
    public void create() throws InterruptedException {
        User user = UserFactory.toUserDO("1245678901");
        user.setId(1);

        Captcha create = new Captcha();
        // 创建token
        CaptchaFactory.addToken(create, String.valueOf(user.getId()), user, 10, TimeUnit.SECONDS);
        tokenCaptchaDomainService.create(create);

        // 验证token
        Captcha verify = CaptchaFactory.toTokenCaptcha(create.getTokenValue());
        tokenCaptchaDomainService.verify(verify);

        // 验证Token过期
        Thread.sleep(1000* 10);
        tokenCaptchaDomainService.verify(verify);

    }

    @Test
    public void verify() {

    }
}