package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.CaptchaAppService;
import com.qijianguo.micro.services.user.domain.verification.entity.Image;
import com.qijianguo.micro.services.user.domain.verification.entity.Phone;
import com.qijianguo.micro.services.user.interfaces.assembler.CaptchaAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.ImageRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneResponse;
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

    @ApiOperation(value = "获取图片验证码", response = PhoneResponse.class)
    @PostMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(HttpServletResponse response) {
        // TODO 后期可以处理为后台程序自动生成
        String key = "11111111";
        ImageRequest request = new ImageRequest();
        // 将Key放到Header中，返回给用户
        Image image = imageCaptchaAppService.create(CaptchaAssembler.toDO(request)).getImage();
        response.setHeader("captcha_img_key", image.getKey());
        return image.getJpeg();
    }

    @ApiOperation(value = "获取手机验证码", response = PhoneResponse.class)
    @PostMapping("/phone")
    public BaseResponse<PhoneResponse> phone(@RequestBody @Valid PhoneRequest request) {
        Phone phone = phoneCaptchaAppService.create(CaptchaAssembler.toDO(request)).getPhone();
        return BaseResponse.success(CaptchaAssembler.toDTO(phone));
    }

}
