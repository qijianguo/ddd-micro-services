package com.qijianguo.micro.services.account.interfaces.facade;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/account/currency")
public class CurrencyController {

    @GetMapping
    public BaseResponse get(Integer userId) {

        return BaseResponse.success();
    }

    public BaseResponse prepaid() {

        return BaseResponse.success();
    }

    public BaseResponse payment() {

        return BaseResponse.success();
    }


}
