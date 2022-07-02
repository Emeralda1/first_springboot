package com.example.lsc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ="com.example.lsc.mybatis.mapper")//扫描此包下的映射
public class Lsc24Application {
    public static void main(String[] args) {
        SpringApplication.run(Lsc24Application.class, args);
    }

}
