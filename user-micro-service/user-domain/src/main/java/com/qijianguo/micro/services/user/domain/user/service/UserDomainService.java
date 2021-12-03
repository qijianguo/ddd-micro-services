package com.qijianguo.micro.services.user.domain.user.service;

import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.entity.UserFactory;
import com.qijianguo.micro.services.user.domain.user.event.UserEvent;
import com.qijianguo.micro.services.user.domain.user.repository.facade.UserRepository;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户领域服务
 * @author qijianguo
 */
@Service
public class UserDomainService {

    @Autowired
    private UserRepository userRepository;

    public User createUserByPhone(User user) {
        UserPo userPo = userRepository.findByUnionKey(user.getPhone().get());
        UserEvent userEvent;
        // 存在
        if (userPo == null) {
            userRepository.save(userPo = UserFactory.toUserPO(user));
            userEvent = UserEvent.created(UserEvent.Type.CREATED, user);
        } else {
            userRepository.update(userPo);
            userEvent = UserEvent.created(UserEvent.Type.UPDATED, user);
        }
        user = UserFactory.toUserDO(userPo);
        userRepository.saveEvent(UserFactory.toUserEventPO(userEvent));
        return user;
    }

}
