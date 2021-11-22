package com.qijianguo.micro.services.user.infrastructure.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author qijianguo
 */
public class RandomUtils {

    private RandomUtils() {
    }

    public static Integer secureRandomNum() {
        SecureRandom instance = new SecureRandom();
        return instance.nextInt(10);
    }

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

    public static void main(String[] args) {
        System.out.println(secureRandomNum(2));
        System.out.println(secureRandomNum(3));
        System.out.println(secureRandomNum(4));
        System.out.println(secureRandomNum(5));
    }
}
