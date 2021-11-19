package com.qijianguo.micro.services.user.domain.account.entity;

/**
 * 用户账户
 * @author qijianguo
 */
public class Account {

    private Integer credit;

    public void createAccount() {
        AccountFactory.createAccount();
    }

    public void updateAccount() {

    }
}
