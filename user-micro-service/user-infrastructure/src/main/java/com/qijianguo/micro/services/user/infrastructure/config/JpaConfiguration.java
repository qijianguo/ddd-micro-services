package com.qijianguo.micro.services.user.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.qijianguo.micro.services.**.domain.**.repository.po"})
@EnableJpaRepositories(basePackages = {"com.qijianguo.micro.services.**.domain.**.repository.mapper"})
public class JpaConfiguration {
}
