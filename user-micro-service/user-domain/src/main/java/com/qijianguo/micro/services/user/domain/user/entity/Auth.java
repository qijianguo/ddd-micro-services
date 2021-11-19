package com.qijianguo.micro.services.user.domain.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author qijianguo
 */
@Data
public class Auth {

    private String type;

    private String unionId;

    private String unionKey;

    private Date createTime;

    private Date modifyTime;

}
