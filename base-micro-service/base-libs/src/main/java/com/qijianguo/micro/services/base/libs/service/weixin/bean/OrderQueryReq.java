package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import org.springframework.util.StringUtils;
import lombok.Data;

/**
 * @author qijianguo
 */
@Data
public class OrderQueryReq {

    /** 微信开放平台审核通过的应用APPID （是）*/
    private String appid;

    /** 微信支付分配的商户号 （是）*/
    private String mch_id;

    /** 微信的订单号，优先使用 （是）*/
    private String transaction_id;

    /*------------ transaction_id 和 out_trade_no 二选一 ------------*/

    /** 商户系统内部的订单号，当没提供transaction_id时需要传这个。 （是）*/
    private String out_trade_no;

    /** 随机字符串，不长于32位 （是）*/
    private String nonce_str;

    /** 签名 （是）*/
    private String sign;

    public boolean validate() {
        return !StringUtils.isEmpty(appid) &&
                (!StringUtils.isEmpty(transaction_id) || !StringUtils.isEmpty(out_trade_no));
    }

    public OrderQueryReq(String appid, String transaction_id, String out_trade_no) {
        this.appid = appid;
        this.transaction_id = transaction_id;
        this.out_trade_no = out_trade_no;
    }

}
