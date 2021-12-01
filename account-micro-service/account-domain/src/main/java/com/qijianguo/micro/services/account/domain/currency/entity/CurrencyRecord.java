package com.qijianguo.micro.services.account.domain.currency.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author qijianguo
 */
@Data
public class CurrencyRecord {

    private Integer CurrencyId;

    private int value;

    private Date createTime;

    private Date expiredTime;

    private Currency.Type type;

}
