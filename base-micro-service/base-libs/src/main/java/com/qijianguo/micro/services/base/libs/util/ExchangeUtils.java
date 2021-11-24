package com.qijianguo.micro.services.base.libs.util;

import java.math.BigDecimal;

public class ExchangeUtils {

    /**
     * 积分数
     */
    public static final double INTEGRAL = 10000d;

    /**
     * 现金单位：元
     */
    public static final double CASH = 1d;

    /**
     * 积分转现金， 默认保留两位小数
     * 10000积分 = 1元
     *
     * @param integral
     * @return
     */
    public static String integral2Cash(int integral) {
        double x = integral / 10000d;
        // 元;
        String s = NumberUtils.keepPrecisionFloor(String.valueOf(x), 3);
        BigDecimal bd = new BigDecimal(s);
        return bd.stripTrailingZeros().toPlainString();
    }

    /**
     * 积分转现金
     * 10000积分 = 1元
     *
     * @param integral  积分
     * @param precision 保留位数
     * @return
     */
    public static String integral2Cash(int integral, Integer precision) {
        double x = integral / 10000d;
        // 元;
        String s = NumberUtils.keepPrecisionFloor(String.valueOf(x), precision);
        BigDecimal bd = new BigDecimal(s);
        return bd.stripTrailingZeros().toPlainString();
    }

    /**
     * 阅读时间转积分 30秒 = 100积分
     *
     * @param readTime 阅读时间（秒）
     * @return
     */
    public static Integer readTime2Integral(Long readTime) {
        double x = (readTime / 30) * 100;
        return (int) x;
    }

    /**
     * 字数换算成牛角币
     * 500字 = 100牛角币
     *
     * @param wordSize
     * @return
     */
    public static int wordSize2Integral(Long wordSize) {
        return (int) (wordSize / 500 * 100);
    }

    /**
     * 现金与积分兑换
     * @param cash （单位：分）1元 = 10000, 1分 = 1000
     * @return
     */
    public static int cash2Integral(int cash) {
        return cash * 1000;
    }

}
