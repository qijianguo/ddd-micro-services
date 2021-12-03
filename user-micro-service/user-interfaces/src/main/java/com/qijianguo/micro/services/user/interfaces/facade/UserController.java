package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.UserAppService;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.application.assembler.CaptchaAssembler;
import com.qijianguo.micro.services.user.application.dto.PhoneVerifyRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserAppService userAppService;

    @ApiOperation(value = "根据手机号登录", response = BaseResponse.class)
    @PostMapping("/phone")
    public BaseResponse loginByPhone(@RequestBody @Valid PhoneVerifyRequest phoneVerifyRequest, HttpServletResponse response) {
        User save = userAppService.save(CaptchaAssembler.toDO(phoneVerifyRequest));
        response.setHeader("authorization", save.getToken());
        return BaseResponse.success(save);
    }

}
