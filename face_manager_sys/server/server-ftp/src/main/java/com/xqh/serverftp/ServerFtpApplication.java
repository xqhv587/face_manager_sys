package com.xqh.serverftp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = {"com.xqh.serverftp"})
@SpringBootApplication
public class ServerFtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerFtpApplication.class, args);
    }

}
