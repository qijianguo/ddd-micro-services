package com.qijianguo.micro.services.account.interfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qijianguo
 */
@SpringBootApplication(scanBasePackages = {"com.qijianguo.micro.services.account.**", "com.qijianguo.micro.services.base.**"})
public class AccountMicroService {

    public static void main(String[] args) {
        SpringApplication.run(AccountMicroService.class, args);
    }

}
