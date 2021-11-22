package com.qijianguo.micro.services.user.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * @author qijianguo
 */
@ApiModel("提交手机号验证码")
@Data
public class PhoneCommitRequest {

    @ApiModelProperty(value = "手机号", dataType = "String", required = true, example = "12345678901")
    @Length(min = 11, max = 11, message = "手机号不正确!")
    @NotBlank(message = "手机号不能为空!")
    private String phone;

    @ApiModelProperty(value = "验证码", dataType = "Integer", required = true, example = "1234")
    @Range(min = 1000, max = 9999, message = "请输入有效的验证码！")
    @NotBlank(message = "验证码不能为空!")
    private Integer code;

}
