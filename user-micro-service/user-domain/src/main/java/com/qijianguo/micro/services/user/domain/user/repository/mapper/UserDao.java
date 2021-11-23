package com.qijianguo.micro.services.user.domain.user.repository.mapper;

import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author qijianguo
 */
@Repository
public interface UserDao extends JpaRepository<UserPo, Integer> {
}
