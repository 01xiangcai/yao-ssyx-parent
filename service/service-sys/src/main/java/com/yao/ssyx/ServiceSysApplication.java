package com.yao.ssyx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: ServiceSysApplication
 * @Description: TODO
 * @author: long
 * @date: 2023-10-18 16:52
 */
@SpringBootApplication
public class ServiceSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSysApplication.class,args);
    }
}
