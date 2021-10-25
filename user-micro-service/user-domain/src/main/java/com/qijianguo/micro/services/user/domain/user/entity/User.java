package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.user.domain.address.entity.Address;
import com.qijianguo.micro.services.user.domain.role.entity.Role;

import java.util.List;

public class User {

    private Integer id;

    private String nickName;

    private String avatar;

    private Gender gender;

    private Integer age;

    private Integer state;

    private List<Address> addresses;

    private Role role;



}
