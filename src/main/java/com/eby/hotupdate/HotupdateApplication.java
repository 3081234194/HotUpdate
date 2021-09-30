package com.eby.hotupdate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.eby.hotupdate.mapper")
@SpringBootApplication
public class HotupdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotupdateApplication.class, args);
    }

}
