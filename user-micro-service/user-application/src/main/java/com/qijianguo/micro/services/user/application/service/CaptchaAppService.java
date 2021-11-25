package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.user.entity.Captcha;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.domain.user.service.CaptchaDomainService;
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

    public Object create(Captcha captcha) {
        switch (captcha.getType()) {
            case PHONE:
                return phoneCaptchaDomainService.create(captcha);
            case IMAGE:
                return imageCaptchaDomainService.create(captcha);
            case MAIL:
                break;
        }
        throw new BusinessException(UserEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
    }

    public void validate(Captcha captcha) {
        switch (captcha.getType()) {
            case PHONE:
                phoneCaptchaDomainService.commit(captcha);
                return;
            case IMAGE:
                imageCaptchaDomainService.commit(captcha);
                return;
            case MAIL:
                break;
        }
        throw new BusinessException(UserEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
    }

}
