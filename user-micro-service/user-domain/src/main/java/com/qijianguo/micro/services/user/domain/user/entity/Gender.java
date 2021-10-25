package com.qijianguo.micro.services.user.domain.user.entity;

public enum Gender {

    MALE(1, "男"),

    FEMALE(2, "女");

    private int id;

    private String desc;

    Gender(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
