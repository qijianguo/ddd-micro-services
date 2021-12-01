package com.qijianguo.micro.services.user.domain.verification.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.libs.service.SmsService;
import com.qijianguo.micro.services.user.domain.verification.entity.*;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author qijianguo
 */
@Service("phoneVerificationDomainService")
public class PhoneVerificationDomainService extends VerificationDomainService<Phone> {

    @Autowired
    private SmsService smsService;
    @Autowired
    private VerificationDomainService imageVerificationDomainService;

    @Override
    public void create(Verification verification) {
        Phone phone = validateAndMerge(verification);

        phone.updateCode();

        save(phone.generateKey(), phone, 86400);

        verification.setPhone(phone);

        smsService.sendVerifyCode(phone.getNumber(), String.valueOf(phone.getCode()));
    }

    @Override
    public void verify(Verification verification) {
        Phone phone = verification.getPhone();

        Phone fromCache = get(phone.generateKey());
        if (fromCache == null || !fromCache.compareTo(phone)) {
            throw new BusinessException(UserEmBusinessError.CODE_EXPIRED);
        }
        delete(phone.generateKey());
    }

    private Phone validateAndMerge(Verification verification) {
        Phone cache = get(verification.getPhone().generateKey());
        if (cache != null) {
            cache.verifyPhoneCode();
            safeVerify(verification, cache);
            return cache;
        }
        return verification.getPhone();
    }

    private void safeVerify(Verification verification, Phone cache) {
        // 验证
        if (cache.getLevel() == Phone.Level.LOWER) {
            Image image = verification.getImage();
            if (image == null || StringUtils.isEmpty(image.getWords())
                    || !compareTo(image.getWords(),
                    (String) imageVerificationDomainService.getAndDel(RedisKey.CAPTCHA_IMG(image.getKey())))
            ) {
                throw new BusinessException(UserEmBusinessError.CAPTCHA_IMG_ERROR);
            }
        }
    }

}
