package com.qijianguo.micro.services.user.domain.captcha.entity;

import lombok.Data;

/**
 * 验证码领域对象
 * @author qijianguo
 */
@Data
public class Captcha {

    private Type type;

    private Phone phone;

    private Image image;

    public String getPhoneNumber() {
        if (phone != null) {
            return phone.getNumber();
        }
        throw new IllegalArgumentException("Captcha Phone is Null");
    }

    public Integer getPhoneCode() {
        if (phone != null) {
            return phone.getCode();
        }
        throw new IllegalArgumentException("Captcha Phone is Null");
    }

    public String getImageKey() {
        if (image != null) {
            return image.getKey();
        }
        throw new IllegalArgumentException("Captcha Image is Null: ");
    }

    public String getImageWords() {
        if (image != null) {
            return image.getWords();
        }
        throw new IllegalArgumentException("Captcha Image is Null: ");
    }

    public String getImageJpeg() {
        if (image != null) {
            return image.getWords();
        }
        throw new IllegalArgumentException("Captcha Image is Null: ");
    }


    public enum Type {
        PHONE,
        IMAGE,
        MAIL,
        ;
    }


}
