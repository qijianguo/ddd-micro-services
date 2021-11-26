package com.qijianguo.micro.services.user.domain.captcha.service;

import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 验证码的领域服务对象
 *
 * @param <T> 服务端保存的数据对象，用于后期校验用户的提交信息是否正确
 *           （例如：图片验证码的文字内容）
 *
 * @author qijianguo
 */
public abstract class CaptchaDomainService <T> {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 创建验证码
     */
    public abstract void create(Captcha captcha);

    /**
     * 验证码校验
     */
    public abstract void commit(Captcha captcha);

    /**
     * 查询缓存
     * @param key 关键字
     * @return 数据对象
     */
    T getFromCache(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    T getFromCacheAndClearCache(String key) {
        T fromCache = getFromCache(key);
        clearCache(key);
        return fromCache;
    }

    boolean compareTo(String input, String saved) {
        if (input == null || saved == null) {
            return false;
        }
        return Objects.equals(input.toLowerCase(), saved.toLowerCase());
    }

    /**
     * 保存到缓存
     * @param key 关键字
     * @param t 数据对象
     */
    void saveToCache(String key, T t) {
        redisTemplate.opsForValue().set(key, t, 60, TimeUnit.SECONDS);
    }

    /**
     * 保存到缓存
     * @param key 关键字
     * @param t 数据对象
     */
    void saveToCache(String key, T t, int seconds) {
        redisTemplate.opsForValue().set(key, t, seconds, TimeUnit.SECONDS);
    }


    /**
     * 清除缓存
     * @param key
     */
    void clearCache(String key) {
        redisTemplate.delete(key);
    }


}