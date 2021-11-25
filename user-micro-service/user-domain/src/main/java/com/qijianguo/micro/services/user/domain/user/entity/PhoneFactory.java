package com.qijianguo.micro.services.user.domain.user.entity;

import java.util.Date;

public class PhoneFactory {

    public static Phone createSimple(String number, Integer code) {
        Phone p = new Phone();
        p.setNumber(number);
        p.setCode(code);
        return p;
    }

    public static Phone create(String number) {
        Phone phone = new Phone();
        phone.setNumber(number);
        phone.setCreateTime(new Date());
        phone.setModifyTime(new Date());
        phone.setCount(0);
        return phone;
    }

}
