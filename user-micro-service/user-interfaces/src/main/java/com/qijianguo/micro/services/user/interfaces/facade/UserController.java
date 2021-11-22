package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneCommitRequest;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "根据手机号登录", response = BaseResponse.class)
    @PostMapping("/phone")
    public BaseResponse loginByPhone(@Valid PhoneCommitRequest phoneCommitRequest) {


        return null;
    }


}
