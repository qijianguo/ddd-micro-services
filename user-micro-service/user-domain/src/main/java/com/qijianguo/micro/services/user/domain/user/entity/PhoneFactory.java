package com.qijianguo.micro.services.user.domain.user.entity;

public class PhoneFactory {

    public static Phone createSimple(String phone, Integer code) {
        Phone p = new Phone();
        p.setPhone(phone);
        p.setCode(code);
        return p;
    }

}
