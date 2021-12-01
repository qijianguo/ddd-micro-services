package com.qijianguo.micro.services.user.domain.verification.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.entity.UserFactory;
import com.qijianguo.micro.services.user.domain.user.service.UserDomainService;
import com.qijianguo.micro.services.user.domain.verification.entity.Verification;
import com.qijianguo.micro.services.user.domain.verification.entity.VerificationFactory;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication(scanBasePackages = {"com.qijianguo.micro.services.user.**", "com.qijianguo.micro.services.base.**"})
public class TokenVerificationDomainServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(TokenVerificationDomainServiceTest.class);

    @Autowired
    private VerificationDomainService tokenVerificationDomainService;

    @Test(expected = BusinessException.class)
    public void create() throws InterruptedException {
        User user = UserFactory.toUserDO("1245678901");
        user.setId(1);

        Verification create = new Verification();
        // 创建token
        VerificationFactory.addToken(create, String.valueOf(user.getId()), user, 10, TimeUnit.SECONDS);
        tokenVerificationDomainService.create(create);

        // 验证token
        Verification verify = VerificationFactory.toTokenVerification(create.getTokenValue());
        tokenVerificationDomainService.verify(verify);

        // 验证Token过期
        Thread.sleep(1000* 10);
        tokenVerificationDomainService.verify(verify);
//        try {
//
//        } catch (Exception e) {
//            logger.info("捕获到异常： {}", e.getMessage());
//            Assert.isTrue(e instanceof BusinessException
//                    && ((BusinessException) e).getErrCode() == UserEmBusinessError.TOKEN_EXPIRED.getErrCode(),
//                    "Token验证失败 ： " + e.getMessage());
//        }

    }

    @Test
    public void commit() {

    }
}