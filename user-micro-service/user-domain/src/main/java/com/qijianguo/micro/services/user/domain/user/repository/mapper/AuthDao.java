package com.qijianguo.micro.services.user.domain.user.repository.mapper;

import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qijianguo
 */
@Repository
public interface AuthDao extends JpaRepository<AuthPo, Integer> {

    @Query(value = "select id, userId from AuthPo where userId = (select userId from AuthPo where unionKey = ?1)")
    List<AuthPo> selectByUnionKeyLeftJoinByUserId(String unionKey);


}
