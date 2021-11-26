package com.qijianguo.micro.services.user.interfaces.dto;

import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qijianguo
 */
@ApiModel(value = "查询手机验证码", parent = BaseResponse.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaPhoneResponse {
    @ApiModelProperty(value = "图片验证码校验", dataType = "boolean")
    private boolean imageVerify;

    @ApiModelProperty(value = "倒计时")
    private Integer countdown;

}
