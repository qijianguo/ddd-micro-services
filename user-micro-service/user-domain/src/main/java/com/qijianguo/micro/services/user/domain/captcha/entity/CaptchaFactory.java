package com.qijianguo.micro.services.user.domain.captcha.entity;

import com.qijianguo.micro.services.base.libs.util.VerifyUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
public class CaptchaFactory {

    public static void addImage(Captcha captcha) {
        Image image = new Image();
        Object[] obj = VerifyUtils.createImage();
        image.setKey(UUID.randomUUID().toString().replace("-", ""));
        image.setWords(String.valueOf(obj[0]));
        image.setJpeg((byte[]) obj[1]);
        captcha.setImage(image);
    }

    public static void addToken(Captcha captcha, String id, Object data) {
        Token token = new Token();
        token.setKey(id);
        token.setData(data);
        token.setTimeout(7);
        token.setTimeUnit(TimeUnit.DAYS);
        captcha.setToken(token);
    }

    public static void addToken(Captcha captcha, String id, Object data, int timeout, TimeUnit timeUnit) {
        Token token = new Token();
        token.setKey(id);
        token.setData(data);
        token.setTimeout(timeout);
        token.setTimeUnit(timeUnit);
        captcha.setToken(token);
    }

    public static Captcha toTokenCaptcha(String value) {
        Captcha captcha = create(Captcha.Type.TOKEN);
        Token token = new Token();
        token.setValue(value);
        captcha.setToken(token);
        return captcha;
    }

    private static Captcha create(Captcha.Type type) {
        Captcha captcha = new Captcha();
        captcha.setType(type);
        return captcha;
    }



}
