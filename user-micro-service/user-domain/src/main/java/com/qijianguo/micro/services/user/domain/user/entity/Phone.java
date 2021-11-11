package com.qijianguo.micro.services.user.domain.user.entity;

/**
 * 电话
 * @author qijianguo
 */
public class Phone {

    private String phone;

    public String createPhoneCode() {
        // TODO create random code

        // save code in cache

        return "1234";
    }

    public boolean verifyPhoneCode(String phone, String code) {

        return false;
    }

    private String getPhoneCode(String phone) {
        // TODO get code in cache.

        return null;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
