package com.qijianguo.micro.services.base.libs.service.redis;

import java.lang.annotation.*;

/**
 * 锁的参数
 *
 * @author qijianguo
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}