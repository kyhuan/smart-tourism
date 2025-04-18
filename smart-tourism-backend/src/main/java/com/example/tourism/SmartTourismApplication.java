package com.example.tourism;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.example.tourism.mapper")
@EnableSwagger2
public class SmartTourismApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartTourismApplication.class, args);
    }
}
