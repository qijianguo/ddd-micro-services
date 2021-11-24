package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @author qijianguo
 */
@Data
public class Auth {

    private Integer userId;

    private Type type;

    private String unionKey;

    private String unionVal;

    private Date createTime;

    private Date modifyTime;

    public enum Type {
        PHONE,
        WX_AND,
        WX_IOS,
        USERNAME,
        QQ_AND,
        QQ_IOS,
        WB,

        ;
        public static Type match(String name) {
            for (Type type : Type.values()) {
                if (type.name().equals(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    public AuthPo toAuthPo() {
        AuthPo po = new AuthPo();


        return po;
    }

}
