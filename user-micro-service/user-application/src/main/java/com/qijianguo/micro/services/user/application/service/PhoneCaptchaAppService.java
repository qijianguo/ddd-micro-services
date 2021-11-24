package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.libs.service.SmsService;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.domain.user.entity.PhoneFactory;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qijianguo
 */
@Service("phoneCaptchaAppService")
public class PhoneCaptchaAppService extends CaptchaAppService<Phone, Phone> {

    @Autowired
    private SmsService smsService;

    @Override
    public Phone create(String key) {
        Phone phone = before(PhoneFactory.create(key));

        phone.createCode();

        putInCache(phone.generateKey(), phone);

        // captchaEventPublish.publish(phone);
        smsService.sendVerifyCode(phone.getPhone(), String.valueOf(phone.getCode()));
        return phone;
    }

    @Override
    public void validate(String key, String value) {
        Phone phone = PhoneFactory.createSimple(key, Integer.parseInt(value));
        Phone fromCache = pullFromCache(phone.generateKey());
        if (fromCache == null || !fromCache.compareTo(phone)) {
            throw new BusinessException(UserEmBusinessError.CODE_EXPIRED);
        }
        clearCache(phone.generateKey());
    }

    private Phone before(Phone phone) {
        Phone cache = pullFromCache(phone.generateKey());
        if (cache != null) {
            cache.verifyPhoneCode();
            return cache;
        }
        return phone;
    }

}
