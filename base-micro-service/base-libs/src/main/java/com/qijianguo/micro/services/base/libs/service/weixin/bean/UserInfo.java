package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;

@Data
public class UserInfo {

    private Integer subscribe;
    private String openid;
    private String nickname;
    private String nickname_emoji;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Integer subscribe_time;
    private String[] privilege;
    private String unionid;
    private Integer groupid;
    private String remark;
    private Integer[] tagid_list;

}

