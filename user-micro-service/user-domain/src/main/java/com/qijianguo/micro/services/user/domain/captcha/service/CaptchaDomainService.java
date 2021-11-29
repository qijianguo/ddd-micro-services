package com.qijianguo.micro.services.user.domain.captcha.service;

import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.repository.facade.CaptchaRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
    private CaptchaRepository captchaRepository;

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
    T get(String key) {
        return (T) captchaRepository.getByKey(key);
    }

    T getAndDel(String key) {
        return (T) captchaRepository.getByKeyAndDel(key);
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
    void save(String key, T t) {
        captchaRepository.save(key, t);
    }

    /**
     * 保存到缓存
     * @param key 关键字
     * @param t 数据对象
     */
    void save(String key, T t, int timeout) {
        captchaRepository.save(key, t, timeout, TimeUnit.SECONDS);
    }

    /**
     * 保存到缓存
     * @param key 关键字
     * @param t 数据对象
     */
    void save(String key, T t, int timeout, TimeUnit timeUnit) {
        captchaRepository.save(key, t, timeout, timeUnit);
    }

    void refresh(String key, int timeout, TimeUnit timeUnit) {
        captchaRepository.refresh(key, timeout, timeUnit);
    }

    /**
     * 清除缓存
     * @param key
     */
    void delete(String key) {
        captchaRepository.deleteByKey(key);
    }


}