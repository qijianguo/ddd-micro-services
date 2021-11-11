package com.qijianguo.micro.services.user.domain.user.repository.facade;

import com.qijianguo.micro.services.user.domain.user.entity.User;

/**
 * @author qijianguo
 */
public interface UserRepository {

    void insert(User user);

    void update(User user);

    void delete(User user);

}
