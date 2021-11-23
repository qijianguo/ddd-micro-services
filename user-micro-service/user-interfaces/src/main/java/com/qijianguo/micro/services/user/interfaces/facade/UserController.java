package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.application.service.UserAppService;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCommitRequest;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserAppService userAppService;

    @ApiOperation(value = "根据手机号登录", response = BaseResponse.class)
    @PostMapping("/phone")
    public BaseResponse loginByPhone(@Valid PhoneCommitRequest phoneCommitRequest) {
        User save = userAppService.save(phoneCommitRequest.getPhone(), phoneCommitRequest.getCode());
        return BaseResponse.success(save);
    }


}
