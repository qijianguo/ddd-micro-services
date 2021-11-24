package com.qijianguo.micro.services.base.libs.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AesUtils.class);

    public static final String AES_KEY = "1234567890000000";

    public static String encrypt(String sSrc) throws Exception {
        return encrypt(sSrc, AES_KEY);
    }

    /**
     * 加密：密码长度为16
     *
     * @param sSrc
     * @param sKey
     */
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            LOGGER.error("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            LOGGER.error("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        //return new Base64().encodeToString(encrypted);
        // return Base64.encode(encrypted);
        return Base64Utils.encodeToString(encrypted);
    }

    public static String decrypt(String sSrc){
        return decrypt(sSrc, AesUtils.AES_KEY);
    }

    /**
     * 解密：密码长度为16
     *
     * @param sSrc
     * @param sKey
     */
    public static String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                LOGGER.error("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                LOGGER.error("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64转码
            //byte[] encrypted1 = new Base64().decode(sSrc);
            byte[] encrypted1 = Base64Utils.decode(sSrc.getBytes(StandardCharsets.UTF_8));
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original, StandardCharsets.UTF_8);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }


    /*//加密
    public static String encrypt(String content, String password) {
        try {
            //将秘钥补全为128位
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //若想改为DES加密，则需要将秘钥位数改为64位
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            //初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密
            byte[] result = cipher.doFinal(byteContent);
            //Base64转码
            return Base64.encode(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public static String decrypt(String content, String password) throws Exception {
        try {
            //将秘钥补全为128位
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //若想改为DES加密，则需要将秘钥位数改为64位
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            //初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            //Base64转码
            byte[] encrypted1 = Base64.decode(content);
            //解密
            byte[] result = cipher.doFinal(encrypted1);
            //二进制转为字符串
            return new String(result, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    /*public static void main(String[] args) throws Exception {
        System.out.println("秘钥为：" + AES_KEY);
        String source = "hello world";
        System.out.println("加密前：" + source);

        String enSour = encrypt(source, AES_KEY);
        System.out.println("加密后：" + enSour);

        String deSour = decrypt(enSour, AES_KEY);
        System.out.println("解密后：" + deSour);
    }*/
}
