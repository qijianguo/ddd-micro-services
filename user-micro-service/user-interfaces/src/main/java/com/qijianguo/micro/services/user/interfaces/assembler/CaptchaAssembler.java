package com.qijianguo.micro.services.user.interfaces.assembler;

import com.qijianguo.micro.services.user.domain.verification.entity.*;
import com.qijianguo.micro.services.user.interfaces.dto.ImageRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneVerifyRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneResponse;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author qijianguo
 */
@Data
public class CaptchaAssembler {

    public static Verification toDO(PhoneRequest request) {
        Verification verification = new Verification();
        verification.setType(Verification.Type.PHONE);
        verification.setPhone(CaptchaAssembler.toPhone(request));
        if (!StringUtils.isEmpty(request.getCaptchaImage())) {
            Image image = toImage(request.getKey(), request.getCaptchaImage());
            verification.setImage(image);
        }
        return verification;
    }

    public static Verification toDO(ImageRequest request) {
        Verification verification = new Verification();
        verification.setType(Verification.Type.IMAGE);
        verification.setImage(new Image(request.getKey()));
        return verification;
    }

    public static Verification toDO(PhoneVerifyRequest request) {
        Verification verification = new Verification();
        verification.setType(Verification.Type.PHONE);
        verification.setPhone(CaptchaAssembler.toPhone(request));
        return verification;
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
