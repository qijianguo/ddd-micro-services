package com.qijianguo.micro.services.user.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author qijianguo
 */
@ApiModel("获取图片验证码")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaImageRequest {

    private String key;
//
//    /** 后期可以处理为后台程序自动生成*/
//    @ApiModelProperty(value = "图片验证码的ID", dataType = "String")
//    @Length(min = 4, max = 4)
//    @NotEmpty
//    private String key;
//
//    @ApiModelProperty(value = "图片验证码的内容", dataType = "String")
//    @NotEmpty
//    private String content;

}
