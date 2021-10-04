package com.example.tavern;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.tavern.mapper")

public class TavernApplication {

    public static void main(String[] args) {
        SpringApplication.run(TavernApplication.class, args);
    }

}
