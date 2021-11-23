package com.qijianguo.micro.services.user.domain.user.repository.facade;

import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;

/**
 * @author qijianguo
 */
public interface UserRepository {

    //User selectByPhone(String phone);

    UserPo selectById(Integer id);

    void insert(UserPo user);

    void update(UserPo user);

    void delete(UserPo user);

}
