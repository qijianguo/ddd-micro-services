package com.qijianguo.micro.services.user.application.assembler;

import com.qijianguo.micro.services.user.application.dto.ImageRequest;
import com.qijianguo.micro.services.user.application.dto.PhoneRequest;
import com.qijianguo.micro.services.user.application.dto.PhoneResponse;
import com.qijianguo.micro.services.user.application.dto.PhoneVerifyRequest;
import com.qijianguo.micro.services.user.domain.captcha.entity.*;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author qijianguo
 */
@Data
public class CaptchaAssembler {

    public static Captcha toDO(PhoneRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(Captcha.Type.PHONE);
        captcha.setPhone(CaptchaAssembler.toPhone(request));
        if (!StringUtils.isEmpty(request.getCaptchaImage())) {
            Image image = toImage(request.getKey(), request.getCaptchaImage());
            captcha.setImage(image);
        }
        return captcha;
    }

    public static Captcha toDO(ImageRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(Captcha.Type.IMAGE);
        captcha.setImage(new Image(request.getKey()));
        return captcha;
    }

    public static Captcha toDO(PhoneVerifyRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(Captcha.Type.PHONE);
        captcha.setPhone(CaptchaAssembler.toPhone(request));
        return captcha;
    }

    public static Phone toPhone(PhoneVerifyRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getPhone());
        phone.setCode(request.getCode());
        return phone;
    }

    public static PhoneResponse toDTO(Phone phone) {
        return new PhoneResponse(phone.getLevel() == Phone.Level.LOWER, PhonePolicy.Config.LIMITED.getNum());
    }

    public static Phone toPhone(PhoneRequest request) {
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
