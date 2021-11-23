package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author qijianguo
 */
public class AuthFactory {

    public static AuthPo convertTo(Auth.Type type, User user) {
        AuthPo auth = new AuthPo();
        auth.setUserId(user.getId());
        auth.setType(type.name());
        auth.setCreateTime(new Date());
        auth.setModifyTime(auth.getCreateTime());
        switch (type) {
            case PHONE:
                auth.setUnionKey(user.getPhone());
                auth.setUnionVal("");
                break;
            case WB:
                break;
        }
        return auth;
    }

    public static Auth toDO(AuthPo authPo) {
        Auth auth = new Auth();
        BeanUtils.copyProperties(authPo, auth);
        auth.setType(Auth.Type.match(authPo.getType()));
        return auth;
    }


}
