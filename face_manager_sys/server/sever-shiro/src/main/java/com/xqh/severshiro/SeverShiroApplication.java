package com.xqh.severshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xqh.severshrio.dao")

public class SeverShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeverShiroApplication.class, args);
    }

}


