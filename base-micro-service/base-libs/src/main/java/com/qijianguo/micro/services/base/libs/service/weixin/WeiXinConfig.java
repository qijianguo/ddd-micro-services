package com.qijianguo.micro.services.base.libs.service.weixin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author qijianguo
 */
@Slf4j
public class WeiXinConfig {

    private static Properties config = null;
    /**
     * 返回系统config.properties配置信息
     *
     * @param key key值
     * @return value值
     */
    public static String getProperty(String key) {
        if (config == null) {
            synchronized (WeiXinConfig.class) {
                if (null == config) {
                    try {
                        Resource resource = new ClassPathResource("weixin.properties");
                        config = PropertiesLoaderUtils.loadProperties(resource);
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }

        return config.getProperty(key);
    }
}
