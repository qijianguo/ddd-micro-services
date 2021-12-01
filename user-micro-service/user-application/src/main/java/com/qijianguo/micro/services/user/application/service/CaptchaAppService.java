package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.verification.entity.Verification;
import com.qijianguo.micro.services.user.domain.verification.service.VerificationDomainService;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qijianguo
 */
@Service
public class CaptchaAppService {

    @Autowired
    private VerificationDomainService imageVerificationDomainService;
    @Autowired
    private VerificationDomainService phoneVerificationDomainService;
    @Autowired
    private VerificationDomainService tokenVerificationDomainService;

    public Verification create(Verification verification) {
        switch (verification.getType()) {
            case PHONE:
                phoneVerificationDomainService.create(verification);
                break;
            case IMAGE:
                imageVerificationDomainService.create(verification);
                break;
            case TOKEN:
                imageVerificationDomainService.create(verification);
            default:
                throw new BusinessException(UserEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
        }
        return verification;

    }

    public void validate(Verification verification) {
        switch (verification.getType()) {
            case PHONE:
                phoneVerificationDomainService.verify(verification);
                break;
            case IMAGE:
                imageVerificationDomainService.verify(verification);
                break;
            case TOKEN:
                tokenVerificationDomainService.verify(verification);
            default:
                throw new BusinessException(UserEmBusinessError.CAPTCHA_TYPE_NOT_SUPPORT);
        }

    }

}
