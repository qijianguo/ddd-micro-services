package com.qijianguo.micro.services.user.domain.user.repository.persistence;

import com.qijianguo.micro.services.user.domain.user.repository.facade.UserRepository;
import com.qijianguo.micro.services.user.domain.user.repository.mapper.UserDao;
import com.qijianguo.micro.services.user.domain.user.repository.mapper.UserEventDao;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserEventPo;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author qijianguo
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserEventDao userEventDao;


    @Override
    public UserPo findById(Integer id) {
        return userDao.findById(id).get();
    }

    @Override
    public UserPo findByUnionKey(String unionKey) {
        return userDao.selectByUnionKey(unionKey);
    }

    @Override
    public void save(UserPo user) {
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

    @Override
    public void saveEvent(UserEventPo userEventPo) {
        userEventDao.save(userEventPo);
    }

    @Override
    public Page<UserPo> findByCriteria(Specification<UserPo> specification, Pageable pageable) {
        return userDao.findAll(specification, pageable);
    }
}
