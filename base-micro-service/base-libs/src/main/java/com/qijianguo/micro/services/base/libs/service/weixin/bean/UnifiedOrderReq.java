package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import org.springframework.util.StringUtils;
import lombok.Data;

/**
 * @author qijianguo
 */
@Data
public class UnifiedOrderReq {

    /** 微信开放平台审核通过的应用APPID:（是） */
    private String appid;

    /** 微信支付分配的商户号：（是）*/
    private String mch_id;

    /** 终端设备号(门店号或收银设备ID)，默认请传"WEB"（否）*/
    private String device_info;

    /** 随机字符串，不长于32位（是） */
    private String nonce_str;

    /** 签名（是）*/
    private String sign;

    /** 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5（否）*/
    private String sign_type;

    /** 商品描述交易字段格式根据不同的应用场景按照以下格式： APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。（是）*/
    private String body;

    /** 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传（否）*/
    private String detail;

    /** 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据（否）*/
    private String attach;

    /** 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一（是）*/
    private String out_trade_no;

    /** 	符合ISO 4217标准的三位字母代码，默认人民币：CNY（否）*/
    private String fee_type;

    /** 订单总金额，单位为分（是）*/
    private String total_fee;

    /** 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP（是）*/
    private String spbill_create_ip;

    /** 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。（否）*/
    private String time_start;

    /** 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，
     * 由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。
     * 建议：最短失效时间间隔大于1分钟（否）*/
    private String time_expire;

    /** 订单优惠标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠（否）*/
    private String goods_tag;

    /** 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。（是）*/
    private String notify_url;

    /** 支付类型： APP（是）*/
    private String trade_type;

    /** no_credit--指定不能使用信用卡支付 （否）*/
    private String limit_pay;

    /** Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效 （否）*/
    private String receipt;

    public boolean validate() {
        return !StringUtils.isEmpty(body) &&
                !StringUtils.isEmpty(out_trade_no) &&
                !StringUtils.isEmpty(total_fee) &&
                !StringUtils.isEmpty(notify_url)
                ;
    }

    public UnifiedOrderReq(String appid, String body, String out_trade_no, String total_fee, String notify_url) {
        this.appid = appid;
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.notify_url = notify_url;
    }
}
