package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author qijianguo
 */
@Data
@ToString
public class UnifiedOrderResp {

    /** SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断 (是)*/
    private String return_code;

    /** 返回信息，如非空，为错误原因 签名失败 参数格式校验错误 (否)*/
    private String return_msg;

    /* --------------------- 以下字段在return_code为SUCCESS的时候有返回 ---------------------*/

    /** 调用接口提交的应用ID （是）*/
    private String appid;

    /** 调用接口提交的商户号 （是）*/
    private String mch_id;

    /** 调用接口提交的终端设备号 （否）*/
    private String device_info;

    /** 微信返回的随机字符串 （是）*/
    private String nonce_str;

    /** 微信返回的签名 （是）*/
    private String sign;

    /** SUCCESS/FAIL （是）*/
    private String result_code;

    /**  （否）*/
    private String err_code;

    /** 错误返回的信息描述  （否）*/
    private String err_code_des;


    /* ---------------------以下字段在return_code 和result_code都为SUCCESS的时候有返回---------------------*/


    /** 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP （是）*/
    private String trade_type;

    /** 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时（是） */
    private String prepay_id;

}
