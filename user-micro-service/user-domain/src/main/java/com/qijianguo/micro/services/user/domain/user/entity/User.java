package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.user.domain.role.entity.Role;

public class User {

    private Integer id;

    private String nickName;

    private String avatar;

    private Gender gender;

    private Integer age;

    private Integer state;

    private Role role;

    private Phone phone;

    public User createUserInfo() {
        return UserFactory.create();
    }

    public void updateUserInfo(User user) {}

    public User getUserInfo() {
        return null;
    }

    public long generateUserId() {
        return 0L;
    }


}
