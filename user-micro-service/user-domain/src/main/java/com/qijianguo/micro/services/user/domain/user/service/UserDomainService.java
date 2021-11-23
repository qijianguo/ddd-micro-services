package com.qijianguo.micro.services.user.domain.user.service;

import com.qijianguo.micro.services.user.domain.user.entity.Auth;
import com.qijianguo.micro.services.user.domain.user.entity.AuthFactory;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.entity.UserFactory;
import com.qijianguo.micro.services.user.domain.user.repository.facade.AuthRepository;
import com.qijianguo.micro.services.user.domain.user.repository.facade.UserRepository;
import com.qijianguo.micro.services.user.domain.user.repository.po.AuthPo;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户领域服务
 * @author qijianguo
 */
@Service
public class UserDomainService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;

    public User createUserByPhone(String phone) {
        User user;
        List<AuthPo> authPos = authRepository.selectByUnionId(phone);
        // 存在
        if (CollectionUtils.isEmpty(authPos)) {
            user = UserFactory.create(phone);
            UserPo userPo = UserFactory.toPO(user);
            userRepository.insert(userPo);
            user.setId(userPo.getId());

            AuthPo authPo = AuthFactory.convertTo(Auth.Type.PHONE, user);
            authRepository.insert(authPo);

            Auth auth = AuthFactory.toDO(authPo);
            user.addAuth(auth);
        } else {
            UserPo userPo = userRepository.selectById(authPos.get(0).getId());
            userRepository.update(userPo);
            user = UserFactory.toDO(userPo);
        }
        return user;
    }

}
