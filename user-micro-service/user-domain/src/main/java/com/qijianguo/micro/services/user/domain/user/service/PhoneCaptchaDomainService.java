package com.qijianguo.micro.services.user.domain.user.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.libs.service.SmsService;
import com.qijianguo.micro.services.user.domain.user.entity.Captcha;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.domain.user.entity.PhoneFactory;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author qijianguo
 */
@Service("phoneCaptchaDomainService")
public class PhoneCaptchaDomainService extends CaptchaDomainService<Phone, Phone> {

    @Autowired
    private SmsService smsService;
    @Autowired
    private CaptchaDomainService imageCaptchaDomainService;

    @Override
    public Phone create(Captcha captcha) {
        Phone phone = validateAndMerge(PhoneFactory.create(captcha.getKey()));

        phone.updateCode();

        saveToCache(phone.generateKey(), phone);

        valid(captcha, phone);

        // captchaEventPublish.publish(phone);
        smsService.sendVerifyCode(phone.getNumber(), String.valueOf(phone.getCode()));
        return phone;
    }

    @Override
    public void commit(Captcha captcha) {
        Phone phone = PhoneFactory.createSimple(captcha.getKey(), Integer.parseInt(captcha.getValue()));
        Phone fromCache = getFromCache(phone.generateKey());
        if (fromCache == null || !fromCache.compareTo(phone)) {
            throw new BusinessException(UserEmBusinessError.CODE_EXPIRED);
        }
        clearCache(phone.generateKey());
    }

    private Phone validateAndMerge(Phone phone) {
        Phone cache = getFromCache(phone.generateKey());
        if (cache != null) {
            cache.verifyPhoneCode();
            return cache;
        }
        return phone;
    }

    private void valid(Captcha captcha, Phone phone) {
        // 验证
        if (phone.getLevel() == Phone.Level.LOWER) {
            Captcha subCaptcha = captcha.getSubCaptcha();
            if (subCaptcha == null || StringUtils.isEmpty(subCaptcha.getValue())
                    || !compareTo(subCaptcha.getValue(),
                    (String)imageCaptchaDomainService.getFromCacheAndClearCache(RedisKey.CAPTCHA_IMG(subCaptcha.getKey())))
            ) {
                throw new BusinessException(UserEmBusinessError.CAPTCHA_IMG_ERROR);
            }
        }
    }

}
