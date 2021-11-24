package com.qijianguo.micro.services.base.libs.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 邀请码生成器，算法原理：<br/>
 * 1) 获取id: 1127738 <br/>
 * 2) 使用自定义进制转为：gpm6 <br/>
 * 3) 转为字符串，并在后面加'o'字符：gpm6o <br/>
 * 4）在后面随机产生若干个随机数字字符：gpm6o7 <br/>
 * 转为自定义进制后就不会出现o这个字符，然后在后面加个'o'，这样就能确定唯一性。最后在后面产生一些随机字符进行补全。<br/>
 *
 * @author jiayu.qiu
 */
public class InvitationCodeUtil {

    /**
     * 自定义进制（选择你想要的进制数，不能重复且最好不要0、1这些容易混淆的字符）
     */
    private static final char[] r = new char[]{'4', 'f', 'h', '7', 'r', 'y', 'o', 'x', 'p', '6', 'a', 'z', '1', '2', 'm', 's', 'k', '3', 'n', 'w', 'c', 'v', '0', 'u', 'g', 'j', 'e', '8', '5', 'b', '9', 't', 'l', 'q', 'i', 'd'};

    /**
     * 定义一个字符用来补全邀请码长度（该字符前面是计算出来的邀请码，后面是用来补全用的）
     */
    private static final char b = 'a';

    /**
     * 进制长度
     */
    private static final int binLen = r.length;

    /**
     * 邀请码长度
     */
    private static final int s = 6;

    /**
     * 补位字符串
     */
    private static String e = "njread";

    /**
     * 根据ID生成六位随机码
     *
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id, String solt) {
        e = Optional.ofNullable(solt).orElse(e);

        char[] buf = new char[32];
        int charPos = 32;

        while ((id / binLen) > 0) {
            int ind = (int) (id % binLen);
            buf[--charPos] = r[ind];
            id /= binLen;
        }
        buf[--charPos] = r[(int) (id % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动补全
        if (str.length() < s) {
            StringBuilder sb = new StringBuilder();
            sb.append(e.subSequence(0, s - str.length()));
            str += sb.toString();
        }
        return str;
    }


    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 99999999; i++) {
            String code = toSerialCode(i, "bandu");
            //System.out.println(String.format("%d 生成的code：%s", i, code));
            //Long id = codeToId(code);
            //System.out.println(String.format("%s 反序列化id：%d", code, id));

            if (map.containsKey(code)) {
                Integer size = map.get(code);
                map.put(code, size++);
                System.out.println(code + " " + size);
            } else {
                map.put(code, 1);
            }
        }
        System.out.println(map);
        //System.out.println(toSerialCode(0));
        //System.out.println(toSerialCode());
        //System.out.println(toSerialCode(1288L)); // ff9oi6
        /*for (int i = 199999999; i > 0; i++) {
            System.out.println("id-->"+ i); //
            String code = toSerialCode(i);
            System.out.println("code-->"+ code); // fo53b1
            System.out.println("id-->"+codeToId(code)); // fo53b1
        }*/
        /*for (long i = 0; i < 100; i++) {
            System.out.println("id-->"+ i); //
            String code = toSerialCode(i);
            System.out.println("code-->"+ code); // fo53b1
            System.out.println("-----");
        }*/
//        String code = toSerialCode(1);
//        System.out.println("code-->"+ code); // fo53b1
//        System.out.println("id-->"+codeToId(code)); // fo53b1

        //System.out.println(codeToId("999999"));
    }


}