package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.libs.service.SmsService;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.qijianguo.micro.services.user.domain.user.entity.Phone.Config.EXPIRED;

/**
 * @author qijianguo
 */
@Service
public class PhoneAppService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SmsService smsService;

    public void createCode(Phone phone) {
        phone = getFromCache(phone);
        phone.createPhoneCode();
        // caching phone code in Redis
        saveInCache(phone);
        // send
        smsService.sendVerifyCode(phone.getPhone(), String.valueOf(phone.getCode()));
    }

    public void verifyCode(Phone phone) {
        Phone fromCache = getFromCache(phone.generateKey());
        if (fromCache == null || !fromCache.compareTo(phone)) {
            throw new BusinessException(UserEmBusinessError.CODE_EXPIRED);
        }
    }

    private void saveInCache(Phone phone) {
        redisTemplate.opsForValue().set(phone.generateKey(), phone, EXPIRED.getNum(), EXPIRED.getTimeUnit());
    }

    private Phone getFromCache(Phone phone) {
        Phone o = getFromCache(phone.generateKey());
        if (o != null) {
            o.verifyPhoneCode();
            return o;
        }
        return phone;
    }

    private Phone getFromCache(String generateKey) {
        return (Phone) redisTemplate.opsForValue().get(generateKey);
    }
}
