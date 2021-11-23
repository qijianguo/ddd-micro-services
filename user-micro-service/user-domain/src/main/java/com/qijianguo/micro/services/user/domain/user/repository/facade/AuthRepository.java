package com.qijianguo.micro.services.user.domain.user.repository.facade;

import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;

import java.util.List;

public interface AuthRepository {

    List<AuthPo> selectByUnionId(String unionId);

    void insert(AuthPo auth);
}
