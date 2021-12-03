package com.qijianguo.micro.services.user.domain.captcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token<T> implements Principal {

    private String key;

    private String value;

    private int timeout;

    private TimeUnit timeUnit;

    private T data;

    public Token(String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return key;
    }

    @Getter
    public enum Config {

        EXPIRE_DATE(7, TimeUnit.DAYS),
        ;
        private int num;

        private TimeUnit timeUnit;

        Config(int num, TimeUnit timeUnit) {
            this.num = num;
            this.timeUnit = timeUnit;
        }

    }

}
