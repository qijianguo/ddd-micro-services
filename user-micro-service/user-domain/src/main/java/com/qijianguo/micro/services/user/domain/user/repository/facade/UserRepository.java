package com.qijianguo.micro.services.user.domain.user.repository.facade;

import com.qijianguo.micro.services.user.domain.user.repository.po.UserEventPo;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;

/**
 * @author qijianguo
 */
public interface UserRepository {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    UserPo findById(Integer id);

    /**
     * 根据unionKey查询
     * @param unionKey
     * @return
     */
    UserPo findByUnionKey(String unionKey);

    /**
     * 保存
     * @param user
     */
    void save(UserPo user);

    void update(UserPo user);

    void delete(UserPo user);

    void saveEvent(UserEventPo user);
}
