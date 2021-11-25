package com.qijianguo.micro.services.user.domain.user.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.libs.util.VerifyUtil;
import com.qijianguo.micro.services.user.domain.user.entity.Captcha;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author qijianguo
 */
@Service("imageCaptchaDomainService")
public class ImageCaptchaDomainService extends CaptchaDomainService<String, byte[]> {

    @Override
    public byte[] create(Captcha captcha) {
        Object[] image = VerifyUtil.createImage();
        // 保存内容
        saveToCache(RedisKey.CAPTCHA_IMG(captcha.getKey()), String.valueOf(image[0]));
        // 图片二进制发送给客户端
        return (byte[]) image[1];
    }

    @Override
    public void commit(Captcha captcha) {
        String o = getFromCache(RedisKey.CAPTCHA_IMG(captcha.getKey()));
        if (StringUtils.isEmpty(o) || !Objects.equals(o.toLowerCase(), captcha.getValue().toLowerCase())) {
            throw new BusinessException(UserEmBusinessError.CAPTCHA_IMG_ERROR);
        }
    }
}
