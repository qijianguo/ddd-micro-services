package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.user.domain.user.entity.PhoneFactory;
import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qijianguo
 */
@Service
public class UserAppService {

    @Autowired
    private PhoneAppService phoneAppService;

    @Autowired
    private UserDomainService userDomainService;

    public User save(String phone, Integer code) {
        phoneAppService.verifyCode(PhoneFactory.createSimple(phone, code));

        User userByPhone = userDomainService.createUserByPhone(phone);

        return userByPhone;
    }

}
