package com.qijianguo.micro.services.user.domain.user.repository.mapper;

import com.qijianguo.micro.services.user.domain.user.repository.po.UserEventPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author qijianguo
 */
@Repository
public interface UserEventDao extends JpaRepository<UserEventPo, String> {


}
