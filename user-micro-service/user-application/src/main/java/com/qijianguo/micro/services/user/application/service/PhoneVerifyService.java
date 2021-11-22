package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.libs.service.SmsService;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.qijianguo.micro.services.user.domain.user.entity.Phone.Config.EXPIRED;

/**
 * @author qijianguo
 */
@Service
public class PhoneVerifyService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SmsService smsService;

    public void createCode(Phone phone) {
        phone = getPhoneRecordFromCache(phone);
        phone.createPhoneCode();
        // caching phone code in Redis
        saveInCache(phone);
        // send
        smsService.sendVerifyCode(phone.getPhone(), String.valueOf(phone.getCode()));
    }

    private void saveInCache(Phone phone) {
        redisTemplate.opsForValue().set(phone.generateKey(), phone, EXPIRED.getNum(), EXPIRED.getTimeUnit());
    }

    private Phone getPhoneRecordFromCache(Phone phone) {
        String generateKey = phone.generateKey();
        Object o = redisTemplate.opsForValue().get(generateKey);
        if (o instanceof Phone) {
            Phone p = ((Phone)o);
            p.verifyPhoneCode();
            return p;
        }
        return phone;
    }
}
