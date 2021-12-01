package com.qijianguo.micro.services.account.domain.currency.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author qijianguo
 */
@Data
public class Currency {

    private Integer id;

    private Integer userId;

    private int balance;

    private int bonusCurrency;

    private Date modifyTime;

    private Date createTime;

    public Currency() {
    }

    /**
     * 增加积分
     * @param currencyRecord
     */
    public void increase(CurrencyRecord currencyRecord) {
        switch (currencyRecord.getType()) {
            case PREPAID:
                break;
            case BONUS:
                break;
            case EXCHANGE:
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    /**
     * 减少积分
     * @param currencyRecord
     */
    public void decrease(CurrencyRecord currencyRecord) {
        switch (currencyRecord.getType()) {
            case EXPEND:
                break;
            case EXPIRED:
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public enum Type {
        // 充值
        PREPAID,
        // 奖励
        BONUS,
        // 兑换
        EXCHANGE,
        // 消费
        EXPEND,
        // 失效
        EXPIRED,
        ;

    }

}
