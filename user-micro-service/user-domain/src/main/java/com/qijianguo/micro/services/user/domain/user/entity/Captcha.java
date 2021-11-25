package com.qijianguo.micro.services.user.domain.user.entity;

import lombok.Data;

/**
 * 验证码领域对象
 * @author qijianguo
 */
@Data
public class Captcha<T> {

    private CaptchaType type;

    private String key;

    private String value;

    private Captcha subCaptcha;

    private void createWithPhone() {

    }

    public void validate() {
        switch (type) {
            case PHONE:
                return;
        }
    }

    public void verify() {

    }

}
