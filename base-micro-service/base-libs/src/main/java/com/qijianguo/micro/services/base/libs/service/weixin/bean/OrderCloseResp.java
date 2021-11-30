package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;

/**
 * @author qijianguo
 */
@Data
public class OrderCloseResp {

    private String return_code;

    private String return_msg;

    /** 应用ID */
    private String appid;

    /** 商户号 */
    private String mch_id;

    /** 随机字符串 */
    private String nonce_str;

    /** 签名 */
    private String sign;

    /** 业务结果 */
    private String result_code;;

    /** 业务结果描述 */
    private String result_msg;

    /** 错误代码 */
    private String err_code;

    /** 错误代码描述 */
    private String err_code_des;


}
