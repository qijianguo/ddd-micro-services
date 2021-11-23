package com.qijianguo.micro.services.user.interfaces;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author qijianguo
 */
@SpringBootApplication(scanBasePackages = {"com.qijianguo.micro.services.user", "com.qijianguo.micro.services.base"})
@MapperScan(basePackages= "com.qijianguo.micro.services.user.domain.user.repository.mapper")
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
