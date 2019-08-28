package com.vy;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vy.mapper")
public class DemeossmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemeossmApplication.class, args);
    }

}
