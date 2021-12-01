package com.qijianguo.micro.services.account.infrastructure.util;

/**
 * @author qijianguo
 */
public class RedisKey {

    public static final String CAPTCHA_CODE(String key) {
        return String.format("capture_code:%s", key);
    }

    public static final String CAPTCHA_IMG(String key) {
        return String.format("capture_image:%s", key);
    }
}
