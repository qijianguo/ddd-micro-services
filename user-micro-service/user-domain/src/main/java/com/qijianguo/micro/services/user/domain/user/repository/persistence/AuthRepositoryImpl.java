package com.qijianguo.micro.services.user.domain.user.repository.persistence;

import com.qijianguo.micro.services.user.domain.user.repository.facade.AuthRepository;
import com.qijianguo.micro.services.user.domain.user.repository.mapper.AuthDao;
import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author qijianguo
 */
@Repository
public class AuthRepositoryImpl implements AuthRepository {

    @Autowired
    private AuthDao authDao;

    @Override
    public List<AuthPo> selectByUnionId(String unionId) {
        return Optional.ofNullable(authDao.selectByUnionKeyLeftJoinByUserId(unionId)).orElse(new ArrayList<>());
    }

    @Override
    public void insert(AuthPo auth) {
        authDao.save(auth);
    }
}
