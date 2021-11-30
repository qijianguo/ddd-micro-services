package com.qijianguo.micro.services.base.libs.service.weixin.api;


import com.qijianguo.micro.services.base.libs.service.weixin.bean.*;
import com.qijianguo.micro.services.base.libs.util.HttpUtils;
import com.qijianguo.micro.services.base.libs.service.weixin.util.PayUtil;
import com.qijianguo.micro.services.base.libs.util.MapUtils;
import com.qijianguo.micro.services.base.libs.util.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Objects;

import static com.qijianguo.micro.services.base.libs.service.weixin.util.PayUtil.*;

/**
 * 微信商户支付API
 * @author qijianguo
 */
public class PayMchApi {

    private static final Logger log = LoggerFactory.getLogger(PayMchApi.class);

    private static final String MCH_TRANSFERS = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    private static final String GET_MCH_TRANSFERS = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
    private static final String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    private static final String ORDER_CLOSE = "https://api.mch.weixin.qq.com/pay/closeorder";

    public static final String SUCCESS = "SUCCESS";

    /**
     * 用于企业向微信用户个人付款
     * https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
     */
    public static TransferResp transfers(TransferReq req) throws Exception {
        if (req == null || !req.validate()) {
            throw new IllegalAccessException("参数错误！");
        }
        req.setNonce_str(PayUtil.getNonceStr());
        req.setSpbill_create_ip(getIp());
        req.setPartner_trade_no(PayUtil.getTransfersNo());
        req.setMchid(MCH_ID);
        Map<String, String> map = MapUtils.objectToMap(req);
        req.setSign(PayUtil.getSign(map, API_SECRET));
        String reqXml = XmlUtils.toXmlWithCData(req);
        String resultXml = HttpUtils.posts(MCH_TRANSFERS, reqXml, sslContext);
        log.debug("transfers_response:{}", resultXml);
        TransferResp resp = XmlUtils.toBean(resultXml, TransferResp.class);
        validateTransfersResp(resp);
        return resp;
    }

    /**
     * 用于商户的企业付款操作进行结果查询，返回付款操作详细结果。
     * https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3
     */
    public static TransferResp getTransfers(GetTransferReq req) throws Exception {
        if (req == null || !req.validate()) {
            throw new IllegalAccessException("参数错误！");
        }
        req.setNonce_str(PayUtil.getNonceStr());
        req.setMch_id(MCH_ID);
        Map<String, String> map = MapUtils.objectToMap(req);
        req.setSign(PayUtil.getSign(map, API_SECRET));
        String reqXml = XmlUtils.toXmlWithCData(req);
        String resultXml = HttpUtils.posts(GET_MCH_TRANSFERS, reqXml, sslContext);
        log.debug("getTransfers_response:{}", resultXml);
        TransferResp resp = XmlUtils.toBean(resultXml, TransferResp.class);
        validateTransfersResp(resp);
        return resp;
    }

    /**
     * 商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易会话标识后再在APP里面调起支付。
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
     */
    public static UnifiedOrderResp unifiedOrder(UnifiedOrderReq req) throws Exception {
        if (req == null || !req.validate()) {
            throw new IllegalAccessException("参数错误！");
        }
        req.setMch_id(MCH_ID);
        req.setDevice_info("WEB");
        req.setNonce_str(PayUtil.getNonceStr());
        req.setSpbill_create_ip(getIp());
        req.setTrade_type("APP");
        req.setReceipt("Y");
        req.setSign(PayUtil.getSign(MapUtils.objectToMap(req), API_SECRET));
        String reqXml = XmlUtils.toXmlWithCData(req);
        String resultXml = HttpUtils.posts(UNIFIED_ORDER, reqXml, sslContext);
        log.debug("unifiedOrder_response:{}", resultXml);
        UnifiedOrderResp resp = XmlUtils.toBean(resultXml, UnifiedOrderResp.class);
        validateUnifiedOrderResp(resp);
        return resp;
    }

    /**
     * 支付结果通知
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
     */
    public static OrderNoticeResp checkOrderNotice(OrderNoticeReq req){
        log.debug("orderNotice:{}", req.toString());
        // 只有return_code和result_code都为success时才进行处理，否则不处理
        if (!orderSuccess(req.getReturn_code(), req.getResult_code())) {
            return noticeOrderFail(req.getReturn_msg());
        }
        // 验证签名
        try {
            String sign = PayUtil.getSign(MapUtils.objectToMap(req, "sign"), API_SECRET);
            if (!Objects.equals(req.getSign(), sign)) {
                return noticeOrderFail("微信签名错误");
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return noticeOrderFail(e.getMessage());
        }
        return PayMchApi.noticeOrderSuccess();
    }

    /**
     * 查询订单
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
     * @param req
     * @return
     * @throws Exception
     */
    public static OrderNoticeReq queryOrder(OrderQueryReq req) throws Exception {
        if (req != null && !req.validate()) {
            throw new IllegalAccessException("参数错误！");
        }
        req.setMch_id(MCH_ID);
        req.setNonce_str(PayUtil.getNonceStr());
        req.setSign(PayUtil.getSign(MapUtils.objectToMap(req, "sign"), API_SECRET));
        String reqXml = XmlUtils.toXmlWithCData(req);
        String resultXml = HttpUtils.posts(ORDER_QUERY, reqXml, sslContext);
        log.debug("queryOrder_response:{}", resultXml);
        OrderNoticeReq resp = XmlUtils.toBean(resultXml, OrderNoticeReq.class);
        validateOrderQueryResp(resp);
        return resp;
    }

    /**
     * 关闭订单（必须在订单生成5分钟后才能关闭）
     * @param req
     * @throws Exception
     */
    public static void closeOrder(OrderCloseReq req) throws Exception {
        if (req != null && !req.validate()) {
            throw new IllegalAccessException("参数错误！");
        }
        req.setMch_id(MCH_ID);
        req.setNonce_str(PayUtil.getNonceStr());
        req.setSign(PayUtil.getSign(MapUtils.objectToMap(req, "sign"), API_SECRET));
        String reqXml = XmlUtils.toXmlWithCData(req);
        String resultXml = HttpUtils.posts(ORDER_CLOSE, reqXml, sslContext);
        log.debug("closeOrder_response:{}", resultXml);
        OrderCloseResp resp = XmlUtils.toBean(resultXml, OrderCloseResp.class);
        if (!orderSuccess(resp.getReturn_code(), resp.getResult_code())) {
            //
        }

    }

    public static void validateTransfersResp(TransferResp resp) throws Exception {
        // 成功！
        if (orderSuccess(resp.getReturn_code(), resp.getResult_code())) {
            return;
        }
        // 失败，查询订单是否真正成功
        GetTransferReq reqG = new GetTransferReq(resp.getMch_appid(), resp.getPartner_trade_no());
        TransferResp transfers = PayMchApi.getTransfers(reqG);
        if (orderSuccess(resp.getReturn_code(), resp.getResult_code())) {
            return;
        }
        throw new Exception(transfers.getReturn_msg());
    }

    public static void validateUnifiedOrderResp(UnifiedOrderResp resp) throws Exception {
        if (orderSuccess(resp.getReturn_code(), resp.getResult_code())) {
            Map<String, String> resultMap = MapUtils.objectToMap(resp, "sign");
            String sign = PayUtil.getSign(resultMap, API_SECRET);
            if (!Objects.equals(sign, resp.getSign())) {
                throw new IllegalAccessException("返回参数签名校验失败！");
            }
        } else {
            log.error("unifiedOrder_response:{}", resp.toString());
            throw new IllegalAccessException(resp.getReturn_msg());
        }
    }

    public static void validateOrderQueryResp(OrderNoticeReq resp) throws Exception {
        if (orderSuccess(resp.getReturn_code(), resp.getResult_code())) {
            String sign = PayUtil.getSign(MapUtils.objectToMap(resp, "sign"), API_SECRET);
            if (!Objects.equals(sign, resp.getSign())) {
                throw new IllegalAccessException("返回参数签名校验失败！");
            }
        } else {
            log.error("orderQuery_response:{}", resp.toString());
            throw new IllegalAccessException(resp.getReturn_msg());
        }
    }

    private static String getIp() {
        return "103.46.128.41";
    }

    public static boolean orderSuccess(String returnCode, String resultCode) {
        return Objects.equals(returnCode, SUCCESS) && Objects.equals(resultCode, SUCCESS);
    }

    public static boolean paySuccess(String returnCode, String resultCode, String tradeState) {
        return Objects.equals(returnCode, SUCCESS) && Objects.equals(resultCode, SUCCESS) && Objects.equals(tradeState, SUCCESS);
    }

    public static OrderNoticeResp noticeOrderSuccess() {
        OrderNoticeResp result = new OrderNoticeResp();
        result.setReturn_code(NOTICE_SUCCESS_CODE);
        result.setReturn_msg(NOTICE_SUCCESS_MSG);
        return result;
    }

    public static OrderNoticeResp noticeOrderFail(String message) {
        OrderNoticeResp result = new OrderNoticeResp();
        result.setReturn_code(NOTICE_FAIL_CODE);
        result.setReturn_msg(String.format(NOTICE_FAIL_MSG, message));
        return result;
    }

    private static final String NOTICE_SUCCESS_CODE = "<![CDATA[SUCCESS]]>";
    private static final String NOTICE_SUCCESS_MSG = "<![CDATA[OK]]>";

    private static final String NOTICE_FAIL_CODE = "<![CDATA[FAIL]]>";
    private static final String NOTICE_FAIL_MSG = "<![CDATA[%s]]>";

    public static boolean notice(OrderNoticeResp orderNoticeResp) {
        if (Objects.equals(NOTICE_SUCCESS_CODE, orderNoticeResp.getReturn_code()) && Objects.equals(NOTICE_SUCCESS_MSG, orderNoticeResp.getReturn_msg())) {
            return true;
        }
        return false;
    }

}
