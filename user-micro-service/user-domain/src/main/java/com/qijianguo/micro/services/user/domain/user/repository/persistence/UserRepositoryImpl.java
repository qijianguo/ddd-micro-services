package com.qijianguo.micro.services.user.domain.user.repository.persistence;

import com.qijianguo.micro.services.user.domain.user.repository.facade.UserRepository;
import com.qijianguo.micro.services.user.domain.user.repository.mapper.UserDao;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author qijianguo
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserDao userDao;

    @Override
    public UserPo selectById(Integer id) {
        return userDao.findById(id).get();
    }

    @Override
    public void insert(UserPo user) {
        userDao.save(user);
    }

    @Override
    public void update(UserPo user) {
        user.setModifyTime(new Date());
        userDao.saveAndFlush(user);
    }

    @Override
    public void delete(UserPo user) {

    }
}
