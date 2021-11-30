package com.qijianguo.micro.services.base.libs.service.weixin.util;

import com.qijianguo.micro.services.base.libs.service.weixin.WeiXinConfig;
import com.qijianguo.micro.services.base.libs.util.MD5Utils;
import com.qijianguo.micro.services.base.libs.util.RandomUtils;
import com.qijianguo.micro.services.base.libs.util.TimeUtils;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;


public class PayUtil {

    private static final Logger log = LoggerFactory.getLogger(PayUtil.class);

    public static final String MCH_ID = WeiXinConfig.getProperty("wx.mchid");

    public static final String API_SECRET = WeiXinConfig.getProperty("wx.api.secret");

    public static final String ANDROID_API_SECRET = WeiXinConfig.getProperty("android.api.secret");
    public static final String IOS_SECRET = WeiXinConfig.getProperty("ios.api.secret");

    private static final String AND_APP_ID = WeiXinConfig.getProperty("android.app-id");
    private static final String IOS_APP_ID = WeiXinConfig.getProperty("ios.app-id");

    public static String getAppId(String platform) throws InterruptedException {
        if (platform == null) {
            throw new NullPointerException("平台platform不能为null");
        }
        switch (platform) {
            case "android":
               return AND_APP_ID;
            case "ios":
                return IOS_APP_ID;
            default:
                throw new InterruptedException("不支持该平台：" + platform);
        }
    }


    public static String getApiSecret(String platform) throws InterruptedException {
        switch (platform) {
            case "android":
                return ANDROID_API_SECRET;
            case "ios":
                return IOS_SECRET;
            default:
                throw new InterruptedException("不支持该平台：" + platform);
        }
    }


    /**
     * 商户转账单号
     *
     * @return
     */
    public static String getTransfersNo() {
        StringBuilder tsf = new StringBuilder("TsfNO");
        tsf.append(TimeUtils.convertLocalDateTime2String(LocalDateTime.now(), TimeUtils.YYYY_HH_MM_HH_MM_SS)).append(RandomUtils.randomNumRange(100, 1000));
        return tsf.toString();
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String getTradeNo(int limitId) {
        // 自增8位数 00000001
        StringBuilder td = new StringBuilder("TdNO");
        td.append(TimeUtils.convertLocalDateTime2String(LocalDateTime.now(), TimeUtils.YYYY_HH_MM_HH_MM_SS)).append(RandomUtils.randomNumRange(100, 1000)).append("_").append(limitId);
        return td.toString();
    }

    /**
     * 退款单号
     *
     * @return
     */
    public static String getRefundNo() {
        // 自增8位数 00000001
        StringBuilder rf = new StringBuilder("RfNO");
        rf.append(TimeUtils.convertLocalDateTime2String(LocalDateTime.now(), TimeUtils.YYYY_HH_MM_HH_MM_SS)).append(RandomUtils.randomNumRange(100, 1000));
        return rf.toString();
    }

    /**
     * 返回客户端ip
     *
     * @param request
     * @return
     */
    public static String getRemoteAddrIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取服务器的ip地址
     *
     * @param request
     * @return
     */
    public static String getLocalIp(HttpServletRequest request) {
        return request.getLocalAddr();
    }

    public static String getSign(Map<String, String> params, String paternerKey) throws UnsupportedEncodingException {
        return MD5Utils.getMD5(createSign(params, false) + "&key=" + paternerKey).toUpperCase();
    }

    /**
     * 构造签名
     *
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            // 参数为空不参与签名
            if (key == null || StringUtils.isEmpty(params.get(key))) {
                 continue;
            }
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueStr = "";
            if (null != value) {
                valueStr = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueStr, "UTF-8"));
            } else {
                temp.append(valueStr);
            }
        }
        return temp.toString();
    }

    /**
     * 创建支付随机字符串
     *
     * @return
     */
    public static String getNonceStr() {
        return RandomUtils.randomString(RandomUtils.LETTER_NUMBER_CHAR, 32);
    }

    /**
     * 支付时间戳
     *
     * @return
     */
    public static String payTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static SSLContext sslContext = null;


    static {
        Resource androidRes = new ClassPathResource("apiclient_cert.p12");
        try {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            char[] keyPassword = WeiXinConfig.getProperty("wx.mchid").toCharArray(); //证书密码
            keystore.load(androidRes.getInputStream(), keyPassword);
            sslContext = SSLContexts.custom().loadKeyMaterial(keystore, keyPassword).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        Resource iosRes = new ClassPathResource("apiclient_cert.p12");
        try {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            char[] keyPassword = WeiXinConfig.getProperty("wx.mchid").toCharArray(); //证书密码
            keystore.load(iosRes.getInputStream(), keyPassword);
            sslContext = SSLContexts.custom().loadKeyMaterial(keystore, keyPassword).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}