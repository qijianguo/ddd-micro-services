package com.qijianguo.micro.services.user.interfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qijianguo
 */
@SpringBootApplication(scanBasePackages = {"com.qijianguo.micro.services.user.**", "com.qijianguo.micro.services.base.**"})
public class UserMicroService {

    public static void main(String[] args) {
        SpringApplication.run(UserMicroService.class, args);
    }

}
