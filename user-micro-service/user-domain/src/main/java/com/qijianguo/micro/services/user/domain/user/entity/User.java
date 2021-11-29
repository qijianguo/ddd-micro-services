package com.qijianguo.micro.services.user.domain.user.entity;

import com.qijianguo.micro.services.user.domain.role.entity.Role;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author qijianguo
 */
@Data
public class User {

    private Integer id;

    private String nickName;

    private String avatar;

    private Gender gender;

    private Integer age;

    private Status state;

    private Role role;

    private String phone;

    private Auth.Type authType;

    private Set<Auth> auths = new HashSet<>();

    private Date createTime;

    private Date modifyTime;

    private String token;

    public long generateUserId() {
        return 0L;
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
                if (Objects.equals(userAuth.getUnionKey(), auth.getUnionKey())) {
                    atomicBoolean.set(false);
                }
            });
        }
        return atomicBoolean.get();
    }

    public void addAuth(Auth auth) {
        auths.add(auth);
    }

    /**
     * 绑定用户登录方式
     * @param auth
     * @return
     */
    public boolean bindUserAuth(Auth auth) {
        return auths.add(auth);
    }

    enum Status {
        DELETED(-1),
        NORMAL(0),
        ;

        int status;

        Status(int status) {
            this.status = status;
        }
    }

    public static void main(String[] args) {
        User user = new User();
        Auth auth = new Auth();
        System.out.println(user.bindUserAuth(auth));;
        System.out.println(user.auths.size());
    }

}
