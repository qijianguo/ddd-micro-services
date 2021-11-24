package com.qijianguo.micro.services.base.libs.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于Gson的一个工具类
 */
public class GsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonUtils.class);

    private GsonUtils() {
    }

    /**
     * 将一个对象转换为JSON格式的串
     *
     * @param object 任意对象
     * @return JSON格式的字符串
     */
    public static String convertVO2String(Object object) {
        try {
            Gson gson = new Gson();
            return gson.toJson(object);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将一个JSON格式的字符串转换为Java对象
     *
     * @param jsonStr     要转换的JSON格式的字符串
     * @param targetClass 要将这个JSON格式的字符串转换为什么类型的对象
     * @return 转换之后的Java对象
     */
    public static <T> T convertString2Object(String jsonStr, Class<T> targetClass) {
        try {
            Gson gson = new Gson();
            if (JSON.isValid(jsonStr)) {
                return gson.fromJson(jsonStr, targetClass);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将一个json转换成一个集合对象
     *
     * @param jsonStr   要转换的JSON格式的字符串
     * @param typeToken TypeToken<这里指定集合类型和泛型信息>
     * @return 转换之后的集合对象
     */
    public static <T> T convertString2Collection(String jsonStr, TypeToken<T> typeToken) {
        try {
            Gson gson = new Gson();
            if (JSON.isValid(jsonStr)) {
                T t = gson.fromJson(jsonStr, typeToken.getType());
                return t;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}