package com.qijianguo.micro.services.user.interfaces.dto;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;

/**
 * @author qijianguo
 */
@ApiModel(value = "查询手机验证码", parent = BaseResponse.class)
@Data
public class PhoneCodeResponse {

    @ApiModelProperty(value = "倒计时")
    private Integer countdown;

    public PhoneCodeResponse(Integer countdown) {
        this.countdown = countdown;
    }
}
