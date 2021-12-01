package com.qijianguo.micro.services.user.domain.verification.repository.persistence;

import com.qijianguo.micro.services.user.domain.verification.repository.facade.CaptchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
@Repository
public class CaptchaRepositoryImpl<T> implements CaptchaRepository<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public T findByKey(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public T findByKeyAndDel(String key) {
        T fromCache = findByKey(key);
        deleteByKey(key);
        return fromCache;
    }

    @Override
    public void save(String key, T t) {
        redisTemplate.opsForValue().set(key, t, 60, TimeUnit.SECONDS);
    }

    @Override
    public void save(String key, T t, int seconds) {
        redisTemplate.opsForValue().set(key, t, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void save(String key, T t, int timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, t, timeout, timeUnit);
    }

    @Override
    public void refresh(String key, int timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }
}
