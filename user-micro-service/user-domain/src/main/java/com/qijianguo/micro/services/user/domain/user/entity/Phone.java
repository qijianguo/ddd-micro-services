package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.exception.EmBusinessError;
import com.qijianguo.micro.services.base.libs.util.TimeUtils;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RandomUtils;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import lombok.Data;
import lombok.Getter;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public void createPhoneCode() {
        // create random code
        code = RandomUtils.secureRandomNum(4);
        count ++;
    }

    public boolean verifyPhoneCode() {
        // 不允许重复请求
        long now = System.currentTimeMillis();
        long i = now - Config.LIMITED.getMilliTime() - modifyTime.getTime();
        if (i <= 0) {
            throw new BusinessException(UserEmBusinessError.CODE_REQ_PHONE_REQ_FREQUENCY, String.format(UserEmBusinessError.CODE_REQ_PHONE_REQ_FREQUENCY.getErrMsg(), (int) (i / 1000)));
        }
        // 限制请求次数
        try {
            if (createTime.after(TimeUtils.convertDate2Date(new Date(), TimeUtils.YYYY_HH_MM_00_00_00)) && count > Config.DAY_LIMITED.num) {
                throw new BusinessException(UserEmBusinessError.CODE_REQ_MAX_COUNTS);
            } else {
                modifyTime = createTime = new Date();
                code = null;
                count = 1;
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        return true;
    }

    public boolean compareTo(Phone phone) {
        if (phone == null) {
            return false;
        }
        return phone.getPhone().equals(this.phone) && phone.getCode().equals(code);
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

    public String generateKey() {
        return RedisKey.PHONE_CODE + phone;
    }

    public String generateKey(String phone) {
        return RedisKey.PHONE_CODE + phone;
    }

    @Getter
    public enum Config {
        /** 验证码过期时间: 5 分钟 */
        EXPIRED(300, TimeUnit.SECONDS),
        /** 验证码请求限制： 1 分钟 */
        LIMITED(60, TimeUnit.SECONDS),
        /*----------------------------------*/
        /** 验证码请求限制： 1 分钟 */
        SEC_LIMITED(1),
        /** 每天请求限制次数 */
        DAY_LIMITED(5),

        ;
        /** 时间/次数 */
        private int num;
        /** 时间单位 */
        private TimeUnit timeUnit;
        Config(int num, TimeUnit timeUnit) {
            this.num = num;
            this.timeUnit = timeUnit;
        }

        Config(int num) {
            this.num = num;
        }

        public int getMilliTime() {
            return num * 1000;
        }
    }
}
