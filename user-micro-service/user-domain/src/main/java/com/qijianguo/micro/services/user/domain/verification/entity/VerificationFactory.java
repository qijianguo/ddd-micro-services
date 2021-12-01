package com.qijianguo.micro.services.user.domain.verification.entity;

import com.qijianguo.micro.services.base.libs.util.VerifyUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
public class VerificationFactory {

    public static void addImage(Verification verification) {
        Image image = new Image();
        Object[] obj = VerifyUtils.createImage();
        image.setKey(UUID.randomUUID().toString().replace("-", ""));
        image.setWords(String.valueOf(obj[0]));
        image.setJpeg((byte[]) obj[1]);
        verification.setImage(image);
    }

    public static void addToken(Verification verification, String id, Object data) {
        Token token = new Token();
        token.setKey(id);
        token.setData(data);
        token.setTimeout(7);
        token.setTimeUnit(TimeUnit.DAYS);
        verification.setToken(token);
    }

    public static void addToken(Verification verification, String id, Object data, int timeout, TimeUnit timeUnit) {
        Token token = new Token();
        token.setKey(id);
        token.setData(data);
        token.setTimeout(timeout);
        token.setTimeUnit(timeUnit);
        verification.setToken(token);
    }

    public static Verification toTokenVerification(String value) {
        Verification verification = create(Verification.Type.TOKEN);
        Token token = new Token();
        token.setValue(value);
        verification.setToken(token);
        return verification;
    }

    private static Verification create(Verification.Type type) {
        Verification verification = new Verification();
        verification.setType(type);
        return verification;
    }



}
