package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;

@Data
public class UserToken {

    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;

}
