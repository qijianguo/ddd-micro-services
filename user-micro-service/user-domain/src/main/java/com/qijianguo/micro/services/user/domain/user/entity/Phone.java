package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.base.libs.util.RandomUtils;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import lombok.Data;
import java.util.Date;

/**
 * 电话
 * @author qijianguo
 */
@Data
public class Phone {

    /** 手机号 */
    private String phone;
    /** 创建时间 */
    private Date createTime;
    /** 最近修改时间 */
    private Date modifyTime;
    /**
     * 请求次数：每日的会累计
     * 当判断创建时间早于今天，则重置count
     */
    private Integer count;
    /** 验证码 */
    private Integer code;
    /** 图片校验码 */
    private String captcha;

    private boolean verify = false;

    private Level level = Level.NORMAL;

    /** 创建手机验证码 */
    public void createCode() {
        int defLen = 4;
        createCode(defLen);
    }

    public void createCode(int length) {
        code = RandomUtils.secureRandomNum(length);
        count ++;
    }

    public boolean verifyPhoneCode() {
        return PhonePolicy.verify(this);
    }

    public boolean compareTo(Phone phone) {
        if (phone == null) {
            return false;
        }
        return phone.getPhone().equals(this.phone) && phone.getCode().equals(code);
    }

    public String generateKey() {
        return RedisKey.CAPTCHA_CODE(phone);
    }

    public static String generateKey(String phone) {
        return RedisKey.CAPTCHA_CODE(phone);
    }

    public enum Level {
        HIGH, NORMAL, LOWER,
        ;
    }
}
