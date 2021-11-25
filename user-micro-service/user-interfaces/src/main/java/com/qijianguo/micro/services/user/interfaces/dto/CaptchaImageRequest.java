package com.qijianguo.micro.services.user.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author qijianguo
 */
@ApiModel("获取图片验证码")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaImageRequest {

    private String key;

}
