package com.qijianguo.micro.services.user.interfaces.assembler;

import com.qijianguo.micro.services.user.domain.captcha.entity.*;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaImageRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneVerifyRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneResponse;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author qijianguo
 */
@Data
public class CaptchaAssembler {

    public static Captcha toDO(CaptchaPhoneRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(Captcha.Type.PHONE);
        captcha.setPhone(CaptchaAssembler.toPhone(request));
        if (!StringUtils.isEmpty(request.getCaptchaImage())) {
            Image image = toImage(request.getKey(), request.getCaptchaImage());
            captcha.setImage(image);
        }
        return captcha;
    }

    public static Captcha toDO(CaptchaImageRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(Captcha.Type.IMAGE);
        captcha.setImage(new Image(request.getKey()));
        return captcha;
    }

    public static Captcha toDO(CaptchaPhoneVerifyRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(Captcha.Type.PHONE);
        captcha.setPhone(CaptchaAssembler.toPhone(request));
        return captcha;
    }

    public static Phone toPhone(CaptchaPhoneVerifyRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getPhone());
        phone.setCode(request.getCode());
        return phone;
    }

    public static CaptchaPhoneResponse toDTO(Phone phone) {
        return new CaptchaPhoneResponse(phone.getLevel() == Phone.Level.LOWER, PhonePolicy.Config.LIMITED.getNum());
    }

    public static Phone toPhone(CaptchaPhoneRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getPhone());
        phone.setCreateTime(new Date());
        phone.setModifyTime(phone.getCreateTime());
        phone.setCount(0);
        return phone;
    }

    public static Image toImage(String key, String content) {
        Image image = new Image();
        image.setKey(key);
        image.setWords(content);
        return image;
    }
}
