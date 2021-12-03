package com.qijianguo.micro.services.user.domain.user.entity;

import com.alibaba.fastjson.JSON;
import com.qijianguo.micro.services.user.domain.role.entity.Role;
import com.qijianguo.micro.services.user.domain.user.event.UserEvent;
import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserEventPo;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.beans.BeanUtils;

import java.util.*;

import static com.qijianguo.micro.services.user.domain.user.entity.Auth.Type.*;

/**
 * @author qijianguo
 */
public class UserFactory {

    private UserFactory(){}

    public static User toUserDO(String phone) {
        User user = new User();
        user.setNickName(phone);
        user.setAvatar("http://");
        user.setGender(Gender.UNKNWN);
        user.setAge(0);
        user.setState(User.Status.NORMAL);
        user.setRole(new Role());
        user.setCreateTime(new Date());
        user.setModifyTime(user.getCreateTime());
        user.setAuthType(PHONE);
        user.addAuth(toAuthDO(user, phone, ""));
        return user;
    }

    public static UserPo toUserPO(User user) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(user, userPo);
        userPo.setGender(user.getGender().id);
        userPo.setState(user.getState().status);
        userPo.setRole(user.getRole().getId());
        userPo.setAuthPos(toAuthPOS(user.getAuths()));
        return userPo;
    }

    public static User toUserDO(UserPo userPo) {
        User user = new User();
        BeanUtils.copyProperties(userPo, user);
        user.setRole(new Role(userPo.getRole()));
        user.setGender(Gender.match(userPo.getId()));
        user.setAuths(toAuthDOS(userPo.getAuthPos()));
        return user;
    }

    private static Auth toAuthDO(User user, String unionKey, String unionVal) {
        Auth auth = new Auth();
        auth.setUserId(user.getId());
        auth.setType(user.getAuthType());
        auth.setCreateTime(new Date());
        auth.setModifyTime(auth.getCreateTime());
        auth.setUnionKey(unionKey);
        auth.setUnionVal(unionVal);
        return auth;
    }

    public static UserEventPo toUserEventPO(UserEvent userEvent) {
        UserEventPo userEventPo = new UserEventPo();
        BeanUtils.copyProperties(userEvent, userEventPo);
        userEventPo.setEventPayload(JSON.toJSONString(userEvent.getEventPayload()));
        userEventPo.setUserId(userEvent.getUserId());
        return userEventPo;
    }

    public static Auth toAuthDO(AuthPo authPo) {
        Auth auth = new Auth();
        BeanUtils.copyProperties(authPo, auth);
        auth.setType(Auth.Type.match(authPo.getType()));
        return auth;
    }

    public static Set<Auth> toAuthDOS(Set<AuthPo> authPos) {
        Set<Auth> list = new HashSet<>();
        authPos.forEach(po -> list.add(toAuthDO(po)));
        return list;
    }

    public static Set<AuthPo> toAuthPOS(Set<Auth> authDOS) {
        Set<AuthPo> set = new HashSet<>();
        authDOS.forEach(auth -> {
            if (auth != null && auth.getType() != null) {
                set.add(toAuthPO(auth));
            }
        });
        return set;
    }

    public static AuthPo toAuthPO(Auth auth) {
        AuthPo authPo = new AuthPo();
        BeanUtils.copyProperties(auth, authPo);
        authPo.setType(auth.getType().name());
        return authPo;
    }

}
