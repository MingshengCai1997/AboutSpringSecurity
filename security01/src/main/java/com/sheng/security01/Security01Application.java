package com.sheng.security01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.sheng.security01.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Security01Application {

    public static void main(String[] args) {
        SpringApplication.run(Security01Application.class, args);
    }

}
