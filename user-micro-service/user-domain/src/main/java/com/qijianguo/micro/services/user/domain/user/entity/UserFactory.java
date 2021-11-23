package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.user.domain.role.entity.Role;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author qijianguo
 */
public class UserFactory {

    public static User create(String phone) {
        User user = new User();
        user.setNickName(phone);
        user.setAvatar("http://");
        user.setGender(Gender.UNKNWN);
        user.setAge(0);
        user.setState(User.Status.NORMAL);
        user.setRole(new Role());
        user.setPhone(phone);
        user.setAuths(new ArrayList<>());
        user.setCreateTime(new Date());
        user.setModifyTime(user.getCreateTime());
        return user;
    }

    public static UserPo toPO(User user) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(user, userPo);
        userPo.setGender(user.getGender().id);
        userPo.setState(user.getState().status);
        userPo.setRole(user.getRole().getId());
        return userPo;
    }

    public static User toDO(UserPo userPo) {
        User user = new User();
        BeanUtils.copyProperties(userPo, user);
        user.setGender(Gender.match(userPo.getId()));
        user.setRole(new Role(userPo.getRole()));
        return user;
    }


}
