package com.qijianguo.micro.services.user.application.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.libs.util.VerifyUtil;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author qijianguo
 */
@Service("imageCaptchaAppService")
public class ImageCaptchaAppService extends CaptchaAppService<String, byte[]> {

    @Override
    public byte[] create(String key) {
        Object[] image = VerifyUtil.createImage();
        // 保存内容
        putInCache(RedisKey.CAPTCHA_IMG(key), String.valueOf(image[0]));
        // 图片二进制发送给客户端
        return (byte[]) image[1];
    }

    @Override
    public void validate(String key, String value) {
        String o = pullFromCache(RedisKey.CAPTCHA_IMG(key));
        if (StringUtils.isEmpty(o) || !Objects.equals(o.toLowerCase(), value.toLowerCase())) {
            throw new BusinessException(UserEmBusinessError.CAPTCHA_ERROR);
        }
    }
}
