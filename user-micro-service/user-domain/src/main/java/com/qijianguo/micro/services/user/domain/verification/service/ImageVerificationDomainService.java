package com.qijianguo.micro.services.user.domain.verification.service;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.user.domain.verification.entity.Verification;
import com.qijianguo.micro.services.user.domain.verification.entity.VerificationFactory;
import com.qijianguo.micro.services.user.domain.verification.entity.Image;
import com.qijianguo.micro.services.user.infrastructure.exception.UserEmBusinessError;
import com.qijianguo.micro.services.user.infrastructure.util.RedisKey;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author qijianguo
 */
@Service("imageVerificationDomainService")
public class ImageVerificationDomainService extends VerificationDomainService<String> {

    @Override
    public void create(Verification verification) {

        VerificationFactory.addImage(verification);

        // caching...
        save(RedisKey.CAPTCHA_IMG(verification.getImageKey()), verification.getImageWords());
    }

    @Override
    public void verify(Verification verification) {
        Image image = verification.getImage();
        String o = get(RedisKey.CAPTCHA_IMG(image.getKey()));
        if (StringUtils.isEmpty(o) || !Objects.equals(o.toLowerCase(), image.getWords().toLowerCase())) {
            throw new BusinessException(UserEmBusinessError.CAPTCHA_IMG_ERROR);
        }
    }
}
