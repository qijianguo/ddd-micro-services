package com.qijianguo.micro.services.user.domain.captcha.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.captcha.entity.Captcha;
import com.qijianguo.micro.services.user.domain.captcha.entity.CaptchaFactory;
import com.qijianguo.micro.services.user.domain.captcha.entity.Image;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author qijianguo
 */
@Service("imageCaptchaDomainService")
public class ImageCaptchaDomainService extends CaptchaDomainService<String> {

    @Override
    public void create(Captcha captcha) {

        Image image = CaptchaFactory.createImage();

        captcha.setImage(image);

        // caching...
        saveToCache(RedisKey.CAPTCHA_IMG(image.getKey()), image.getWords());
    }

    @Override
    public void commit(Captcha captcha) {
        Image image = captcha.getImage();
        String o = getFromCache(RedisKey.CAPTCHA_IMG(image.getKey()));
        if (StringUtils.isEmpty(o) || !Objects.equals(o.toLowerCase(), image.getWords().toLowerCase())) {
            throw new BusinessException(UserEmBusinessError.CAPTCHA_IMG_ERROR);
        }
    }
}
