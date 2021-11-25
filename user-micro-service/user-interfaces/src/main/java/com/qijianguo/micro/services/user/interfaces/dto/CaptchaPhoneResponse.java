package com.qijianguo.micro.services.user.interfaces.dto;

import com.qijianguo.micro.services.user.domain.user.entity.CaptchaType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author qijianguo
 */
@ApiModel
@Data
public class CaptchaPhoneResponse {

    private CaptchaType type;

    private String value;
}
