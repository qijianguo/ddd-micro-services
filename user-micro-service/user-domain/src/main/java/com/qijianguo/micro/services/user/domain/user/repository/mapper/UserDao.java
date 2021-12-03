package com.qijianguo.micro.services.user.domain.user.repository.mapper;

import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author qijianguo
 */
@Repository
public interface UserDao extends JpaRepository<UserPo, Integer>, JpaSpecificationExecutor<UserPo> {

    @Query(value = "select * from t_user where id = (select user_id from t_auth where union_key = ?1 limit 1)", nativeQuery = true)
    UserPo selectByUnionKey(String unionKey);
}
