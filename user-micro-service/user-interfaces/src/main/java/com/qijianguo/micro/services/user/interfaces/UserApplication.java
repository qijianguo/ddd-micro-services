package com.qijianguo.micro.services.user.interfaces;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author qijianguo
 */
@SpringBootApplication(scanBasePackages = {"com.qijianguo.micro.services.user", "com.qijianguo.micro.services.base"})
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
