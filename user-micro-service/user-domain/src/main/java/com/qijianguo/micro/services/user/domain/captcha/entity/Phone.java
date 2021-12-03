package com.qijianguo.micro.services.user.domain.captcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qijianguo.micro.services.base.libs.util.RandomUtils;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import lombok.Data;
import java.util.Date;

/**
 * 电话
 * @author qijianguo
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {

    /** 手机号 */
    private String number;
    /** 创建时间 */
    private Date createTime;
    /** 最近修改时间 */
    private Date modifyTime;
    /**
     * 请求次数：每日的会累计
     * modifyTime.before(today) -> reset count
     */
    private Integer count;
    /** 验证码 */
    private Integer code;
    /**
     * 手机号安全等级
     */
    private Level level = Level.NORMAL;

    /** 刷新手机验证码 */
    public void updateCode() {
        int defLen = 4;
        updateCode(defLen);
    }

    /**
     * 创建指定长度的数字验证码
     * @param length
     */
    public void updateCode(int length) {
        code = RandomUtils.secureRandomNum(length);
        count ++;
        // captcha 校验
        if (count > PhonePolicy.Config.CAPTCHA_VERIFY.getNum()) {
            this.level = Phone.Level.LOWER;
        }
        modifyTime = new Date();
    }

    public boolean verifyPhoneCode() {
        return PhonePolicy.verify(this);
    }

    public boolean compareTo(Phone phone) {
        if (phone == null) {
            return false;
        }
        return phone.getNumber().equals(this.number) && phone.getCode().equals(code);
    }

    public String generateKey() {
        return RedisKey.CAPTCHA_CODE(number);
    }

    public static String generateKey(String phone) {
        return RedisKey.CAPTCHA_CODE(phone);
    }

    public enum Level {
        HIGH, NORMAL, LOWER,
        ;
    }
}
