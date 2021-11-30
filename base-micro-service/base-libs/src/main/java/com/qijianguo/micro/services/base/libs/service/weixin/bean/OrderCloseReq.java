package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;

/**
 * @author qijianguo
 */
@Data
public class OrderCloseReq {

    /** 应用ID (是) */
    private String appid;

    /** 商户号 (是) */
    private String mch_id;

    /** 商户订单号 (是) */
    private String out_trade_no;

    /** 随机字符串 (是) */
    private String nonce_str;

    /** 签名 (是) */
    private String sign;

    public boolean validate() {
        return true;
    }


}
