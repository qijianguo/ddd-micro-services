package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.PhoneVerifyService;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.interfaces.assembler.PhoneAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneVerifyService phoneVerifyService;

    @ApiOperation(value = "获取手机验证码", response = PhoneCodeResponse.class)
    @PostMapping("/user")
    public BaseResponse<PhoneCodeResponse> phoneCode(@Valid PhoneCodeRequest request) {
        Phone phone = PhoneAssembler.toDO(request);
        phoneVerifyService.createCode(phone);
        PhoneCodeResponse response = PhoneAssembler.toDTO(phone);
        return BaseResponse.success(response);
    }
}
