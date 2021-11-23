package com.qijianguo.micro.services.user.domain.user.entity;

import java.util.Objects;

/**
 * @author qijianguo
 */
public enum Gender {

    UNKNWN(-1, "-"),

    MALE(1, "男"),

    FEMALE(2, "女")

    ;

    int id;

    String desc;

    Gender(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static Gender match(int id) {
        for (Gender gender : Gender.values()) {
            if (Objects.equals(gender.id, id)) {
                return gender;
            }
        }
        return UNKNWN;
    }


}
