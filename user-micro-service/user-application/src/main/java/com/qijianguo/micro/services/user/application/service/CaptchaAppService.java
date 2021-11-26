package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.service.CaptchaDomainService;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qijianguo
 */
@Service
public class CaptchaAppService {

    @Autowired
    private CaptchaDomainService imageCaptchaDomainService;
    @Autowired
    private CaptchaDomainService phoneCaptchaDomainService;

    public Captcha create(Captcha captcha) {
        switch (captcha.getType()) {
            case PHONE:
                phoneCaptchaDomainService.create(captcha);
                break;
            case IMAGE:
                imageCaptchaDomainService.create(captcha);
                break;
            default:
                throw new BusinessException(UserEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
        }
        return captcha;

    }

    public void validate(Captcha captcha) {
        switch (captcha.getType()) {
            case PHONE:
                phoneCaptchaDomainService.commit(captcha);
                break;
            case IMAGE:
                imageCaptchaDomainService.commit(captcha);
                break;
            default:
                throw new BusinessException(UserEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
        }

    }

}
