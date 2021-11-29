package com.qijianguo.micro.services.user.domain.user.repository.facade;

import com.qijianguo.micro.services.user.domain.user.repository.po.UserEventPo;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;

/**
 * @author qijianguo
 */
public interface UserRepository {

    UserPo selectById(Integer id);

    UserPo selectByUnionId(String unionKey);

    void insert(UserPo user);

    void update(UserPo user);

    void delete(UserPo user);

    void saveEvent(UserEventPo user);
}
