package com.qijianguo.micro.services.base.libs.bean;

/**
 * @author qijianguo
 */
public class SmsRequest {

    private String phone;

    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "number='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
