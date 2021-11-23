package com.qijianguo.micro.services.user.domain.user.repository.mapper;

import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthDao extends JpaRepository<AuthPo, Integer> {

    @Query(value = "select id, userId from t_auth where user_id = (select user_id from t_auth where union_key = :unionKey)")
    List<AuthPo> selectByUnionKeyLeftJoinByUserId(@Param("unionKey") String unionKey);
}
