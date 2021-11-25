package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.libs.util.RandomUtils;
import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.CaptchaAppService;
import com.qijianguo.micro.services.user.domain.user.entity.Captcha;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.interfaces.assembler.CaptchaAssembler;
import com.qijianguo.micro.services.user.interfaces.assembler.PhoneAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaImageRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCommitRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaAppService imageCaptchaAppService;
    @Autowired
    private CaptchaAppService phoneCaptchaAppService;

    @ApiOperation(value = "获取图片验证码", response = PhoneCodeResponse.class)
    @PostMapping(value = "/img", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image() {
        String key = "11111111";
        CaptchaImageRequest request = new CaptchaImageRequest(key);
        return (byte[]) imageCaptchaAppService.create(CaptchaAssembler.toDO(request));
    }

    @ApiOperation(value = "获取手机验证码", response = PhoneCodeResponse.class)
    @PostMapping("/phone")
    public BaseResponse<PhoneCodeResponse> phone(@Valid CaptchaPhoneCodeRequest request) {
        Phone phone = (Phone) phoneCaptchaAppService.create(CaptchaAssembler.toDO(request));
        return BaseResponse.success(PhoneAssembler.toDTO(phone));
    }

}
