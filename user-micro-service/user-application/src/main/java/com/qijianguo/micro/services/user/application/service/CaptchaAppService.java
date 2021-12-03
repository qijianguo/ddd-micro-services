package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.exception.CaptchaEmBusinessError;
import com.qijianguo.micro.services.user.domain.captcha.service.CaptchaDomainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qijianguo
 */
@Service
public class CaptchaAppService {

    @Autowired
    private CaptchaDomainService imageCaptchaDomainService;
    @Autowired
    private CaptchaDomainService phoneCaptchaDomainService;
    @Autowired
    private CaptchaDomainService tokenCaptchaDomainService;

    @Transactional(rollbackFor = BusinessException.class)
    public Captcha create(Captcha captcha) {
        switch (captcha.getType()) {
            case PHONE:
                phoneCaptchaDomainService.create(captcha);
                break;
            case IMAGE:
                imageCaptchaDomainService.create(captcha);
                break;
            case TOKEN:
                imageCaptchaDomainService.create(captcha);
            default:
                throw new BusinessException(CaptchaEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
        }
        return captcha;

    }

    @Transactional(rollbackFor = BusinessException.class)
    public void validate(Captcha captcha) {
        switch (captcha.getType()) {
            case PHONE:
                phoneCaptchaDomainService.verify(captcha);
                break;
            case IMAGE:
                imageCaptchaDomainService.verify(captcha);
                break;
            case TOKEN:
                tokenCaptchaDomainService.verify(captcha);
            default:
                throw new BusinessException(CaptchaEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
        }
    }

}
