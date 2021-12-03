package com.qijianguo.micro.services.user.domain.captcha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qijianguo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String key;

    private String words;

    private byte[] jpeg;

    public Image(String key) {
        this.key = key;
    }
}
