package com.itdr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itdr.mapper")
public class RlgspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RlgspringbootApplication.class, args);
    }

}
