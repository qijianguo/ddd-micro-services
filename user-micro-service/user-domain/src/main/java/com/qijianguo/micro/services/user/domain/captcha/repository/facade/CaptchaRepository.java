package com.qijianguo.micro.services.user.domain.captcha.repository.facade;

import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
public interface CaptchaRepository<T> {

    /**
     * 查询数据
     * @param key 关键字
     * @return 数据对象
     */
    T findByKey(String key);

    /**
     * 查询并删除存储的记录
     * @param key 关键字
     * @return 数据对象
     */
    T findByKeyAndDel(String key);

    /**
     * 保存到缓存
     * @param key 关键字
     * @param t 数据对象
     */
    void save(String key, T t);

    /**
     * 保存到缓存
     * @param key 关键字
     * @param t 数据对象
     */
    void save(String key, T t, int seconds);

    /**
     * 保存到缓存
     * @param key 关键字
     * @param t 数据对象
     */
    void save(String key, T t, int timeout, TimeUnit timeUnit);

    /**
     * 刷新数据对象
     * @param key 关键字
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     */
    void refresh(String key, int timeout, TimeUnit timeUnit);

    /**
     * 删除记录
     * @param key
     */
    void deleteByKey(String key);
}
