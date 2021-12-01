package com.qijianguo.micro.services.user.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author qijianguo
 */
@ApiModel("获取手机号验证码")
@Data
@Valid
public class PhoneRequest {

    @ApiModelProperty(value = "手机号", dataType = "String", required = true, example = "12345678901")
    @Length(min = 11, max = 11, message = "手机号不正确!")
    @NotBlank(message = "手机号不能为空!")
    private String phone;

    @ApiModelProperty(value = "图片验证码的KEY", hidden = true)
    private String key;
    @ApiModelProperty(value = "图片验证码（选填）", dataType = "String", example = "a1b2")
    private String captchaImage;

}
