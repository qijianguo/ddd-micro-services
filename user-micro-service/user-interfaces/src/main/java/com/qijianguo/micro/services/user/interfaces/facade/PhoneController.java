package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.PhoneAppService;
import com.qijianguo.micro.services.user.domain.user.entity.Phone;
import com.qijianguo.micro.services.user.interfaces.assembler.PhoneAssembler;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeRequest;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCodeResponse;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCommitRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneAppService phoneAppService;

    @ApiOperation(value = "获取手机验证码", response = PhoneCodeResponse.class)
    @PostMapping("/user")
    public BaseResponse<PhoneCodeResponse> phoneCode(@Valid PhoneCodeRequest request) {
        Phone phone = PhoneAssembler.toDO(request);
        phoneAppService.createCode(phone);
        return BaseResponse.success(PhoneAssembler.toDTO(phone));
    }

    @ApiOperation(value = "获取手机验证码", response = PhoneCodeResponse.class)
    @PutMapping("/user")
    public BaseResponse phoneCodeCommit(@Valid PhoneCommitRequest phoneCommitRequest) {
        Phone phone = PhoneAssembler.toDO(phoneCommitRequest);
        phoneAppService.verifyCode(phone);
        return BaseResponse.success();
    }
}
