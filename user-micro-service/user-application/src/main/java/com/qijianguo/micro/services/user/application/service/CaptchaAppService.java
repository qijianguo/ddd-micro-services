package com.qijianguo.micro.services.user.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
public abstract class CaptchaAppService<T, K> {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 创建验证码
     * @param key 验证码的唯一键，作为缓存的KEY
     * @return 创建后需要返回的数据
     */
    public abstract K create(String key);

    /**
     * 验证码校验
     * @param key 验证码的唯一键，作为缓存的KEY
     * @param value 验证码的值
     */
    public abstract void validate(String key, String value);

    T pullFromCache(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    void putInCache(String key, T t) {
        redisTemplate.opsForValue().set(key, t, 60, TimeUnit.SECONDS);
    }

    void clearCache(String key) {
        redisTemplate.delete(key);
    }


}
