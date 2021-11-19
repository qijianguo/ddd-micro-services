package com.qijianguo.micro.services.user.interfaces.facade;

import com.qijianguo.micro.services.common.entity.BaseResponse;
import com.qijianguo.micro.services.user.interfaces.dto.PhoneRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public BaseResponse loginByTel(PhoneRequest phoneRequest) {

        return null;
    }


}
