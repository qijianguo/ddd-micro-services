package com.qijianguo.micro.services.user.domain.captcha.entity;

import com.qijianguo.micro.services.base.libs.util.VerifyUtil;

import java.util.UUID;

/**
 * @author qijianguo
 */
public class CaptchaFactory {

    public static Image createImage() {
        Image image = new Image();
        Object[] obj = VerifyUtil.createImage();
        image.setKey(UUID.randomUUID().toString().replace("-", ""));
        image.setWords(String.valueOf(obj[0]));
        image.setJpeg((byte[]) obj[1]);
        return image;
    }

}
