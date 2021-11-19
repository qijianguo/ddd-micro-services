package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.user.domain.role.entity.Role;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class User {

    private Integer id;

    private String nickName;

    private String avatar;

    private Gender gender;

    private Integer age;

    private Integer state;

    private Role role;

    private Phone phone;

    private List<Auth> auths;

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

    /**
     * 修改性别
     * @param gender
     */
    public void updateGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 修改手机
     * @param phone
     */
    public void updatePhone(Phone phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * 检查用户登录方式已绑定
     * @param userAuth
     * @return
     */
    public boolean checkUserAuthBound(Auth userAuth) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        if (!CollectionUtils.isEmpty(auths)) {
            auths.forEach(auth -> {
                if (Objects.equals(userAuth.getUnionId(), auth.getUnionId())) {
                    atomicBoolean.set(false);
                }
            });
        }
        return atomicBoolean.get();
    }

    /**
     * 绑定用户登录方式
     * @param auth
     * @return
     */
    public boolean bindUserAuth(Auth auth) {
        if (auths == null) {
            auths = new ArrayList<>(1);
        }
        return auths.add(auth);
    }

    public static void main(String[] args) {
        User user = new User();
        Auth auth = new Auth();
        System.out.println(user.bindUserAuth(auth));;
        System.out.println(user.auths.size());
    }
}
