package com.qijianguo.micro.services.user.interfaces.assembler;

import com.qijianguo.micro.services.user.domain.user.entity.Captcha;
import com.qijianguo.micro.services.user.domain.user.entity.CaptchaType;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaImageRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCommitRequest;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author qijianguo
 */
@Data
public class CaptchaAssembler {

    public static Captcha toDO(CaptchaPhoneCodeRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(CaptchaType.PHONE);
        captcha.setKey(request.getPhone());
        if (!StringUtils.isEmpty(request.getCaptchaImg())) {
            Captcha subCap = CaptchaAssembler.toDO(new CaptchaImageRequest("11111111"));
            subCap.setValue(request.getCaptchaImg());
            captcha.setSubCaptcha(subCap);
        }
        return captcha;
    }

    public static Captcha toDO(CaptchaImageRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(CaptchaType.IMAGE);
        captcha.setKey(request.getKey());
        return captcha;
    }

    public static Captcha toDO(CaptchaPhoneCommitRequest request) {
        Captcha captcha = new Captcha();
        captcha.setType(CaptchaType.PHONE);
        captcha.setKey(request.getPhone());
        captcha.setValue(String.valueOf(request.getCode()));
        return captcha;
    }
}
