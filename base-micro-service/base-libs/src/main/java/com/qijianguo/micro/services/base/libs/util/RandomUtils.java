package com.qijianguo.micro.services.base.libs.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author qijianguo
 */
public class RandomUtils {

    private RandomUtils() {
    }

    public static final String ALL_CHAR = "-_#&$@+-*/%()[]0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String NUMBER_CHAR = "0123456789";

    public static final String SPECIAL_CHAR = "-_#&$@+-*/%()[]";

    public static final String LETTER_NUMBER_CHAR = LETTER_CHAR + NUMBER_CHAR;

    /**
     * 返回一个定长的随机字符串
     *
     * @param chars  模型串
     * @param length 随机长度
     * @return
     */
    public static String randomString(String chars, int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机字符串字母全部大写
     *
     * @param chars  模型串
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String randomLowerString(String chars, int length) {
        return randomString(chars, length).toLowerCase();
    }

    /**
     * 返回一个定长的随机字符串字母全部小写
     *
     * @param chars  模型串
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String randomUpperString(String chars, int length) {
        return randomString(chars, length).toLowerCase();
    }

    /**
     * 生成一个定长的纯0字符串
     *
     * @param length 字符串长度
     * @return 纯0字符串
     */
    public static String randomZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        return sb.toString();
    }


    /**
     * 生成一个最小值和最大值之间的整数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 介于最小值和最大值之间的整数
     */
    public static int randomNumRange(int min, int max) {
        Random r = new Random();
        return r.nextInt(max+1-min)+min;
    }

    /**
     * 安全的生成1-9数字
     * @return 0-9的数字
     */
    public static Integer secureRandomSingleNum() {
        SecureRandom instance = new SecureRandom();
        return instance.nextInt(10);
    }

    /**
     * 生成数字列表
     * @param size 生成的数字个数
     * @return list
     */
    public static List<Integer> secureRandomNumList(int size) {
        SecureRandom instance = new SecureRandom();
        IntStream ints = instance.ints(size, 0, 10);
        List<Integer> collect = ints.mapToObj(Integer::new).collect(Collectors.toList());
        return collect;
    }

    public static Integer secureRandomNum(int size) {
        SecureRandom instance = new SecureRandom();
        IntStream ints = instance.ints(1, (int) Math.pow(10, size - 1), (int) (Math.pow(10, size)) - 1);
        List<Integer> collect = ints.mapToObj(Integer::new).collect(Collectors.toList());
        return collect.get(0);
    }

}
