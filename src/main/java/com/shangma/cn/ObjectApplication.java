package com.shangma.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.shangma.cn.mapper"})
@EnableTransactionManagement
public class ObjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectApplication.class);
    }

}
