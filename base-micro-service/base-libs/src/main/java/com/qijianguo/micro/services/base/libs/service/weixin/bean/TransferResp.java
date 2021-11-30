package com.qijianguo.micro.services.base.libs.service.weixin.bean;

import lombok.Data;

@Data
public class TransferResp {

    /**
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String return_code;

    /**
     * 返回信息，如非空，为错误原因
     * 签名失败 参数格式校验错误
     */
    private String return_msg;

    /* =========以下字段在return_code为SUCCESS的时候有返回========== */

    /**
     * 业务结果：SUCCESS/FAIL，注意：当状态为FAIL时，存在业务结果未明确的情况。如果如果状态为FAIL，请务必关注错误代码（err_code字段），通过查询查询接口确认此次付款的结果。
     */
    private String result_code;

    /**
     * 商户号的appid
     */
    private String mch_appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 	微信支付分配的终端设备号
     */
    private String device_info;

    /**
     * 调用企业付款API时，微信系统内部产生的单号
     */
    private String detail_id;

    /**
     * SUCCESS:转账成功
     * FAILED:转账失败
     * PROCESSING:处理中
     */
    private String status;

    /**
     * 失败原因
     */
    private String reason;

    /**
     * 收款用户openid
     */
    private String openId;

    /**
     * 付款金额
     * 单位为“分”
     */
    private String payment_amount;

    /**
     * 错误码信息，注意：出现未明确的错误码时（SYSTEMERROR等），请务必用原商户订单号重试，或通过查询接口确认此次付款的结果。
     */
    private String err_code;

    /**
     * FROM API ：
     * 错误代码描述字段err_code_des只供人工定位问题时做参考，系统实现时请不要依赖这个字段来做自动化处理。
     */
    private String err_code_des;

    /** 随机字符串，不长于32位 */
    private String nonce_str;

    /* 以下字段在return_code 和result_code都为SUCCESS的时候有返回 */

    /**
     * 商户单号
     */
    private String partner_trade_no;

    /**
     * 发起转账的时间
     */
    private String transfer_time;

    /**
     * 微信支付成功时间
     */
    private String payment_time;

}
