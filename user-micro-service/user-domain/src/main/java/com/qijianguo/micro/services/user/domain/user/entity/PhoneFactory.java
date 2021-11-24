package com.qijianguo.micro.services.user.domain.user.entity;

import java.util.Date;

public class PhoneFactory {

    public static Phone createSimple(String phone, Integer code) {
        Phone p = new Phone();
        p.setPhone(phone);
        p.setCode(code);
        return p;
    }

    public static Phone create(String p) {
        Phone phone = new Phone();
        phone.setPhone(p);
        phone.setCreateTime(new Date());
        phone.setModifyTime(new Date());
        phone.setCaptcha("");
        phone.setCount(0);
        return phone;
    }

}
