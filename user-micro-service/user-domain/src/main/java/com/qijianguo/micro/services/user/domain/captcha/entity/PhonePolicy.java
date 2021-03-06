package com.qijianguo.micro.services.user.domain.captcha.entity;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.captcha.exception.CaptchaEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.TimeUtils;
import lombok.Getter;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
public class PhonePolicy {

    public static boolean verify(Phone phone) {
        // 不允许重复请求
        Date now = new Date();
        long i = now.getTime() - Config.LIMITED.getMilliTime() - phone.getModifyTime().getTime();
        if (i <= 0) {
            String format = String.format("验证码获取频繁, 请在%d秒后重试.", (int) (i * -1 / 1000) );
            throw new BusinessException(CaptchaEmBusinessError.CODE_REQ_PHONE_REQ_FREQUENCY, format);
        }
        // 限制请求次数
        try {
            if (phone.getCreateTime().before(TimeUtils.dataFormat(now, TimeUtils.YYYY_HH_MM_00_00_00))) {
                reset(phone);
            } else if (phone.getCount() >= Config.DAY_LIMITED.num){
                throw new BusinessException(CaptchaEmBusinessError.CODE_REQ_MAX_COUNTS);
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        phone.setCreateTime(new Date());
        return true;
    }

    private static void reset(Phone phone) {
        phone.setCreateTime(new Date());
        phone.setCode(null);
        phone.setCount(0);
    }

    @Getter
    public enum Config {

        /** 验证码过期时间: 5 分钟 */
        EXPIRED(300, TimeUnit.SECONDS),
        /** 验证码请求限制： 1 分钟 */
        LIMITED(10, TimeUnit.SECONDS),
        /*----------------------------------*/
        /** 验证码请求限制： 1 分钟 */
        SEC_LIMITED(1),
        /** 每天请求限制次数 */
        DAY_LIMITED(5),
        /** 图片验证码开启校验 */
        CAPTCHA_VERIFY(2),

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
