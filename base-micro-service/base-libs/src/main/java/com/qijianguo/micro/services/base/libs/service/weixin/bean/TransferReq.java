package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * @author qijianguo
 */
@Data
public class TransferReq {

    /** 商户appid */
    private String mch_appid;

    /** 商户号*/
    private String mchid;

    /** 随机字符串 */
    private String nonce_str;

    /** 商户订单号 */
    private String partner_trade_no;

    /** 用户openid */
    private String openid;

    /** 校验用户姓名选项 */
    private String check_name;

    /** 用户姓名 */
    @NotNull
    private String re_user_name;

    /** 金额：单位分 */
    private String amount;

    /** 企业付款描述信息 */
    private String desc;

    /** Ip地址 */
    private String spbill_create_ip;

    /** 签名 */
    private String sign;

    public boolean validate() {
        check_name = (!StringUtils.isEmpty(re_user_name) ? check_name = "FORCE_CHECK" : "NO_CHECK");
        return !StringUtils.isEmpty(openid) &&
                !StringUtils.isEmpty(amount) &&
                isInteger(amount) && !StringUtils.isEmpty(desc);
    }

    public TransferReq(String appid, String openid, String re_user_name, String amount, String desc) {
        this.mch_appid = appid;
        this.openid = openid;
        this.check_name = !StringUtils.isEmpty(re_user_name) ? "FORCE_CHECK" : "NO_CHECK";
        this.re_user_name = re_user_name;
        this.amount = amount;
        this.desc = desc;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
