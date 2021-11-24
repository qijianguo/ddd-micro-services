package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.CaptchaAppService;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.interfaces.assembler.PhoneAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @GetMapping(value = "/img", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] verifyImage(String key) {
        return (byte[]) imageCaptchaAppService.create(key);
    }

    @ApiOperation(value = "获取手机验证码", response = PhoneCodeResponse.class)
    @PostMapping("/phone")
    public BaseResponse<PhoneCodeResponse> phoneCode(@Valid PhoneCodeRequest request) {
        Phone phone = (Phone) phoneCaptchaAppService.create(request.getPhone());
        return BaseResponse.success(PhoneAssembler.toDTO(phone));
    }
}
