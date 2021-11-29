package com.qijianguo.micro.services.user.domain.captcha.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.libs.service.SmsService;
import com.qijianguo.micro.services.user.domain.captcha.entity.*;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author qijianguo
 */
@Service("phoneCaptchaDomainService")
public class PhoneCaptchaDomainService extends CaptchaDomainService<Phone> {

    @Autowired
    private SmsService smsService;
    @Autowired
    private CaptchaDomainService imageCaptchaDomainService;

    @Override
    public void create(Captcha captcha) {
        Phone phone = validateAndMerge(captcha);

        phone.updateCode();

        save(phone.generateKey(), phone, 86400);

        captcha.setPhone(phone);

        smsService.sendVerifyCode(phone.getNumber(), String.valueOf(phone.getCode()));
    }

    @Override
    public void commit(Captcha captcha) {
        Phone phone = captcha.getPhone();

        Phone fromCache = get(phone.generateKey());
        if (fromCache == null || !fromCache.compareTo(phone)) {
            throw new BusinessException(UserEmBusinessError.CODE_EXPIRED);
        }
        delete(phone.generateKey());
    }

    private Phone validateAndMerge(Captcha captcha) {
        Phone cache = get(captcha.getPhone().generateKey());
        if (cache != null) {
            cache.verifyPhoneCode();
            safeVerify(captcha, cache);
            return cache;
        }
        return captcha.getPhone();
    }

    private void safeVerify(Captcha captcha, Phone cache) {
        // 验证
        if (cache.getLevel() == Phone.Level.LOWER) {
            Image image = captcha.getImage();
            if (image == null || StringUtils.isEmpty(image.getWords())
                    || !compareTo(image.getWords(),
                    (String)imageCaptchaDomainService.getAndDel(RedisKey.CAPTCHA_IMG(image.getKey())))
            ) {
                throw new BusinessException(UserEmBusinessError.CAPTCHA_IMG_ERROR);
            }
        }
    }

}
