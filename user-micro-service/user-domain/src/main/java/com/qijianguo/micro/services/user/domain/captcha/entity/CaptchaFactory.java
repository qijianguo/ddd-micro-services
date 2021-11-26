package com.qijianguo.micro.services.user.domain.captcha.entity;

import com.qijianguo.micro.services.base.libs.util.VerifyUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author qijianguo
 */
public class CaptchaFactory {

    public static Phone createPhoneSimple(String number, Integer code) {
        Phone p = new Phone();
        p.setNumber(number);
        p.setCode(code);
        return p;
    }

    public static Phone createPhone(String number) {
        Phone phone = new Phone();
        phone.setNumber(number);
        phone.setCreateTime(new Date());
        phone.setModifyTime(phone.getCreateTime());
        phone.setCount(0);
        return phone;
    }

    public static Image createImage() {
        Image image = new Image();
        Object[] obj = VerifyUtil.createImage();
        image.setKey(UUID.randomUUID().toString().replace("-", ""));
        image.setWords(String.valueOf(obj[0]));
        image.setJpeg((byte[]) obj[1]);
        return image;
    }
}
