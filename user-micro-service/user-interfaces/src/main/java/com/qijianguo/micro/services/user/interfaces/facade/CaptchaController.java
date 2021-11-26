package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.CaptchaAppService;
import com.qijianguo.micro.services.user.domain.captcha.entity.Image;
import com.qijianguo.micro.services.user.domain.captcha.entity.Phone;
import com.qijianguo.micro.services.user.interfaces.assembler.CaptchaAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaImageRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.CaptchaPhoneResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @ApiOperation(value = "获取图片验证码", response = CaptchaPhoneResponse.class)
    @PostMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(HttpServletResponse response) {
        // TODO 后期可以处理为后台程序自动生成
        String key = "11111111";
        CaptchaImageRequest request = new CaptchaImageRequest();
        // 将Key放到Header中，返回给用户
        response.setHeader("captcha_img_key", key);
        Image image = imageCaptchaAppService.create(CaptchaAssembler.toDO(request)).getImage();
        return image.getJpeg();
    }

    @ApiOperation(value = "获取手机验证码", response = CaptchaPhoneResponse.class)
    @PostMapping("/phone")
    public BaseResponse<CaptchaPhoneResponse> phone(@RequestBody @Valid CaptchaPhoneCodeRequest request) {
        Phone phone = phoneCaptchaAppService.create(CaptchaAssembler.toDO(request)).getPhone();
        return BaseResponse.success(CaptchaAssembler.toDTO(phone));
    }

}
