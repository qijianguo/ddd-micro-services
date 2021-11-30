package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;

/**
 * @author qijianguo
 * 订单支付通知
 */
@Data
public class OrderNoticeReq {

    /** SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断（是）*/
    private String return_code;

    /** 返回信息，如非空，为错误原因 签名失败 参数格式校验错误（否）*/
    private String return_msg;

    /** 微信分配的小程序ID（是）*/
    private String appid;

    /**	微信支付分配的商户号（是）*/
    private String mch_id;

    /** 微信支付分配的终端设备号（否）*/
    private String device_info;

    /** 随机字符串，不长于32位（是）*/
    private String nonce_str;

    /** 签名（是）*/
    private String sign;

    /** 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5（否）*/
    private String sign_type;

    /** SUCCESS/FAIL（是）*/
    private String result_code;

    /** 错误返回的信息描述（否）*/
    private String err_code;

    /** 错误返回的信息描述（否）*/
    private String err_code_des;

    /** 用户在商户appid下的唯一标识（是）*/
    private String openid;

    /** 用户是否关注公众账号，Y-关注，N-未关注（是）*/
    private String is_subscribe;

    /** JSAPI、NATIVE、APP（是）*/
    private String trade_type;

    /**
     * 银行类型，采用字符串类型的银行标识（是）
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_2
     */
    private String bank_type;

    /** 订单总金额，单位为分（是）*/
    private String total_fee;

    /** 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额（否）*/
    private String settlement_total_fee;

    /** 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY（否）*/
    private String fee_type;

    /** 现金支付金额订单现金支付金额 (是) */
    private String cash_fee;

    /** 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY（否）*/
    private String cash_fee_type;

    /**	代金券金额<=订单金额，订单金额-代金券金额=现金支付金额（否）*/
    private String coupon_fee;

    /** 代金券使用数量（否）*/
    private String coupon_count;

    /** CASH--充值代金券
     * NO_CASH---非充值代金券
     * 并且订单使用了免充值券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
     *
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。下列情况可能导致订单不可以享受优惠：可能情况。
     * https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_202&index=7#menu4
     */
    private String coupon_type_$n;

    /**
     * 代金券ID,$n为下标，从0开始编号
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
     * 下列情况可能导致订单不可以享受优惠：可能情况。
     *（否）
     */
    private String coupon_id_$n;

    /** 单个代金券支付金额,$n为下标，从0开始编号（否）*/
    private String coupon_fee_$n;

    /** 微信支付订单号（是）*/
    private String transaction_id;

    /** 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一（是）*/
    private String out_trade_no;

    /** 商家数据包，原样返回（否）*/
    private String attach;

    /** 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010（是）*/
    private String time_end;

    /*-----------------以下是订单查询特有的字段-------------------*/

    /**SUCCESS—支付成功
     REFUND—转入退款
     NOTPAY—未支付
     CLOSED—已关闭
     REVOKED—已撤销（刷卡支付）
     USERPAYING--用户支付中
     PAYERROR--支付失败(其他原因，如银行返回失败)*/
    private String trade_state;

    /** 交易状态描述 对当前查询订单状态的描述和下一步操作的指引 */
    private String trade_state_desc;


}
