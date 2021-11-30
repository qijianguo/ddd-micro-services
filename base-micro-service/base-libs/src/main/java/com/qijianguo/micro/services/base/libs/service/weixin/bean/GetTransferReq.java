package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author qijianguo
 */
@Data
public class GetTransferReq {

    /** 随机字符串，不长于32位 */
    private String nonce_str;

    /** 签名 */
    private String sign;

    /** 商户调用企业付款API时使用的商户订单号 */
    private String partner_trade_no;

    /** 微信支付分配的商户号 */
    private String mch_id;

    /** 商户号的appid */
    private String appid;

    public GetTransferReq(String appid, String partner_trade_no) {
        this.appid = appid;
        this.partner_trade_no = partner_trade_no;
    }

    public boolean validate() {
        return !StringUtils.isEmpty(partner_trade_no);
    }
}
